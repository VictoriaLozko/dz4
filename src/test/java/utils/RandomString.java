package utils;

import java.util.Random;

public class RandomString {

    public static String getRandomString() {
        String STRING_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 18) { // length of the random string.
            int index = (int)((rnd.nextFloat() * STRING_CHARS.length())%35);
            str.append(STRING_CHARS.charAt(index));
        }
        String string = str.toString();
        return string;

    }
}
