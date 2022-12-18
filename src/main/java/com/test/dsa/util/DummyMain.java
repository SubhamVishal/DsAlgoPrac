package com.test.dsa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import java.util.TimeZone;

public class DummyMain {
    private String retryHandler(int retryCount) {
        try {
            throw new RuntimeException("He");
        } catch (Exception ex) {
            System.out.println("In catch block");
            if(retryCount > 1) {
                throw ex;
            }
        } finally {
            System.out.println("In finally block");
        }
        return "she";
    }
    private static int solve(int m, int n) {
        if(m > n) return m-n;
        int count = 0;
        while(n > m) {
            if( n % 2 == 0) {
                count++;
                n/=2;
            }else {
                count+=2;
                n/=2;
                n += 1;
            }
        }
        count += m-n;
        return count;
    }

    public static void main(String[] args) {
        /*String ab = "true";
        boolean cd = Boolean.parseBoolean(ab);
        System.out.println(ab);
        System.out.println(cd);
        System.out.println(DummyMain.solve(10, 23));
        *//*
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        String temporal = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(now);
        String fileName = LocalDate.now().format(DateTimeFormatter.ofPattern(temporal)) + "/Twitch-Duplicate.csv" ;
        System.out.println(fileName);

        System.out.println(DummyClass.getCommaSeparatedFields());
        DummyClass dc = new DummyClass();
        //dc.setName("Subham");
        //dc.setAge(12);
        //dc.setRage(10L);
        //dc.setRole(null);
        System.out.println(dc.getCommaSeperatedValues());
        Optional<DummyClass> dummyClassOpt = Optional.of(new DummyClass("Sv", 12, 11L, "Dev"));
        if(dummyClassOpt.isPresent()) {
            System.out.println("Hiiiiiii 1" +  dummyClassOpt.get());
            System.out.println("Hiiiiiii 2" +  dummyClassOpt.get());
        } else {
            System.out.println("Jaaaaaa");
        }*/
        //System.out.println(new DummyMain().retryHandler(3));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("GMT")));
        System.out.println(cal.toInstant());
        System.out.println(cal.toInstant().atZone(ZoneId.of("GMT")));
    }
}
