package proxx.app.model.board;

import proxx.app.model.cell.Cell;
import proxx.app.model.cell.Position;

import java.util.Set;

/**
 * The root interface for the game board.
 * Implementations could use various data structures to represent the board in memory: maps, 2d arrays, lists and so on.
 */
public interface GameBoard {

  /**
   * Performs initialization of the width x height board.
   */
  void initialize(int width, int height);

  /**
   * Opens all the cells of the board.
   */
  void openBoard();

  /**
   * Opens the given cell.
   */
  Cell setOpenedStatus(Cell cell);

  /**
   * Make the given cell to be a black hole.
   */
  Cell setBlackHoleType(Cell cell);

  /**
   * Retrieves a cell by the given position.
   */
  Cell getCell(Position position);

  /**
   * Retrieves all the cells.
   */
  Set<Cell> getCells();

}
