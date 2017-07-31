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
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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
        // https://stackoverflow.com/questions/949355/executors-newcachedthreadpool-versus-executors-newfixedthreadpool
        // https://stackoverflow.com/questions/17957382/fixedthreadpool-vs-cachedthreadpool-the-lesser-of-two-evils
        // service = Executors.newCachedThreadPool();
        // service = Executors.newFixedThreadPool(1000);
        /* Se inicia el pool con 20 threads, 1000 maximo threads en el pool, 60 seg tiempo libre del thread para que salga del pool  */
        service = new ThreadPoolExecutor(20, 1000, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        
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
