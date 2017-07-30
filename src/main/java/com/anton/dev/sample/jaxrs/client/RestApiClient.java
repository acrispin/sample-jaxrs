/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.client;

import com.anton.dev.sample.jaxrs.messenger.model.Message;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author anton
 */
public class RestApiClient {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        
        Response res = client.target("http://localhost:8084/sample-jaxrs/rest/messages/1")
                             .request()
                             .get();
        Message msg = res.readEntity(Message.class);
        System.out.println("response: " + res);
        System.out.println("message: " + msg.getMessage());
        
        Message msg2 = client.target("http://localhost:8084/sample-jaxrs/rest/messages/2")
                             .request(MediaType.APPLICATION_JSON)
                             .get(Message.class);
        System.out.println("message2: " + msg2.getMessage());
        
        String msg3 = client.target("http://localhost:8084/sample-jaxrs/rest/messages/2")
                             .request(MediaType.APPLICATION_JSON)
                             .get(String.class);
        System.out.println("message3: " + msg3);
        
        // best practice
        WebTarget baseTarget = client.target("http://localhost:8084/sample-jaxrs/rest");
        WebTarget msgTarget = baseTarget.path("messages");
        WebTarget singleMsgTarget = msgTarget.path("{messageId}");
        
        Message msg4 = singleMsgTarget.resolveTemplate("messageId", "1")
                                      .request(MediaType.APPLICATION_JSON)
                                      .get(Message.class);
        System.out.println("message4: " + msg4.getMessage());
        
        // make post
        Message msg5 = new Message(10, "Nuevo mensaje desde cliente", "test");
        Response postRes = msgTarget.request()
                                    .post(Entity.json(msg5));
        if (postRes.getStatus() == 201) {
            Message createdMsg = postRes.readEntity(Message.class);
            System.out.println("Created message: " + createdMsg.getMessage());
        } else {
            System.out.println("Error to created message.");
        }
    }
}
