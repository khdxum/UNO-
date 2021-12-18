package ca.sheridancollege.project;

import java.util.ArrayList;

public class UnoManager {

    public static final String FINISH = "FINISH";

    private static UnoManager unoManager = new UnoManager();

    private CardManager cardManager = CardManager.getInstance();

    private Logic logic = new Logic();

    private UnoIO menu = new UnoIO();

    private Player player1;
    private Player player2;

    private int currentPlayer = 1;
    private String currentColor = "";
    private boolean wildCardPlayed = false;

    private UnoManager() {
        initGame();

    }

    public static UnoManager getInstance() {
        if (unoManager == null) {
            return unoManager = new UnoManager();
        }
        return unoManager;
    }

    private void initGame() {

        menu.showWelcome();
        setPlayersIDs();
        setPlayersCards();
        setTheFirstCard();
        beginGame();
    }

    private void setPlayersIDs() {
        String[] IDs = menu.setPlayersIDs().split("\\$");

        setPlayer1ID(IDs[0]);
        setPlayer2ID(IDs[1]);

    }

    private void setPlayersCards() {
        for (int i = 0; i < 7; i++) {
            getPlayer1().getCards().add(cardManager.giveCard());
            getPlayer2().getCards().add(cardManager.giveCard());
        }

    }

    private void setTheFirstCard() {
        Card card;
        String cardNameFisrtPart = "";
        String cardNameSecondPart = "";
        boolean isPlayable = false;
        do {
            card = cardManager.getLastCard();
            cardNameFisrtPart = card.getCardName().substring(0, 1);
            cardNameSecondPart = card.getCardName().substring(1);

            if (!cardNameFisrtPart.equals("w")
                    && !cardNameSecondPart.equals("10")
                    && !cardNameSecondPart.equals("11")
                    && !cardNameSecondPart.equals("12")) {
                isPlayable = true;
            } else {
                isPlayable = false;
                cardManager.putCard(cardManager.giveCard());
            }
        } while (!isPlayable);

        menu.showLastCard(card.toString());
    }

    private void beginGame() {
        int playerInput = -1;
        String state = "";
        do {
            menu.showPlayerTurn(getCurrentPlayer().getId());
            if (!state.equals("") && !wildCardPlayed) {
                menu.showLastCard(cardManager.lastCard());
            }

            menu.showPlayerCards(listToArray(getCurrentPlayer().getCards()));
            playerInput = menu.getPlayerInput(listToArray(getCurrentPlayer().getCards()));

            if (playerInput == 100) {
                state = FINISH;
                gameFinished();
                break;
            }

            if (playerInput == 200) {
                getCurrentPlayer().getCards().add(cardManager.giveCard());
                state = "DRAW";
                handleState(state, playerInput);
                continue;
            }

            Card playerCard = getCurrentPlayer().getCards().get(playerInput);

            if (!wildCardPlayed) {
                state = logic.handleLogic(playerCard, cardManager.getLastCard());
            } else {
                state = logic.handleLogic(playerCard, new Card(currentColor + "200"));
            }

            System.out.println(state);
            handleState(state, playerInput);

        } while (true);

    }

    private void handleState(String state, int playerInput) {
        switch (state) {
            case "DRAW":
                menu.showDrawNotification(getCurrentPlayer().getId());
                changeCurrentPlayer();
                break;

            case Logic.CONTINUE:
                wildCardPlayed = false;
                cardManager.putCard(getCurrentPlayer().getCards()
                        .remove(playerInput));
                changeCurrentPlayer();
                break;

            case Logic.WRONG_CARD:
                menu.showWrongCardWarning();
                break;

            case Logic.BLOCK:
                wildCardPlayed = false;
                menu.showBlockCardNotification(getCurrentPlayer().getId());
                cardManager.putCard(getCurrentPlayer().getCards()
                        .remove(playerInput));
                break;

            case Logic.REVERSE:
                wildCardPlayed = false;
                menu.showReverseCardNotification(getCurrentPlayer().getId());
                cardManager.putCard(getCurrentPlayer().getCards()
                        .remove(playerInput));
                break;

            case Logic.DRAW2:
                wildCardPlayed = false;
                menu.showDraw2CardNotification(getCurrentPlayer().getId());
                cardManager.putCard(getCurrentPlayer().getCards()
                        .remove(playerInput));
                changeCurrentPlayer();
                getCurrentPlayer().getCards().add(cardManager.giveCard());
                getCurrentPlayer().getCards().add(cardManager.giveCard());
                break;

            case Logic.WILD:
                wildCardPlayed = true;
                cardManager.putCard(getCurrentPlayer().getCards()
                        .remove(playerInput));
                currentColor = menu.wildCardPlayed(getCurrentPlayer().getId());
                menu.showWildCardNotification(getCurrentPlayer().getId(),
                        currentColor);
                changeCurrentPlayer();
                break;

            case Logic.WILD4:
                wildCardPlayed = true;
                cardManager.putCard(getCurrentPlayer().getCards()
                        .remove(playerInput));
                currentColor = menu.wildCardPlayed(getCurrentPlayer().getId());
                menu.showWild4CardNotification(getCurrentPlayer().getId(), currentColor);
                changeCurrentPlayer();
                getCurrentPlayer().getCards().add(cardManager.giveCard());
                getCurrentPlayer().getCards().add(cardManager.giveCard());
                getCurrentPlayer().getCards().add(cardManager.giveCard());
                getCurrentPlayer().getCards().add(cardManager.giveCard());
                changeCurrentPlayer();
                break;
        }

        if (getCurrentPlayer().getSizeOfCards() == 0) {
            showScores();
        }
    }

    private void showScores() {
        changeCurrentPlayer();
        String playerId = getCurrentPlayer().getId();
        changeCurrentPlayer();
        menu.showScores(playerId, calculateScore(getCurrentPlayer()));
        changeCurrentPlayer();
    }

    private int calculateScore(Player player) {
        int score = 0;
        for (Card card : getCurrentPlayer().getCards()) {
            score += Integer.parseInt(card.getCardName().substring(1));
        }

        return score;
    }

    private void gameFinished() {
        System.out.println("Game terminated by " + getCurrentPlayer().getId());
        showScores();
    }

    private String[] listToArray(ArrayList list) {
        String[] playerCardsArray = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            playerCardsArray[i] = list.get(i).toString();
        }

        return playerCardsArray;
    }

    public Player getPlayer1() {
        return player1;
    }

    private void setPlayer1ID(String id) {
        this.player1 = new Player(id);
    }

    public Player getPlayer2() {
        return player2;
    }

    private void setPlayer2ID(String id) {
        this.player2 = new Player(id);
    }

    private Player getCurrentPlayer() {
        if (currentPlayer == 1) {
            return getPlayer1();
        }
        return getPlayer2();
    }

    private void setCurrentPlayer(int num) {
        if (num == 1) {
            currentPlayer = 1;
        } else {
            currentPlayer = 2;
        }
    }

    private void changeCurrentPlayer() {
        if (currentPlayer == 1) {
            setCurrentPlayer(2);
        } else {
            setCurrentPlayer(1);
        }
    }

}
