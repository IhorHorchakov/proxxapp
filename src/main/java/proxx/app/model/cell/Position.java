package proxx.app.model.cell;

import java.util.Objects;

public class Position {
  private int width;
  private int height;

  public Position(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public static Position of(int width, int height) {
    return new Position(width, height);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Position)) return false;
    Position position = (Position) o;
    return width == position.width &&
            height == position.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }

  @Override
  public String toString() {
    return String.format("{width=%d, height=%d}", width, height);
  }
}
