package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;


public abstract class Square {
  protected Game game;
  protected int id;

  public static Square fromType(String type, JSONObject jobj, Game game) {    
    if(type.equals("chance"))        { return new ChanceSquare(jobj, game); }
    else if(type.equals("company"))  { return new CompanySquare(jobj, game); }
    else if(type.equals("free"))     { return new FreeSquare(jobj, game); }
    else if(type.equals("goToJail")) { return new GoToJailSquare(jobj, game); }
    else if(type.equals("irs"))      { return new IrsSquare(jobj, game); }
    else if(type.equals("jail"))     { return new JailSquare(jobj, game); }
    else if(type.equals("profits"))  { return new ProfitsSquare(jobj, game); }
    else if(type.equals("start"))    { return new StartSquare(jobj, game); }
    else if(type.equals("terrain"))  { return new TerrainSquare(jobj, game); }

    return null;
  }

  public int getId() {
    return this.id;
  }

  public int getPosition() {
    return this.id;
  }

  public abstract void affectLandingPlayer(Player player);

  public abstract void affectPassingPlayer(Player player);

}
