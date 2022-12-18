package com.test.dsa.util;

import lombok.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyClass {
    private String name;
    private int age;
    private long rage;
    private String role;

    public static List<String> getCommaSeparatedFields() {
        Field[] fields = DummyClass.class.getDeclaredFields();
        List<String> filedNames = new ArrayList<>();
        for(Field field : fields) {
            System.out.println(field.getName());
            filedNames.add(field.getName());
        }
        return filedNames;
    }

    public List<String> getCommaSeperatedValues() {
        Field[] fields = this.getClass().getDeclaredFields();
        List<String> filedNames = new ArrayList<>();
        for(Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.get(this));
                filedNames.add(String.valueOf(field.get(this)));
            } catch (IllegalAccessException illegalAccessException) {

            }
        }
        return filedNames;
    }
}
