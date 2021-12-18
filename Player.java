package ca.sheridancollege.project;


import java.util.ArrayList;

public class Player {

    private String id;
    private ArrayList<Card> cards = new ArrayList<>();

    public Player(String id) {
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getSizeOfCards() {
        return cards.size();
    }

}
