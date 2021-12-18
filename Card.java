package ca.sheridancollege.project;

public class Card {

    private String cardName;

    public Card(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String toString() {
        String color = getCardName().substring(0, 1);
        String number = getCardName().substring(1);

        String cardCompleteName = "";

        switch (color) {
            case "r":
                cardCompleteName = "Red";
                break;

            case "y":
                cardCompleteName = "Yellow";
                break;

            case "g":
                cardCompleteName = "Green";
                break;

            case "b":
                cardCompleteName = "Blue";
                break;

            case "w":
                cardCompleteName = "Wild";
                break;
        }

        switch (number) {
            case "0":
                cardCompleteName = cardCompleteName + " 0";
                break;

            case "1":
                cardCompleteName = cardCompleteName + " 1";
                break;

            case "2":
                cardCompleteName = cardCompleteName + " 2";
                break;

            case "3":
                cardCompleteName = cardCompleteName + " 3";
                break;

            case "4":
                cardCompleteName = cardCompleteName + " 4";
                break;

            case "5":
                cardCompleteName = cardCompleteName + " 5";
                break;

            case "6":
                cardCompleteName = cardCompleteName + " 6";
                break;

            case "7":
                cardCompleteName = cardCompleteName + " 7";
                break;

            case "8":
                cardCompleteName = cardCompleteName + " 8";
                break;

            case "9":
                cardCompleteName = cardCompleteName + " 9";
                break;

            case "10":
                cardCompleteName = cardCompleteName + " Block";
                break;

            case "11":
                cardCompleteName = cardCompleteName + " Reverse";
                break;

            case "12":
                cardCompleteName = cardCompleteName + " Draw 2";
                break;

            case "20":
                cardCompleteName = cardCompleteName;
                break;

            case "24":
                cardCompleteName = cardCompleteName + " Draw 4";
                break;

        }

        return cardCompleteName;

    }
}
