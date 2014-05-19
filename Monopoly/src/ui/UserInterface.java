package ui;

public interface UserInterface {
  public boolean askMessage (String message);
  public boolean askMessage (String message, String title);
  public void showMessage (String message);
  public void showMessage (String message, String title);
  public void diceWasRolled(int[] roll);
  public void repaint();
}
