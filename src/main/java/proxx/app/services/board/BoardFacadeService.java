package proxx.app.services.board;

import proxx.app.model.board.GameBoard;
import proxx.app.model.cell.Cell;
import proxx.app.model.cell.Position;
import proxx.app.model.settings.InputSettings;

import java.util.List;
import java.util.Random;

public class BoardFacadeService implements BoardService {
  private GameBoard gameBoard;

  public BoardFacadeService(GameBoard gameBoard) {
    this.gameBoard = gameBoard;
  }

  @Override
  public void initialize(InputSettings inputSettings, Position initialPosition) {
    gameBoard.initialize(inputSettings.getWidth(), inputSettings.getHeight());
    addAdjacentCellReferences(inputSettings);
    generateBlackHoles(inputSettings, initialPosition);
    calculateAdjacentBlackHolesNumber();
  }

  @Override
  public Cell getCell(Position position) {
    return gameBoard.getCell(position);
  }

  @Override
  public void openBoard() {
    gameBoard.openBoard();
  }

  @Override
  public void openAdjacentCells(Cell cell) {
    gameBoard.setOpenedStatus(cell);
    if (cell.getNumberOfAdjacentBlackHoles() == 0) {
      for (Cell adjacent : cell.getAdjacentCells()) {
        if (adjacent.isStandard() && adjacent.isHidden()) {
          openAdjacentCells(adjacent);
        }
      }
    }
  }

  @Override
  public boolean hasAllStandardCellsOpened() {
    return gameBoard.getCells().stream().filter(Cell::isStandard).allMatch(Cell::isOpened);
  }

  private void addAdjacentCellReferences(InputSettings inputSettings) {
    for (int width = 0; width < inputSettings.getWidth(); width++) {
      for (int height = 0; height < inputSettings.getHeight(); height++) {
        Position position = Position.of(width, height);
        Cell cell = getCell(position);
        /* Add references to the top adjacent cells */
        addIgnoreNull(cell.getAdjacentCells(), getCell(Position.of(width - 1, height + 1)));
        addIgnoreNull(cell.getAdjacentCells(), getCell(Position.of(width, height + 1)));
        addIgnoreNull(cell.getAdjacentCells(), getCell(Position.of(width + 1, height + 1)));
        /* Add references to the middle adjacent cells */
        addIgnoreNull(cell.getAdjacentCells(), getCell(Position.of(width - 1, height)));
        addIgnoreNull(cell.getAdjacentCells(), getCell(Position.of(width + 1, height)));
        /* Add references to the bottom adjacent cells */
        addIgnoreNull(cell.getAdjacentCells(), getCell(Position.of(width - 1, height - 1)));
        addIgnoreNull(cell.getAdjacentCells(), getCell(Position.of(width, height - 1)));
        addIgnoreNull(cell.getAdjacentCells(), getCell(Position.of(width + 1, height - 1)));
      }
    }
  }

  private void generateBlackHoles(InputSettings inputSettings, Position initialPosition) {
    Random random = new Random();
    for (int blackHoleIndex = 0; blackHoleIndex < inputSettings.getNumberOfBlackHoles(); blackHoleIndex++) {
      Position randomBlackHolePosition = Position.of(
              random.nextInt(inputSettings.getWidth()),
              random.nextInt(inputSettings.getHeight())
      );
      Cell cellForBlackHole = getCell(randomBlackHolePosition);
      if (cellForBlackHole.isBlackHole() || cellForBlackHole.getAdjacentCells().contains(getCell(initialPosition)) || initialPosition.equals(randomBlackHolePosition)) {
        blackHoleIndex--;
      } else {
        gameBoard.setBlackHoleType(cellForBlackHole);
      }
    }
  }

  private void calculateAdjacentBlackHolesNumber() {
    gameBoard.getCells().forEach(cell -> {
      if (cell.isStandard()) {
        int numberOfAdjacentBlackHoles = (int) cell.getAdjacentCells().stream().filter(Cell::isBlackHole).count();
        cell.setNumberOfAdjacentBlackHoles(numberOfAdjacentBlackHoles);
      }
    });
  }

  private void addIgnoreNull(List<Cell> adjacentCells, Cell cell) {
    if (cell != null) {
      adjacentCells.add(cell);
    }
  }
}
