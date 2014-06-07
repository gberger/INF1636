package com.monopoly.ui.cardlist;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.monopoly.engine.core.Player;

public class CardListFrame extends JFrame {
  private static final long serialVersionUID = 3295284813173053174L;
  private CardListPane content;
  protected ArrayList<CardListButton> buttons;
  protected JScrollPane scrollPane;

  public CardListFrame( Player p ) {
    this.setTitle("Cartas de " + p.getName());
    this.setSize(620,600);
    this.buttons = new ArrayList<CardListButton>();
    
    this.content = new CardListPane(p,this.buttons);
    

    JPanel topPanel = new JPanel();
    topPanel.setLayout( new FlowLayout() );
    
    topPanel.add( this.content );

    this.scrollPane = new JScrollPane(topPanel);
    this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    this.scrollPane.getViewport().setVisible(true);
    getContentPane().add( this.scrollPane, BorderLayout.CENTER );
    
    this.setVisible(true);
    
  }
  
  public void addListenerToButtons(ActionListener al)
  {
    for(CardListButton button : this.buttons)
      button.addActionListener(al);
  }
  
  public void update() {
    this.content.renderItems();
    this.scrollPane.updateUI();
  }
  
}
