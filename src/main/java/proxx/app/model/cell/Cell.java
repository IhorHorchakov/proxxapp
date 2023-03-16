package proxx.app.model.cell;

import java.util.ArrayList;
import java.util.List;

public class Cell {
  private CellStatus status;
  private CellType type;
  private List<Cell> adjacentCells;
  private int numberOfAdjacentBlackHoles;

  public Cell(CellStatus status, CellType type, List<Cell> adjacentCells, int numberOfAdjacentBlackHoles) {
    this.status = status;
    this.type = type;
    this.adjacentCells = adjacentCells;
    this.numberOfAdjacentBlackHoles = numberOfAdjacentBlackHoles;
  }

  public static Cell defaultCell() {
    return new Cell(CellStatus.HIDDEN, CellType.STANDARD, new ArrayList<>(), 0);
  }

  public CellStatus getStatus() {
    return status;
  }

  public void setStatus(CellStatus status) {
    this.status = status;
  }

  public boolean isHidden() {
    return status.isHidden();
  }

  public boolean isOpened() {
    return status.isOpened();
  }

  public CellType getType() {
    return type;
  }

  public void setType(CellType type) {
    this.type = type;
  }

  public boolean isStandard() {
    return type.isStandard();
  }

  public boolean isBlackHole() {
    return type.isBlackHole();
  }

  public int getNumberOfAdjacentBlackHoles() {
    return numberOfAdjacentBlackHoles;
  }

  public void setNumberOfAdjacentBlackHoles(int numberOfAdjacentBlackHoles) {
    this.numberOfAdjacentBlackHoles = numberOfAdjacentBlackHoles;
  }

  public List<Cell> getAdjacentCells() {
    return adjacentCells;
  }
}
