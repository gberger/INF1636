package com.monopoly.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.monopoly.engine.core.Game;

public class GameFrame extends JFrame {
  private static final long serialVersionUID = 2541117588135371641L;
  private List<JButton> buttons;
  
  public GameFrame() {
    this.setTitle("Monopoly");
    this.setSize(1000, 700);
    this.setVisible(true);
    this.setDefaultCloseOperation( EXIT_ON_CLOSE );

    this.getContentPane().setLayout(null);

    this.buttons = new ArrayList<JButton>();
    this.buttons.add(new DiceButton().setup());
    this.buttons.add(new PassTurnButton().setup());
    this.buttons.add(new JailPassButton().setup());
    this.buttons.add(new GoBankruptButton().setup());

    for(JButton button : buttons){
      this.getContentPane().add(button, -1);  
    }

    this.repaint();
    
  }
  
  public void bindGame(Game game){
    BoardPanel board = new BoardPanel(game.getPlayers());
    this.getContentPane().add(board, BorderLayout.CENTER);
    
    PlayerListPanel playerList = new PlayerListPanel(game);
    this.getContentPane().add(playerList, BorderLayout.LINE_START);
    
    this.buttons.addAll(playerList.getShowCardsButtonList());
    
    this.repaint();
  }
  
  public boolean askBoolean(String message, String title) {
    AskBooleanDialog dialog = new AskBooleanDialog(this, message, title);
    System.out.println("[Pergunta BOOL] " + title + " - " + message);
    boolean answer = dialog.getAnswer();
    System.out.println("[Resposta] " + answer);
    return answer;
  }

  public int askInt(String message, String title, int min, int max) {
    AskIntDialog dialog = new AskIntDialog(this, message, title, min, max);
    System.out.println("[Pergunta INT] " + title + " - " + message);
    int answer = dialog.getAnswer();
    System.out.println("[Resposta] " + answer);
    return answer;
  }
  
  public String askString(String message, String title) {
    AskStringDialog dialog = new AskStringDialog(this, message, title);
    System.out.println("[Pergunta STR] " + title + " - " + message);
    String answer = dialog.getAnswer();
    System.out.println("[Resposta] " + answer);
    return answer;
  }
  
  public Object askOptions(String message, String title, Object[] options) {
    AskOptionsDialog dialog = new AskOptionsDialog(this, message, title, options);
    System.out.println("[Pergunta OPT] " + title + " - " + message);
    Object answer = dialog.getAnswer();
    System.out.println("[Resposta] " + answer);
    return answer;
  }

  public void showMessage (String message, String title) {
    System.out.println("[Mensagem] " + title + " - " + message);
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  public void addListenerToButtons(ActionListener al) {
    for(JButton button : this.buttons) {
      button.addActionListener(al);
    }
  }
}
