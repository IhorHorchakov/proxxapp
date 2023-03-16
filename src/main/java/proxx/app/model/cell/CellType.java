package proxx.app.model.cell;

public enum CellType {
  STANDARD,
  BLACK_HOLE;

  public boolean isStandard() {
    return this == STANDARD;
  }

  public boolean isBlackHole() {
    return this == BLACK_HOLE;
  }
}
