package com.sachin.finalproject.to;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        String id = "D00009";
        String startName = "D";
        if (id != null) {
            String[] newId = id.split(startName);
            int addId = Integer.parseInt(newId[1]);
            addId++;
            String result = String.format(startName + "%05d", addId);
            System.out.println(id);
            System.out.println(result);
        }
    }
}
