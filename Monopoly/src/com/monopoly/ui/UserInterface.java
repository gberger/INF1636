package com.monopoly.ui;

public interface UserInterface {
  public boolean askMessage (String message);
  public boolean askMessage (String message, String title);
  public String inputMessage (String message);
  public String inputMessage (String message, String title);
  public void showMessage (String message);
  public void showMessage (String message, String title);
}
