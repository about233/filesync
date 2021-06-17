package com.tdh.filesync.util;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class FileListener extends FileAlterationListenerAdaptor {
    /**
     * @description 启动监听
     * @param
     * @return
     * @author yanggz
     */
    @Override
    public void onStart(FileAlterationObserver observer) {
        // System.out.println("启动监听器：");
    }
    @Override
    public void onDirectoryCreate(File directory) {
        //有新文件夹生成
    }
    @Override
    public void onDirectoryChange(File directory) {
        //有文件夹内容发生变化
    }
    @Override
    public void onDirectoryDelete(File directory) {
        //有文件夹被删除
    }
    /**
     * @description 文件创建
     * @param
     * @return
     * @author yanggz
     */
    @Override
    public void onFileCreate(File file){
        Resource resource = new ClassPathResource("application.properties");
        Properties props = null;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String outPath = props.getProperty("inPath");
        try {
            FileOperate.copyGeneralFile(file.getAbsolutePath(),outPath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * @description 文件内容发生变化
     * @param
     * @return
     * @author yanggz
     */
    @Override
    public void onFileChange(File file){
        //有文件被修改
    }
    /**
     * @description 文件被删除
     * @param
     * @return
     * @author yanggz
     */
    @Override
    public void onFileDelete(File file){
        //有文件被删除
    }
    /**
     * @description 监听停止
     * @param
     * @return
     * @author yanggz
     */
    @Override
    public void onStop(FileAlterationObserver observer){
        // System.out.println("监听停止");
    }
 }
