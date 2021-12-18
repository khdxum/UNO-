package ca.sheridancollege.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    public DataValidator() {
    }

    public boolean isIDValid(String id) {
        if (id.trim().length() > 0 && id.trim() != "$") {
            return true;
        }
        return false;
    }

    public int isCardNumberValid(String cardNumber, int size) {
        Pattern p = Pattern.compile("[0-9][0-9]?[0-9]?");
        Matcher m = p.matcher(cardNumber);

        if (m.matches()) {
            int number = Integer.parseInt(cardNumber);

            if (number == 100) {
                // Terminate game
                return 100;

            }

            if (number == 200) {
                //Draw a card
                return 200;
            }

            if (number >= 0 && number < size) {
                // Correct input
                return 101;
            }

        }

        // Incorrect input
        return 102;
    }

    public boolean isColorIndexValid(String input) {
        Pattern p = Pattern.compile("[0123]");
        Matcher m = p.matcher(input);

        if (m.matches()) {
            return true;
        }

        return false;
    }

}
