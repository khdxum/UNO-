package ca.sheridancollege.project;

import java.util.Scanner;

public class UnoIO {

    private DataValidator validator = new DataValidator();

    private Scanner scanner = new Scanner(System.in);

    public UnoIO() {

    }

    public void showWelcome() {
        System.out.println("Welcome");
    }

    public String setPlayersIDs() {
        String input = "$";
        String player1ID = "";
        String player2ID = "";

        do {
            System.out.println("You are player 1. Please enter your ID");
            input = scanner.nextLine();

            if (!validator.isIDValid(input)) {
                System.out.println("Please enter a valid ID");
                input = "$";
            }

            player1ID = input.trim();

        } while (input == "$");

        input = "$";

        do {
            System.out.println("You are player 2. Please enter your ID");
            input = scanner.nextLine();

            if (!validator.isIDValid(input)) {
                System.out.println("Please enter a valid ID");
                input = "$";
            }

            player2ID = input.trim();

        } while (input == "$");

        return player1ID + "$" + player2ID;
    }

    public void showPlayerTurn(String playerID) {
        System.out.println("This is " + playerID + " turn.");
    }

    public void showLastCard(String cardName) {
        System.out.println("The last card is " + cardName + ".");
    }

    public void showPlayerCards(String[] cardsList) {
        System.out.println("Please enter the card index");

        for (int i = 0; i < cardsList.length; i++) {
            System.out.println(i + "." + cardsList[i]);
        }

    }

    public int getPlayerInput(String[] cardsList) {
        String input = "$";
        int status = 0;

        showExitCharacters();

        do {
            input = scanner.nextLine();
            status = validator.isCardNumberValid(input, cardsList.length);

            if (status == 100) {
                return 100;
            }
            if (status == 200) {
                return 200;
            }

            if (status == 102) {
                input = "$";
                System.out.println("Please enter a correct number.");
            }

        } while (input == "$");

        return Integer.parseInt(input);
    }

    public void showExitCharacters() {
        System.out.println("200.Draw a card.");
        System.out.println("100.Terminate game.");
    }

    public void showWrongCardWarning() {
        System.out.println("Please choose a correct card.");
    }

    public void showBlockCardNotification(String playerID) {
        System.out.println(playerID + " played a block card. "
                + "It's getting interesting. Kooool.");
    }

    public void showReverseCardNotification(String playerID) {
        System.out.println(playerID + " reversed the turn. "
                + "YAAAAYYYY.");
    }

    public void showDraw2CardNotification(String playerID) {
        System.out.println(playerID + " just made you to pick extra cards,"
                + " but here is no need to pick the extra 2 cards."
                + " I already added them to your hand. It's less painful. I know.");
    }

    public String wildCardPlayed(String playerID) {
        String input = "$";
        boolean status = false;

        do {
            System.out.println("Please choose color index.");
            System.out.println("0.Red");
            System.out.println("1.Yellow");
            System.out.println("2.Green");
            System.out.println("3.Blue");

            input = scanner.nextLine();
            status = validator.isColorIndexValid(input);

            if (!status) {
                input = "$";
                System.out.println("Please enter a correct number.");
            }
        } while (input == "$");

        if (Integer.parseInt(input) == 0) {
            return "r";
        }
        if (Integer.parseInt(input) == 1) {
            return "y";
        }
        if (Integer.parseInt(input) == 2) {
            return "g";
        }

        return "b";
    }

    public void showWildCardNotification(String playerId, String color) {
        String currentColor = "";

        switch (color) {
            case "r":
                currentColor = "Red";
                break;
            case "y":
                currentColor = "Yellow";
                break;
            case "g":
                currentColor = "Green";
                break;
            case "b":
                currentColor = "Blue";
                break;
        }

        System.out.println(playerId + " played a wildcard. ");
        System.out.println("The color changed to " + currentColor);

    }

    public void showWild4CardNotification(String playerId, String color) {
        String currentColor = "";

        switch (color) {
            case "r":
                currentColor = "Red";
                break;
            case "y":
                currentColor = "Yellow";
                break;
            case "g":
                currentColor = "Green";
                break;
            case "b":
                currentColor = "Blue";
                break;
        }

        System.out.println(playerId + " played a wild draw 4 card. ");
        System.out.println("The color changed to " + currentColor);
        System.out.println("And, the opponent is BUUURRRRNIIINNNNNGGGG ;))))");

    }

    public void showScores(String playerId, int score) {
        System.out.println("Ladies and gentelemen. I introduce you to the winner:");
        System.out.println(playerId + " with " + score + " scores.");
    }

    public void showDrawNotification(String playerId){
        System.out.println(playerId + " drew a card");
    }
}