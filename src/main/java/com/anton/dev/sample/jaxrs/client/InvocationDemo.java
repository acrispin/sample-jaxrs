/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author anton
 */
public class InvocationDemo {
    
    public static void main(String[] args) {
        InvocationDemo demo = new InvocationDemo();
        Invocation inv = demo.prepareRequestForMsgByYear(2017);
        Response res = inv.invoke();
        System.out.println("response status: " + res.getStatus());
    }

    private Invocation prepareRequestForMsgByYear(int year) {
        Client client = ClientBuilder.newClient();
        
        return client.target("http://localhost:8084/sample-jaxrs/rest")
                     .path("messages")
                     .queryParam("year", year)
                     .request(MediaType.APPLICATION_JSON)
                     .buildGet();
    }
    
}
