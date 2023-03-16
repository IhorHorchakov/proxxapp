package proxx.app.model.board;

import proxx.app.model.cell.Cell;
import proxx.app.model.cell.CellStatus;
import proxx.app.model.cell.CellType;
import proxx.app.model.cell.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of the game board based on hash map.
 * Position serves as key and Cell is a value.
 *
 * @see GameBoard
 * @see Position
 * @see Cell
 * @see HashMap
 */
public class PositionedHashMapBoard implements GameBoard {
  private Map<Position, Cell> gameBoard;

  @Override
  public void initialize(int width, int height) {
    this.gameBoard = new HashMap<>(width * height);
    for (int widthIndex = 0; widthIndex < width; widthIndex++) {
      for (int heightIndex = 0; heightIndex < height; heightIndex++) {
        gameBoard.put(Position.of(widthIndex, heightIndex), Cell.defaultCell());
      }
    }
  }

  @Override
  public Cell setOpenedStatus(Cell cell) {
    cell.setStatus(CellStatus.OPENED);
    return cell;
  }

  @Override
  public Cell setBlackHoleType(Cell cell) {
    cell.setType(CellType.BLACK_HOLE);
    return cell;
  }

  @Override
  public void openBoard() {
    gameBoard.values().forEach(this::setOpenedStatus);
  }

  @Override
  public Cell getCell(Position position) {
    return gameBoard.get(position);
  }

  @Override
  public Set<Cell> getCells() {
    return new HashSet<>(gameBoard.values());
  }
}
