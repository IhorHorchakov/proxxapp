package proxx.app.model.cell;

public enum CellStatus {
  OPENED,
  HIDDEN;

  public boolean isOpened() {
    return this == OPENED;
  }

  public boolean isHidden() {
    return this == HIDDEN;
  }
}
