package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
  BufferedImage board;
  
  public BoardPanel() {
    this.setBounds(10,10,300,300);
    this.setLayout(null);
    try {                
      board = ImageIO.read(new File("img/board.png"));
    } catch (IOException ex) {
      // handle exception...
    }
  }
  
  @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(board, 0, 0,300, 300, null);            
  }
  
}
