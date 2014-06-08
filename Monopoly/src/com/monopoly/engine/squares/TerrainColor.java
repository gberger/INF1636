package com.monopoly.engine.squares;


public enum TerrainColor {
  PINK,
  LIGHT_BLUE,
  PURPLE,
  ORANGE,
  RED,
  YELLOW,
  GREEN,
  DARK_BLUE;

  public String getName() {
    if(this == TerrainColor.PINK) {
      return "Rosa";
    } else if(this == TerrainColor.LIGHT_BLUE) {
      return "Azul Claro";
    } else if(this == TerrainColor.PURPLE) {
      return "Roxo";
    } else if(this == TerrainColor.ORANGE) {
      return "Laranja";
    } else if(this == TerrainColor.RED) {
      return "Vermelho";
    } else if(this == TerrainColor.YELLOW) {
      return "Amarelo";
    } else if(this == TerrainColor.GREEN) {
      return "Verde";
    } else if(this == TerrainColor.DARK_BLUE) {
      return "Azul Escuro";
    }
    return "";
  }
}
