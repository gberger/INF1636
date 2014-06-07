package com.monopoly.ui.cardlist;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import com.monopoly.engine.core.*;
import com.monopoly.engine.cards.*;
import com.monopoly.ui.UserInterfaceEvents;

public class CardListPane extends JPanel {
  private static final long serialVersionUID = 8840501986485230422L;
  protected Player player;
  protected ArrayList<JPanel> rows;
  protected ArrayList<CardListButton> buttons;


  public CardListPane(Player p, ArrayList<CardListButton> buttonsList) {
    this.player = p;  
    this.buttons = buttonsList;
    
    this.renderItems();
    this.setVisible(true);
    this.repaint();
  }
  
  protected void renderItems() {
    this.removeAll();
    this.buttons.clear();
    
    ArrayList<Card> cardList = (ArrayList<Card>) player.getCards();
    
    this.setSize(600,20*cardList.size());
    
    GridLayout layout = new GridLayout(cardList.size(),1);
    this.setLayout( layout );
    
    this.rows = new ArrayList<JPanel>();
    
    for(Card card : cardList)
    {
      if(card instanceof NegotiableCard)
      {
        JPanel row = new JPanel();
        row.setLayout( new BorderLayout() );
        
        JPanel row1 = new JPanel();
        row1.setSize(600,15);
        row1.setLayout( new FlowLayout());
        ((FlowLayout)row1.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout)row1.getLayout()).setHgap(20);
        
        NegotiableCard cardN = (NegotiableCard)card;
        JLabel nameLabel = new JLabel(cardN.getName());
        nameLabel.setSize(200,15);
        row1.add( nameLabel );
        
        if(cardN instanceof TerrainCard) {
          JLabel colorLabel = new JLabel( "Cor: " + ((TerrainCard)cardN).getColorName());
          colorLabel.setSize(40,15);
          row1.add(colorLabel);
          JLabel buildingsLabel = new JLabel( "(" + ((TerrainCard)cardN).getBuildings() + " imóveis)");
          buildingsLabel.setSize(40,15);
          row1.add(buildingsLabel);
        }
        
        JPanel row2 = new JPanel();
        row2.setSize(600,15);
        row2.setLayout( new FlowLayout());
        ((FlowLayout)row2.getLayout()).setHgap(2);
        

        CardListButton button;
        button = new CardListButton(cardN, UserInterfaceEvents.CARD_VIEW);
        this.buttons.add(button);
        row2.add( button );
        button = new CardListButton(cardN, UserInterfaceEvents.CARD_NEGOTIATE);
        this.buttons.add(button);
        row2.add( button );
        button = new CardListButton(cardN, UserInterfaceEvents.CARD_MORTGAGE);
        this.buttons.add(button);
        row2.add( button );
        button = new CardListButton(cardN, UserInterfaceEvents.CARD_NEW_BUILDING);
        this.buttons.add(button);
        row2.add( button );
        button = new CardListButton(cardN, UserInterfaceEvents.CARD_REMOVE_BULDING);
        this.buttons.add(button);
        row2.add( button );
        
        row2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        row.add(row1, BorderLayout.NORTH);
        row.add(row2, BorderLayout.CENTER);
        this.add(row);        
        
      }
    }
  }
  
  @Override
  public void paintComponents(Graphics g) {
    this.renderItems();
  }
}
