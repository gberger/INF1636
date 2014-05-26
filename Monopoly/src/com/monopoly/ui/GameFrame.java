package com.monopoly.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame {
  private static final long serialVersionUID = 2541117588135371641L;
  
  public GameFrame(BoardPanel board, PlayerListPanel playerList, List<JButton> buttons) {
    this.setTitle("Monopoly");
    this.setSize(1000, 700);
    this.setVisible(true);
    this.setDefaultCloseOperation( EXIT_ON_CLOSE );
    
    this.getContentPane().setLayout(null);
    this.getContentPane().add(board, BorderLayout.CENTER);
    this.getContentPane().add(playerList, BorderLayout.LINE_START);
    
    for(JButton button : buttons){
      this.getContentPane().add(button, -1);  
    }
    
    this.repaint();
  }
  
  public boolean askMessage(String message, String title) {
    QuestionDialog dialog = new QuestionDialog(this, message, title);
    System.out.println("[Pergunta] " + title + " - " + message);
    boolean answer = dialog.getAnswer();
    System.out.println("[Resposta] " + answer);
    return answer;
  }

  public void showMessage (String message, String title) {
    System.out.println("[Mensagem] " + title + " - " + message);
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  }
}
