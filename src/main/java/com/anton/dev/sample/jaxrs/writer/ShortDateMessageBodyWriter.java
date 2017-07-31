/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author anton
 */
@Provider
@Produces("text/shortdate")
public class ShortDateMessageBodyWriter implements MessageBodyWriter<Date> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Date.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Date t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Date date, 
            Class<?> type, 
            Type genericType, 
            Annotation[] annotations, 
            MediaType mediaType, 
            MultivaluedMap<String, Object> httpHeaders, 
            OutputStream out) throws IOException, WebApplicationException {
        String shortdate = date.getDate() + "-" + date.getMonth() + "-" + date.getYear();
        out.write(shortdate.getBytes());
    }
    
}
