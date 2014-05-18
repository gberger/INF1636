package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import engine.*;



public class UI extends JFrame implements ActionListener {
  private Game game;
  private BoardPanel board;
  private PlayerListPanel playerList;
  private JButton dicesButton, movePinButton;
  
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
  
  public boolean askMessage (String message) {
    QuestionDialog dialog = new QuestionDialog(this, message);
    return dialog.getAnswer();
  }

  public void showMessage (String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  public void actionPerformed(ActionEvent e) {
    if ("rollDices".equals(e.getActionCommand())) {
      this.rollDices();
    } else if ("movePin".equals(e.getActionCommand())) {
      this.movePin();
    }
    this.repaint();
  } 
  
  private void rollDices() {
    this.dicesButton.setVisible(false);
    this.movePinButton.setVisible(true);
    game.getDices().roll();
    int[] dicesValues = game.getDices().getLastRoll();
    // TODO mostrar imagem de dados
    this.showMessage("Seus dados foram " + dicesValues[0] + " e " + dicesValues[1]);
  }
  
  private void movePin() {
    game.movePin();
    this.movePinButton.setVisible(false);
    this.dicesButton.setVisible(true);
    
    Square currSquare = game.getCurrentSquare();
    Player currPlayer = game.getCurrentPlayer();
    
    currSquare.affectLandingPlayer(game, currPlayer, this);
    
    game.nextTurn();
  }
}
