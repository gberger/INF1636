package com.monopoly.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.StyleSheet;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;


public class PlayerListPanel extends JPanel {
  private static final long serialVersionUID = -752755826147793469L;
  private Game game;
  private List<ShowCardsButton> showCardsButtonList;

  public PlayerListPanel(Game game) {
    this.game = game;
    this.showCardsButtonList = new ArrayList<ShowCardsButton>();
    this.setBounds(10,100,300,300);
    
    this.setLayout(null);
    
    for(int i=0;i<this.game.getPlayers().size();i++) {
      Player player = this.game.getPlayers().get(i);
      ShowCardsButton showCardsButton = new ShowCardsButton(player);
      showCardsButtonList.add(i,showCardsButton);
    }
  }
  
  public List<ShowCardsButton> getShowCardsButtonList() {
    return this.showCardsButtonList;
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    
    // Creates title border for this panel
    this.setBounds(10,100,320,45+27*this.game.getPlayers().size());
    TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)," JOGADORES ");
    title.setTitleFont(new Font("Arial",Font.PLAIN,20));
    title.setTitleJustification(TitledBorder.CENTER);
    this.setBorder(title);
    
    // Set anti-aliasing
    List<Player> players = game.getPlayers();
    Graphics2D g2 = (Graphics2D)g;
    RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2.setRenderingHints(rh);
    
    int y = 25;
    
    // Generates each element of the list
    for(int i=0;i<players.size();i++) {
      if(i!=0)
        g.drawLine(15, y, 300, y);
      
      y+=20;
      Player current = players.get(i);
      
      int fontStyle = Font.PLAIN;
      if(current == game.getCurrentPlayer()) {
        fontStyle = Font.BOLD;
      }
      if(current.isOutOfGame()) {
        fontStyle = Font.ITALIC;
      }
      g.setFont(new Font("Arial",fontStyle,16));
      StyleSheet ssheet = new StyleSheet();
      g.setColor(ssheet.stringToColor(current.getColor().toString()));

      g.drawString(current.getName(),20, y);
      g.drawString("$"+current.getBalance(),170, y);

      g.setColor(Color.BLACK);
      
      this.add(this.showCardsButtonList.get(i).setup(220, y-20));
      
      y+=10;   
    }
    
  }
  
  
}
