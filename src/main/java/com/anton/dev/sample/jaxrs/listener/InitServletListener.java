/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.listener;

import com.anton.dev.sample.jaxrs.util.DisposableManager;
import com.anton.dev.sample.jaxrs.util.ThreadPool;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author anton
 */
public class InitServletListener implements ServletContextListener {
    
    private static final Logger LOGGER = LogManager.getLogger(InitServletListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Iniciando ThreadPool ..........");
        DisposableManager.getInstance().push(ThreadPool.getInstance());
        LOGGER.info("Iniciado ThreadPool ..........");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Iniciando cleanup de recursos ..........");
        DisposableManager.getInstance().cleanUp();
        LOGGER.info("Finalizando cleanup de recursos ..........");
    }
    
}
