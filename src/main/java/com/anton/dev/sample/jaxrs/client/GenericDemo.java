/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.client;

import com.anton.dev.sample.jaxrs.messenger.model.Message;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author anton
 */
public class GenericDemo {
    
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        
        List<Message> messages = client.target("http://localhost:8084/sample-jaxrs/rest")
                .path("messages")
                .queryParam("year", 2017)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Message>>() {});
        System.out.println("messages: " + messages);
    }
    
}
