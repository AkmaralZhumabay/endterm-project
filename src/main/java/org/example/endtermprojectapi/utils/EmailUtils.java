package org.example.endtermprojectapi.utils;

public class EmailUtils {
    public static boolean isValid(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
