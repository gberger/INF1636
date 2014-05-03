import java.awt.BorderLayout;

import javax.swing.*;

public class UI extends JFrame {
  Game game;
  
  public UI(Game game) {
    this.game = game;
    
    this.setTitle("Monopoly");
    this.setSize(1000, 700);
    this.setVisible(true);
    this.setDefaultCloseOperation( EXIT_ON_CLOSE );
    
    
    
    BoardPanel board = new BoardPanel(game.getPlayers());
    this.getContentPane().add(board,BorderLayout.CENTER);
    
    PlayerListPanel playerList = new PlayerListPanel(game);
    this.getContentPane().add(playerList,BorderLayout.LINE_START);
    
    this.repaint();
  }
}
