package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import engine.*;


public class GUI extends JFrame implements ActionListener, UserInterface {
  private static final long serialVersionUID = -3537670252267017863L;
  private Game game;
  private BoardPanel board;
  private PlayerListPanel playerList;
  private JButton dicesButton;
  
  public GUI(Game game) {
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
    
    this.board = new BoardPanel(game.getPlayers());
    
    this.playerList = new PlayerListPanel(game);
    
    this.getContentPane().add(this.dicesButton,FlowLayout.LEFT);
    this.getContentPane().add(this.board,BorderLayout.CENTER);
    this.getContentPane().add(this.playerList,BorderLayout.LINE_START);
    
    this.repaint();
  }

  public boolean askMessage (String message) {
    return this.askMessage(message, "Pergunta");
  }

  public boolean askMessage (String message, String title) {
    QuestionDialog dialog = new QuestionDialog(this, message, title);
    return dialog.getAnswer();
  }

  public void showMessage (String message) {
    this.showMessage(message, "Mensagem");
  }

  public void showMessage (String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  public void actionPerformed(ActionEvent e) {
    if ("rollDices".equals(e.getActionCommand())) {
      this.rollDices();
    }
    this.repaint();
  } 
  
  private void rollDices() {
    int[] dicesValues = game.getDices().roll();
    // TODO mostrar imagem de dados
    this.showMessage("Seus dados foram " + dicesValues[0] + " e " + dicesValues[1]);
    game.movePin(this);
    game.nextTurn();
  }
}
