package com.monopoly.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame {
  private static final long serialVersionUID = 2541117588135371641L;
  private List<JButton> buttons;
  
  public GameFrame(BoardPanel board, PlayerListPanel playerList) {
    this.setTitle("Monopoly");
    this.setSize(1000, 700);
    this.setVisible(true);
    this.setDefaultCloseOperation( EXIT_ON_CLOSE );

    this.getContentPane().setLayout(null);
    this.getContentPane().add(board, BorderLayout.CENTER);
    this.getContentPane().add(playerList, BorderLayout.LINE_START);

    this.buttons = new ArrayList<JButton>();
    this.buttons.add(new DiceButton().setup());
    this.buttons.add(new PassTurnButton().setup());
    this.buttons.add(new JailPassButton().setup());

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
  
  public String inputMessage(String message, String title) {
    InputDialog dialog = new InputDialog(this, message, title);
    System.out.println("[Pergunta] " + title + " - " + message);
    String answer = dialog.getAnswer();
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
