package com.ivy.bakingapp.utils;

public class TextUtils {

    public static String removeTrailingZero(String stringNumber) {
        return !stringNumber.contains(".") ? stringNumber :
                stringNumber.replaceAll("0*$", "").replaceAll("\\.$", "");
    }

    public static String capitalizeEachWords(String text) {
        String[] stringArray = text.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stringArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            stringBuilder.append(cap).append(" ");
        }
        return stringBuilder.toString();
    }

}
