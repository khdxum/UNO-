package ca.sheridancollege.project;


import java.util.ArrayList;
import java.util.Collections;

public class CardManager {

    private static CardManager cardManager = new CardManager();
    private ArrayList<Card> drawPile = new ArrayList<Card>();

    private CardManager() {
        initDrawPile();
        shuffleDrawPile();
    }

    private void initDrawPile() {
        drawPile.add(new Card("r0"));
        drawPile.add(new Card("y0"));
        drawPile.add(new Card("g0"));
        drawPile.add(new Card("b0"));

        for (int i = 0; i < 4; i++) {
            drawPile.add(new Card("w20"));
            drawPile.add(new Card("w24"));
        }

        for (int i = 1; i < 13; i++) {
            drawPile.add(new Card("r" + i));
            drawPile.add(new Card("r" + i));

            drawPile.add(new Card("y" + i));
            drawPile.add(new Card("y" + i));

            drawPile.add(new Card("g" + i));
            drawPile.add(new Card("g" + i));

            drawPile.add(new Card("b" + i));
            drawPile.add(new Card("b" + i));
        }
    }

    private void shuffleDrawPile() {
        Collections.shuffle(drawPile);
    }
    
    public Card giveCard(){
        return drawPile.remove(0);
    }
    
    public void putCard(Card card){
        drawPile.add(card);
    }
    
    public String lastCard(){
        return drawPile.get(drawPile.size() - 1).toString();
    }
    
    public Card getLastCard(){
        return drawPile.get(drawPile.size() - 1);
    }

    public static CardManager getInstance() {
        return cardManager;
    }
}

