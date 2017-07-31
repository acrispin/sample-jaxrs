/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.rest;

import com.anton.dev.sample.jaxrs.util.ThreadPool;
import com.anton.dev.sample.jaxrs.util.Utility;
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
@Path("test-no-singleton")
public class MyResourceNoSingleton {
	
    private int count;
    // private final ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    private final ThreadPool pool = ThreadPool.getInstance();

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
        pool.submit(new Runnable() {
            @Override
            public void run() {
                asyncResponse.resume(doTestMethod2());
            }
        });
    }

    private String doTestMethod2() {
        count = count + 1;
        return "It works! This method2 async was called " + count + " time(s)";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("count2")
    public String testMethod3() throws InterruptedException {
        count = count + 1;
        long timeProcess = Utility.getRandomNum(1, 30) * 100;
        System.out.println("testMethod3 with " + timeProcess/1000.0 + " seconds " + " on thread: " + Thread.currentThread().getName());
        Thread.sleep(timeProcess);
        return "It works! This method3 was called " + count + " time(s)";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("count2/async")
    public void testMethod4(@Suspended final AsyncResponse asyncResponse) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    asyncResponse.resume(doTestMethod4());
                } catch (InterruptedException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });
    }

    private String doTestMethod4() throws InterruptedException {
        count = count + 1;
        long timeProcess = Utility.getRandomNum(1, 30) * 100;
        System.out.println("doTestMethod4 with " + timeProcess/1000.0 + " seconds " + " on thread: " + Thread.currentThread().getName());
        Thread.sleep(timeProcess);
        return "It works! This method4 async was called " + count + " time(s)";
    }
    
}


/*

Testear los request con Apache HTTP server benchmarking tool

instalacion
$ sudo apt-get install apache2-utils

$ ab -n1000 -c100 http://localhost:8084/sample-jaxrs/rest/test-no-singleton/count
$ ab -n1000 -c100 http://localhost:8084/sample-jaxrs/rest/test-no-singleton/count/async

$ ab -n100 -c100 http://localhost:8084/sample-jaxrs/rest/test-no-singleton/count2
$ ab -n100 -c100 http://localhost:8084/sample-jaxrs/rest/test-no-singleton/count2/async

el metodo async no es eficiente con un recurso no singleton
por cada request se crea una instancia del recurso y un ""executorService"
para este caso el "executorService" no es conveniente usarlo como propiedad del recurso
deberia pertenecer a otro componente externo como singleton

Con estas pruebas se muestra la mejora en usar async
~ 100 req/seg
$ ab -n1000 -c1000 http://localhost:8084/sample-jaxrs/rest/test-no-singleton/count2

~ 110 req/seg
$ ab -n2000 -c1000 http://localhost:8084/sample-jaxrs/rest/test-no-singleton/count2

~ 300 req/seg
$ ab -n1000 -c1000 http://localhost:8084/sample-jaxrs/rest/test-no-singleton/count2/async

~ 360 req/seg
$ ab -n2000 -c1000 http://localhost:8084/sample-jaxrs/rest/test-no-singleton/count2/async
*/
