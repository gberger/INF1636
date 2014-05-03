import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

public class BoardPanel extends JPanel {
  final int width = 600;
  final int height = 600;
  
  BufferedImage board;
  List<Player> players;
  
  
  public BoardPanel(List<Player> players) {
    this.players = players;
    this.setBounds(350,40,this.width,this.height);
    this.setLayout(null);
    try {
      board = ImageIO.read(new File("img/board.jpg"));
    } catch (IOException ex) {
      System.out.println("Unable to load board image!");
    }
  }
  
  protected JSONObject getPlaceCoordinates(int placeId)
  {
    JSONObject coord = new JSONObject();
    int x, y;
    
    if(placeId < 10) {
      x = board.getWidth()-80;
      y = 90*(placeId) + 90;
    } else if (placeId < 20) {
      x = board.getWidth()-120-((placeId-10)*90);
      y = board.getHeight()-90;
    } else if (placeId < 30) {
      x = 70;
      y = board.getHeight()-100-((placeId-20)*90);
    } else {
      x = 90+(placeId-30)*90;
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
      
      
      int[] playerCountByPlace = new int[40];
      
      for(int i=0;i<this.players.size();i++) {
        Player current = players.get(i);
        try {
          pin = ImageIO.read(new File("img/"+current.getColor().toString().toLowerCase()+"_pin.png"));
        } catch (IOException ex) {
          System.out.println("Unable to load pin image!");
        }
        
        JSONObject coord = this.getPlaceCoordinates(current.getPosition());
        playerCountByPlace[current.getPosition()]++;
        g.drawImage(pin, (int)coord.get("x")-(playerCountByPlace[current.getPosition()]*5), (int)coord.get("y"),15,24, null);
      }
      
  }
  
}