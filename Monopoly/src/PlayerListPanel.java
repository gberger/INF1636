import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.text.html.StyleSheet;


public class PlayerListPanel extends JPanel {
  Game game;

  public PlayerListPanel(Game game) {
    this.game = game;
    this.setBounds(10,100,300,300);
    
    this.setLayout(null);
    
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    this.setBounds(10,100,300,300);
    //super.paintComponent(g);
    List<Player> players = game.getPlayers();
    Graphics2D g2 = (Graphics2D)g;
    RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2.setRenderingHints(rh);
    
    g.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
    g.drawString("           JOGADORES",20, 20);
    
    int y = 35;
    
    for(int i=0;i<players.size();i++) {
      g.drawLine(15, y, 300, y);
      y+=20;
      Player current = players.get(i);
      int fontStyle = Font.PLAIN;
      if(current == game.getCurrentPlayer())
        fontStyle = Font.BOLD;
      g.setFont(new Font("Comic Sans MS",fontStyle,16));
      StyleSheet ssheet = new StyleSheet();
      g.setColor(ssheet.stringToColor(current.getColor().toString()));
      g.drawString(current.getName()+" - $"+current.getMoney(),20, y);
      g.setColor(Color.BLACK);
      y+=10;   
    }
    
  }
  
  
}
