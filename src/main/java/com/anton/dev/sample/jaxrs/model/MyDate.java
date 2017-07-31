/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.model;

/**
 *
 * @author anton
 */
public class MyDate {
	
    private int date;
    private int month;
    private int year;

    public int getDate() {
            return date;
    }
    public void setDate(int date) {
            this.date = date;
    }
    public int getMonth() {
            return month;
    }
    public void setMonth(int month) {
            this.month = month;
    }
    public int getYear() {
            return year;
    }
    public void setYear(int year) {
            this.year = year;
    }

    @Override
    public String toString() {
            StringBuilder sb = new StringBuilder(super.toString());
            sb.append(" [")
              .append("date=").append(date)
              .append(", month=").append(month)
              .append(", year=").append(year)
              .append("]");
            return sb.toString();
    }
    
}
