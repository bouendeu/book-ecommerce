package com.itbcafrica.repository;

/**
 * Created by OEM on 22.09.2019.
 */
public class TextUtil {
    public String sanitize(String textToSanitize){
        return  textToSanitize.replaceAll("\\s+"," ");
    }
}
