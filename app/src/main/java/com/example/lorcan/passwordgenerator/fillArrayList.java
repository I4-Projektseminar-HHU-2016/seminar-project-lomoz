package com.example.lorcan.passwordgenerator;

import java.util.ArrayList;

/**
 * Created by lorcan on 13.09.16.
 */
public class FillArrayList {

    /*
     * Funktion, die eine ArrayList je nach gewählten Einstellungen
     * der Checkboxes mit den entsprechenden Chars füllt.
     */

    public static void fillPasswordArrayListChars(ArrayList<String> passwordChars, boolean uppercaseOn, String[] uppercaseStrings, boolean lowercaseOn, String[] lowercaseStrings, boolean numbersOn, String[] numbersStrings, boolean specialsOn, String[] specialsStrings, int passwordCharsLength) {

        passwordChars.clear();

        if (uppercaseOn == true) {

            for (int i = 0; i < uppercaseStrings.length; i++) {

                passwordChars.add(uppercaseStrings[i]);
            }
        }

        if (lowercaseOn == true) {

            for (int i = 0; i < lowercaseStrings.length; i++) {

                passwordChars.add(lowercaseStrings[i]);
            }
        }

        if (numbersOn == true) {

            for (int i = 0; i < numbersStrings.length; i++) {

                passwordChars.add(numbersStrings[i]);
            }
        }

        if (specialsOn == true) {

            for (int i = 0; i < specialsStrings.length; i++) {

                passwordChars.add(specialsStrings[i]);
            }
        }

        System.out.println("\nArrayList mit allen ausgewählten Elementen: " + passwordChars + "\n");

        passwordCharsLength = passwordChars.size();

        System.out.println("Länge der ArrayList mit allen ausgewählten Elementen: " + passwordCharsLength + "\n");

        MainActivity.makePassword();
    }

}
