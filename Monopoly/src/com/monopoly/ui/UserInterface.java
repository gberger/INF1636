package com.monopoly.ui;

public interface UserInterface {
  public boolean askBoolean (String message);
  public boolean askBoolean (String message, String title);
  public String askString (String message);
  public String askString (String message, String title);
  public void showMessage (String message);
  public void showMessage (String message, String title);
}
