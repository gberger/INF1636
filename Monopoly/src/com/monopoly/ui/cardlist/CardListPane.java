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

  public CardListPane(Player p) {
    this.player = p;
    //this.setPreferredSize(new Dimension(300,300));
    //this.setSize(300, 300);
    
    
    this.renderItems();
    
    
    this.setVisible(true);
    this.repaint();
  }
  
  protected void renderItems() {
    this.removeAll();
    
    ArrayList<Card> cardList = (ArrayList<Card>) player.getCards();
    
    this.setSize(600,20*cardList.size());
    
    GridLayout layout = new GridLayout(cardList.size(),1);
    //layout.setHgap(0);
    this.setLayout( layout );
    
    this.rows = new ArrayList<JPanel>();
    
    //layout.
    
    for(Card card : cardList)
    {
      if(card instanceof PropertyCard)
      {
        JPanel row = new JPanel();
        row.setLayout( new BorderLayout() );
        
        JPanel row1 = new JPanel();
        row1.setSize(600,15);
        row1.setLayout( new FlowLayout());
        ((FlowLayout)row1.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout)row1.getLayout()).setHgap(20);
        
        PropertyCard property = (PropertyCard)card;
        JLabel nameLabel = new JLabel(property.getName());
        nameLabel.setSize(200,15);
        row1.add( nameLabel );
        
        if(property instanceof TerrainCard)
        {
          JLabel buildingsLabel = new JLabel( "(" + ((TerrainCard)property).getBuildings() + " imóveis)");
          buildingsLabel.setSize(40,15);
          row1.add(buildingsLabel);
        }
        
        JPanel row2 = new JPanel();
        row2.setSize(600,15);
        row2.setLayout( new FlowLayout());
        ((FlowLayout)row2.getLayout()).setHgap(2);
        
        row2.add( new CardListButton(property, UserInterfaceEvents.CARD_VIEW));
        row2.add( new CardListButton(property, UserInterfaceEvents.CARD_NEGOCIATE));
        row2.add( new CardListButton(property, UserInterfaceEvents.CARD_MORTGAGE));
        row2.add( new CardListButton(property, UserInterfaceEvents.CARD_NEW_BUILDING));
        row2.add( new CardListButton(property, UserInterfaceEvents.CARD_PULVERIZE_BULDING));
        
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
