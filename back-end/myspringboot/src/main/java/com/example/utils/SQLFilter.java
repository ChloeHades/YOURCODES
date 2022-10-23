package com.example.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * SQLfiltration
 *
 * @author
 */
public class SQLFilter {

    /**
     * SQLInjection filter
     *
     * @param str The string to be verified
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //Convert to lower case
        str = str.toLowerCase();

        //illegal character
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //Check whether invalid characters are included
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
               return "";
            }
        }

        return str;
    }
}
