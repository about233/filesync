package com.tdh.filesync.util;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FileMonitor
 * @Description
 * @Author yanggz
 * @Date 2021/6/17 10:30
 */
@Component
public class FileMonitor {
    /**
     * @param
     * @return
     * @description
     * @author yanggz
     */
    @PostConstruct
    public void initFileMonitor() throws IOException {
        Resource resource = new ClassPathResource("application.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        String inPath = props.getProperty("outPath");

        // 轮询间隔 1 秒
        Integer time = 1;
        long interval = TimeUnit.SECONDS.toMillis(time);
        // 创建一个文件观察器用于处理文件的格式,
        FileAlterationObserver observer = new FileAlterationObserver(
                inPath, FileFilterUtils.and(FileFilterUtils.suffixFileFilter(".zip")), null);
        observer.addListener(new FileListener()); //设置文件变化监听器
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        try {
            monitor.start();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }


}
