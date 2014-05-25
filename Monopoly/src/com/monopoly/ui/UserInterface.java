package com.monopoly.ui;

public interface UserInterface {
  public boolean askMessage (String message);
  public boolean askMessage (String message, String title);
  public void showMessage (String message);
  public void showMessage (String message, String title);
  public void repaint();
}
