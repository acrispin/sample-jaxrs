/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author anton
 */
public class ThreadPool implements IDisposable {
    private static final Logger LOGGER = LogManager.getLogger(ThreadPool.class);
    private static ThreadPool instance;
    private ExecutorService service;
    
    private ThreadPool() {
        service = Executors.newCachedThreadPool();
        LOGGER.info("Instancia " + this.getClass().getName() + " creada" + " on thread: " + Thread.currentThread().getName());
    }
    
    public static ThreadPool getInstance() {       
        if (instance == null) {
            synchronized (ThreadPool.class) {
                if(instance == null) {
                    instance = new ThreadPool();
                }
            }
        }
        return instance;
    }
    
    public void execute(Runnable runnable){
        service.execute(runnable);
    }
    
    public void submit(Runnable runnable){
        service.submit(runnable);
    }
    
    public <T> Future<T> submit(Callable<T> task){
        return service.submit(task);
    }
    
    @Override
    public void dispose() {
        service.shutdown();
        service = null;
    }
    
}
