/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.converter;

import com.anton.dev.sample.jaxrs.model.MyDate;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author anton
 */
@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.getName().equals(MyDate.class.getName())) {
            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    Calendar reqDate = Calendar.getInstance();
                    switch (value) {
                        case "tomorrow":
                            reqDate.add(Calendar.DATE, 1);
                            break;
                        case "yesterday":
                            reqDate.add(Calendar.DATE, -1);
                            break;
                    }
                    MyDate mydate = new MyDate();
                    mydate.setDate(reqDate.get(Calendar.DATE));
                    mydate.setMonth(reqDate.get(Calendar.MONTH));
                    mydate.setYear(reqDate.get(Calendar.YEAR));

                    return rawType.cast(mydate);
                }

                @Override
                public String toString(T myBean) {
                    if (myBean == null) {
                        return null;
                    }
                    return myBean.toString();
                }
            };
        }
        return null;
    }

}
