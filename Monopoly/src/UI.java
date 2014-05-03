import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

import javax.swing.*;



public class UI extends JFrame implements ActionListener {
  Game game;
  BoardPanel board;
  PlayerListPanel playerList;
  JButton dicesButton, movePinButton;
  
  public UI(Game game) {
    this.game = game;
    
    this.setTitle("Monopoly");
    this.setSize(1000, 700);
    this.setVisible(true);
    this.setDefaultCloseOperation( EXIT_ON_CLOSE );
    
    // Roll dices button
    ImageIcon diceIcon = new ImageIcon("img/dice_icon.png");
    Image img = diceIcon.getImage() ;  
    Image newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;  
    diceIcon = new ImageIcon( newimg );
    
    this.dicesButton = new JButton("Rolar dados", diceIcon);
    this.dicesButton.setActionCommand("rollDices");
    this.dicesButton.setBounds(10,40,120,40);
    this.dicesButton.addActionListener(this);
    
    //
    
    // Move pin button 
    this.movePinButton = new JButton("Mover pinos", null);
    this.movePinButton.setActionCommand("movePin");
    this.movePinButton.setBounds(10,40,120,40);
    this.movePinButton.addActionListener(this);
    
    this.movePinButton.setVisible(false);
    //
    
    this.board = new BoardPanel(game.getPlayers());
    
    this.playerList = new PlayerListPanel(game);
    
    this.getContentPane().add(this.dicesButton,FlowLayout.LEFT);
    this.getContentPane().add(this.movePinButton,FlowLayout.LEFT);
    this.getContentPane().add(this.board,BorderLayout.CENTER);
    this.getContentPane().add(this.playerList,BorderLayout.LINE_START);
    
    this.repaint();
  }
  
  public void actionPerformed(ActionEvent e) {
    if ("rollDices".equals(e.getActionCommand())) {
      this.dicesButton.setVisible(false);
      this.movePinButton.setVisible(true);
      game.getDices().roll();
      int[] dicesValues = game.getDices().getLastRoll();
      JOptionPane.showMessageDialog(null, "Seus dados foram "+dicesValues[0]+" e "+dicesValues[1]);
      
    } else {
      this.movePinButton.setVisible(false);
      this.dicesButton.setVisible(true);
      game.runTurn();
      
    }
    this.repaint();
  } 
  

  
}
