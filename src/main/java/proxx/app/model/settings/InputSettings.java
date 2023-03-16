package proxx.app.model.settings;

public class InputSettings {
  private int width;
  private int height;
  private int numberOfBlackHoles;

  public InputSettings(int width, int height, int numberOfBlackHoles) {
    this.width = width;
    this.height = height;
    this.numberOfBlackHoles = numberOfBlackHoles;
  }

  public static InputSettings of(GameMode gameMode) {
    return new InputSettings(gameMode.getWidth(), gameMode.getHeight(), gameMode.getNumberOfBlackHoles());
  }

  public static InputSettings of(int width, int height, int blackHoles) {
    return new InputSettings(width, height, blackHoles);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getNumberOfBlackHoles() {
    return numberOfBlackHoles;
  }
}
