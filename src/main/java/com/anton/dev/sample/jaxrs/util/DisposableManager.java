/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class DisposableManager {
    private static DisposableManager instance;
    private final List<IDisposable> list;
    
    private DisposableManager() {
        list = new ArrayList<>();
    }
    
    public static synchronized DisposableManager getInstance() {
        if(instance==null){
            instance = new DisposableManager();
        }
        return instance;
    }
    
    public synchronized boolean push(IDisposable disposable) {
        return list.add(disposable);
    }
    
    public synchronized void cleanUp() {
        for(IDisposable item : list){
            item.dispose();
        }
        instance = null;
    }
    
}
