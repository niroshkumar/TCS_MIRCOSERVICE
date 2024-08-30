package com.tcs.LibraryManagementSystem.utility;

public class JsonStringConvert {
    public static String formatedString(String value) {
        return String.format("{\"%s\":\"%s\"}", "message", value);
    }
}
