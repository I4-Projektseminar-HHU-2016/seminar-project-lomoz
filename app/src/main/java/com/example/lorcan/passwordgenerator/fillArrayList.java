package com.example.lorcan.passwordgenerator;

import java.util.ArrayList;

public class FillArrayList {

    private static String[] uppercaseStrings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static String[] lowercaseStrings = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static String[] numbersStrings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static String[] specialsStrings = {"!", "?", "@", "(", ")", "{", "}", "[", "]", "/", "=", "~", "$", "%", "&", "#", "*", "-"};

    /*
     * Method, which fills an ArrayList with Chars
     * equivalent to the CheckBoxes checked by the user.
     */
    public static void fillPasswordArrayListChars(ArrayList<String> passwordChars, boolean uppercaseOn, boolean lowercaseOn, boolean numbersOn, boolean specialsOn) {

        passwordChars.clear();

        if (uppercaseOn) {

            for (int i = 0; i < uppercaseStrings.length; i++) {

                passwordChars.add(uppercaseStrings[i]);
            }
        }

        if (lowercaseOn) {

            for (int i = 0; i < lowercaseStrings.length; i++) {

                passwordChars.add(lowercaseStrings[i]);
            }
        }

        if (numbersOn) {

            for (int i = 0; i < numbersStrings.length; i++) {

                passwordChars.add(numbersStrings[i]);
            }
        }

        if (specialsOn) {

            for (int i = 0; i < specialsStrings.length; i++) {

                passwordChars.add(specialsStrings[i]);
            }
        }

        System.out.println("\nArrayList with all chosen elements: " + passwordChars + "\n");

        int passwordCharsLength = passwordChars.size();

        System.out.println("Length of ArrayList with all chosen elements: " + passwordCharsLength + "\n");

        MainActivity.makePassword();
    }

}
