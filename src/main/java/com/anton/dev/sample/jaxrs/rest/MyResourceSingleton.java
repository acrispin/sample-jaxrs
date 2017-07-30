/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.rest;

import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author anton
 */
@Path("test-singleton")
@Singleton
public class MyResourceSingleton {
	
    private int count;
    private final ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("count")
    public String testMethod() {
            count = count + 1;
            return "It works! This method was called " + count + " time(s)";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("count/async")
    public void testMethod2(@Suspended final AsyncResponse asyncResponse) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                asyncResponse.resume(doTestMethod2());
            }
        });
    }

    private String doTestMethod2() {
        count = count + 1;
        return "It works! This method async was called " + count + " time(s)";
    }
    
}


/*

Testear los request con Apache HTTP server benchmarking tool

instalacion
$ sudo apt-get install apache2-utils

$ ab -n1000 -c100 http://localhost:8084/sample-jaxrs/rest/test-singleton/count
$ ab -n1000 -c100 http://localhost:8084/sample-jaxrs/rest/test-singleton/count/async

*/