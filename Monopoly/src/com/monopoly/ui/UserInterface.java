package com.monopoly.ui;

import com.monopoly.engine.core.Game;

public interface UserInterface {
  public boolean askBoolean (String message);
  public boolean askBoolean (String message, String title);
  public int askInt (String message, int max);
  public int askInt (String message, String title, int max);
  public int askInt (String message, int min, int max);
  public int askInt (String message, String title, int min, int max);
  public String askString (String message);
  public String askString (String message, String title);
  public void showMessage (String message);
  public void showMessage (String message, String title);
  public void bindGame(Game game);
}
