package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;
import org.json.simple.JSONObject;

public class BoardPanel extends JPanel {
  final int width = 500;
  final int height = 500;
  
  BufferedImage board;
  
  
  public BoardPanel() {
    this.setBounds(10,10,this.width,this.height);
    this.setLayout(null);
    try {                
      board = ImageIO.read(new File("img/board.jpg"));
    } catch (IOException ex) {
      // handle exception...
    }
  }
  
  protected JSONObject getPlaceCoordinates(int placeId)
  {
    JSONObject coord = new JSONObject();
    int x, y;
    
    if(placeId <= 10) {
      x = board.getWidth()-80;
      y = 90*(placeId-1) + 90;
    } else if (placeId <= 20) {
      x = board.getWidth()-120-((placeId-11)*90);
      y = board.getHeight()-90;
    } else if (placeId <= 30) {
      x = 70;
      y = board.getHeight()-100-((placeId-21)*90);
    } else {
      x = 90+(placeId-31)*90;
      y = 65;
    }
    x *= (float)this.width/board.getWidth();
    y *= (float)this.height/board.getHeight();
    coord.put("x", x);
    coord.put("y", y);
    return coord;
  }
  
  @Override
  protected void paintComponent(Graphics g) {
      BufferedImage pin = null;
      super.paintComponent(g);
      g.drawImage(board, 0, 0,this.width,this.height, null);
      
      /* 
      try {
        pin = ImageIO.read(new File("img/blue_pin.png"));
      } catch (IOException ex) {
        
      }
      
      for(int i=1;i<41;i++) {
        JSONObject coord = this.getPlaceCoordinates(i);
        //System.out.println((int)coord.get("x"));
        g.drawImage(pin, (int)coord.get("x"), (int)coord.get("y"),15,24, null);
      }
      */
      
  }
  
}
