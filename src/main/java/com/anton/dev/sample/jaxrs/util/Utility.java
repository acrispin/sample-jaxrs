/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ACRISPIN
 */
public final class Utility {
    
    /**
     *
     */
    protected  Utility() {     
    }

    /**
     * Convierte una fecha de la clase Date a string con el formato dd/MM/yyyy
     * @param date fecha a cambiar.
     * @return la fecha date como {@link String} en el formato dd/MM/yyyy.
     */
    public static String dateToString(Date date) {
        // Date input = new Date(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(date);
        return fecha;
    }

    /**
     * Convierte una fecha de string con el formato dd/MM/yyyy a la clase Date
     * @param date fecha a cambiar.
     * @return la fecha date como {@link Date}. El parámetro date debe estar en
     * el formato dd/MM/yyyy.
     * @throws ParseException si el String no puede ser transformado.
     */
    public static Date stringToDate(String date) throws ParseException {
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = formatter.parse(date);
        return fecha;
    }

    /**
     * Suma dias a un objeto de clase Date
     * @param date fecha a cambiar.
     * @param dias cantidad de dias a incrementar a la fecha pasada como
     * parámetro.
     * @return fecha incrementada en una cantidad igual a dias, si dias es
     * negativo se resta a la fecha original.
     */
    public static Date addDays(Date date, short dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, dias);
        return cal.getTime();
    }

    /**
     * Suma meses a un objeto de clase Date
     * @param date fecha a cambiar.
     * @param meses cantidad de meses a incrementar a la fecha pasada como
     * parámetro.
     * @return fecha incrementada en una cantidad igual a meses, si meses es
     * negativo se resta a la fecha original.
     */
    public static Date addMonths(Date date, short meses) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, meses);
        return cal.getTime();
    }
    
    
    /**
     *
     * @param cad String
     * @return boolean
     * null o vacia
     */
    public static boolean isNullOrEmpty(String cad) {
        return cad == null || cad.isEmpty();
    }

    /**
     *
     * @param cad String
     * @return boolean
     * null o vacia o si solo contiene espacios en blanco
     */
    public static boolean isNullOrWhiteSpace(String cad) {
        return cad == null || cad.trim().length() == 0;
    }

    /**
     *
     * @param str String
     * @return boolean
     */
    public static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param email String
     * @return boolean
     */
    public static boolean validateEmail(String email) {
        String cadPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(cadPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public static int getRandomNum(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    public static int getRandomNum2(int min, int max){
        return min + (int) (Math.random() * max);
    }

}