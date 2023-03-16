package proxx.app.model.settings;

public enum GameMode {
  EASY(8, 8, 10),
  MEDIUM(16, 16, 40),
  HARD(24, 24, 99);

  int width;
  int height;
  int numberOfBlackHoles;

  GameMode(int width, int height, int numberOfBlackHoles) {
    this.width = width;
    this.height = height;
    this.numberOfBlackHoles = numberOfBlackHoles;
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
