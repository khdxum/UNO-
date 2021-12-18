package ca.sheridancollege.project;

public class Logic {

    public static final String WRONG_CARD = "WRONG_CARD";
    public static final String CONTINUE = "CONTINUE";
    public static final String REVERSE = "REVERSE";
    public static final String BLOCK = "BLOCK";
    public static final String DRAW2 = "DRAW2";
    public static final String WILD4 = "WILD4";
    public static final String WILD = "WILD";

    public Logic() {
    }

    public String handleLogic(Card playerCard, Card LastCard) {
        String playerCardNameFirstPart
                = playerCard.getCardName().substring(0, 1);
        String lastCardNameFirstPart
                = LastCard.getCardName().substring(0, 1);
        String playerCardNameSecondPart
                = playerCard.getCardName().substring(1);
        String lastCardNameSecondPart
                = LastCard.getCardName().substring(1);

        if (playerCardNameFirstPart.equals("w")) {
            return wildCardPlayed(playerCardNameSecondPart);
        } else if (lastCardNameSecondPart.equals(playerCardNameSecondPart)) {
            return sameNumberCards(playerCardNameFirstPart,
                    lastCardNameFirstPart);
        } else if (lastCardNameFirstPart.equals(playerCardNameFirstPart)) {
            return sameColorCards(playerCardNameSecondPart,
                    lastCardNameSecondPart);
        }
        return WRONG_CARD;
    }

    private String sameNumberCards(String playerCardNameFirstPart,
            String lastCardNameFirstPart) {
        switch (playerCardNameFirstPart) {
            case "10":
                return BLOCK;

            case "11":
                return REVERSE;

            case "12":
                return DRAW2;
        }

        return CONTINUE;
    }

    private String sameColorCards(String playerCardNameFirstPart,
            String lastCardNameFirstPart) {
        switch (playerCardNameFirstPart) {
            case "10":
                return BLOCK;

            case "11":
                return REVERSE;

            case "12":
                return DRAW2;
        }

        return CONTINUE;
    }

    private String wildCardPlayed(String playerCardNameSecondPart) {
        if (playerCardNameSecondPart.equals("20")) {
            return WILD;
        }
        return WILD4;
    }

}
