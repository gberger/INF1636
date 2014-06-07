package com.monopoly.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.monopoly.engine.core.*;
import com.monopoly.engine.cards.*;

public class BoardPanel extends JPanel {
  private static final long serialVersionUID = -6420464876258642413L;
  private final int width = 600;
  private final int height = 600;

  private List<Player> players;
  private BufferedImage boardImage;
  private Map<PlayerColor, BufferedImage> pinImages = new HashMap<PlayerColor, BufferedImage>();
  private BufferedImage houseIcon;

  private void initializeBoardImage() throws IOException {
    boardImage = ImageIO.read(new File("img/board.jpg"));
  }

  private void initializePinImages() throws IOException {
    for (PlayerColor color : PlayerColor.values()) {
      BufferedImage pin = ImageIO.read(new File("img/" + color.toString().toLowerCase() + "_pin.png"));
      pinImages.put(color, pin);
    }
  }
  
  private void initializeHouseIcon() throws IOException {
    houseIcon = ImageIO.read(new File("img/house_icon.png"));
  }

  public BoardPanel(List<Player> players) {
    this.setBounds(350,40,this.width,this.height);

    this.players = players;

    try {
      this.initializeBoardImage();
      this.initializePinImages();
      this.initializeHouseIcon();
    } catch (IOException e) {
      System.out.println("Unable to load images.");
      e.printStackTrace();
      System.exit(1);
    }
  }

  protected Map<String, Integer> getCoordinatesForPosition(int position, boolean housePosition) {
    Map<String, Integer> coords = new HashMap<String, Integer>();
    int x, y;

    if(position < 10) {
      x = boardImage.getWidth()-80;
      y = 90*(position) + 90;
      if(housePosition)
        x -= 85;
    } else if (position < 20) {
      x = boardImage.getWidth()-120-((position-10)*90);
      y = boardImage.getHeight()-90;
      if(housePosition)
        y -= 60;
    } else if (position < 30) {
      x = 70;
      y = boardImage.getHeight()-100-((position-20)*90);
      if(housePosition)
        x += 60;
    } else {
      x = 90+(position-30)*93;
      y = 65;
      if(housePosition)
        y += 60;
    }
    x *= (float)this.width/boardImage.getWidth();
    y *= (float)this.height/boardImage.getHeight();

    

    coords.put("x", x);
    coords.put("y", y);

    return coords;
  }

  protected Map<String, Integer> getCoordinatesForPlayer(Player player) {
    Map<String, Integer> coords = this.getCoordinatesForPosition(player.getPosition(), false);
    coords.put("x", coords.get("x") - player.getColor().ordinal() * 5);
    return coords;
  }

  @Override
  protected void paintComponent(Graphics g) {
    this.setBounds(350, 40, this.width, this.height);
    
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawImage(boardImage, 0, 0, this.width, this.height, null);

    Map<String, Integer> coords;
    for(Player player : players){
      BufferedImage pin = pinImages.get(player.getColor());
      coords = this.getCoordinatesForPlayer(player);
      g.drawImage(pin, coords.get("x"), coords.get("y"), 15, 24, null);
      
      g.setFont(g.getFont().deriveFont(Font.BOLD,14));
      for(Card card : player.getCards())
      {
        if(card instanceof TerrainCard && ((TerrainCard)card).getBuildings() > 0)
        {
          coords = this.getCoordinatesForPosition(card.getId(), true);
          g.drawImage(this.houseIcon, coords.get("x"), coords.get("y"), 16, 16, null);
          if(((TerrainCard)card).getBuildings() > 1)
            g.drawString("2", coords.get("x")-9, coords.get("y")+16);
        }
      }
      
    }
    
  }
}
