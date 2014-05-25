package com.monopoly.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.monopoly.engine.core.Player;
import com.monopoly.engine.core.PlayerColor;

public class BoardPanel extends JPanel {
  private static final long serialVersionUID = -6420464876258642413L;
  private final int width = 600;
  private final int height = 600;

  private List<Player> players;
  private BufferedImage boardImage;
  private Map<PlayerColor, BufferedImage> pinImages = new HashMap<PlayerColor, BufferedImage>();

  private void initializeBoardImage() throws IOException {
    boardImage = ImageIO.read(new File("img/board.jpg"));
  }

  private void initializePinImages() throws IOException {
    for (PlayerColor color : PlayerColor.values()) {
      BufferedImage pin = ImageIO.read(new File("img/" + color.toString().toLowerCase() + "_pin.png"));
      pinImages.put(color, pin);
    }
  }

  public BoardPanel(List<Player> players) {
    this.setBounds(350,40,this.width,this.height);

    this.players = players;

    try {
      this.initializeBoardImage();
      this.initializePinImages();
    } catch (IOException e) {
      System.out.println("Unable to load images.");
      e.printStackTrace();
      System.exit(1);
    }
  }

  protected Map<String, Integer> getCoordinatesForPositionAndColor(int position, PlayerColor color) {
    Map<String, Integer> coords = new HashMap<String, Integer>();
    int x, y;

    if(position < 10) {
      x = boardImage.getWidth()-80;
      y = 90*(position) + 90;
    } else if (position < 20) {
      x = boardImage.getWidth()-120-((position-10)*90);
      y = boardImage.getHeight()-90;
    } else if (position < 30) {
      x = 70;
      y = boardImage.getHeight()-100-((position-20)*90);
    } else {
      x = 90+(position-30)*90;
      y = 65;
    }
    x *= (float)this.width/boardImage.getWidth();
    y *= (float)this.height/boardImage.getHeight();

    x -= color.ordinal() * 5;

    coords.put("x", x);
    coords.put("y", y);

    return coords;
  }

  protected Map<String, Integer> getCoordinatesForPlayer(Player player) {
    return this.getCoordinatesForPositionAndColor(player.getPosition(), player.getColor());
  }

  @Override
  protected void paintComponent(Graphics g) {
    this.setBounds(350, 40, this.width, this.height);

    g.drawImage(boardImage, 0, 0, this.width, this.height, null);

    for(Player player : players){
      BufferedImage pin = pinImages.get(player.getColor());
      Map<String, Integer> coords = this.getCoordinatesForPlayer(player);
      g.drawImage(pin, coords.get("x"), coords.get("y"), 15, 24, null);
    }
  }
}
