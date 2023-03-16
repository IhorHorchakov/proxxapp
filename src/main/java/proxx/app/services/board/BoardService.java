package proxx.app.services.board;

import proxx.app.model.cell.Cell;
import proxx.app.model.cell.Position;
import proxx.app.model.settings.InputSettings;

/**
 * The BoardService provides a layer for business logic to work with the underlying game board.
 *
 * @see InputSettings
 * @see Cell
 * @see Position
 */
public interface BoardService {

  /**
   * Performs initialization of the board service with the given input settings at stat position.
   */
  void initialize(InputSettings inputSettings, Position startPosition);

  /**
   * Opens all the cells of game board.
   */
  void openBoard();

  /**
   * Retrieves the cell by it's position.
   *
   * @param position - position of the user's click.
   */
  Cell getCell(Position position);

  /**
   * Opens the given cell with with it's adjacent cells if needed.
   *
   * @param cell - cell to be opened together with it's adjacent cells.
   */
  void openAdjacentCells(Cell cell);

  /**
   * Checks if all the standard cells (status = 'STANDARD') are opened (type = 'OPENED').
   * Returns true if all the standard cells are opened.
   */
  boolean hasAllStandardCellsOpened();
}
