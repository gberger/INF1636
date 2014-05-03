import javax.swing.*;

import ui.*;

public class UI extends JFrame {
  public UI() {
    this.setTitle("Monopoly");
    this.setSize(500, 500);
    this.setVisible(true);
    setDefaultCloseOperation( EXIT_ON_CLOSE );
    
    BoardPanel board = new BoardPanel();
    this.getContentPane().add(board);
    this.repaint();
  }
}
