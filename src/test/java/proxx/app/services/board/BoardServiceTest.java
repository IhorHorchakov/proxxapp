package proxx.app.services.board;

import org.junit.Test;
import org.mockito.Mockito;
import proxx.app.model.board.GameBoard;
import proxx.app.model.board.PositionedHashMapBoard;
import proxx.app.model.cell.Cell;
import proxx.app.model.cell.Position;
import proxx.app.model.settings.InputSettings;

import static org.junit.Assert.assertEquals;


public class BoardServiceTest {
  private GameBoard gameBoardSpy = Mockito.spy(PositionedHashMapBoard.class);
  private BoardService boardService = new BoardFacadeService(gameBoardSpy);

  @Test
  public void should_initialize_board() {
    // given
    InputSettings inputSettings = InputSettings.of(5, 5, 1);
    Position position = Position.of(0, 0);

    // when
    boardService.initialize(inputSettings, position);

    // then
    Mockito.verify(gameBoardSpy, Mockito.times(1)).initialize(inputSettings.getWidth(), inputSettings.getHeight());
    assertEquals(1, calculateNumberOfBlackHoles());
  }

  @Test
  public void should_open_board() {
    // given
    InputSettings inputSettings = InputSettings.of(5, 5, 1);
    Position position = Position.of(0, 0);
    boardService.initialize(inputSettings, position);

    // when
    boardService.openBoard();

    // then
    Mockito.verify(gameBoardSpy, Mockito.times(1)).initialize(inputSettings.getWidth(), inputSettings.getHeight());
    assertEquals(25, calculateNumberOfOpenedCells());
  }

  private long calculateNumberOfBlackHoles() {
    return gameBoardSpy.getCells().stream().filter(Cell::isBlackHole).count();
  }

  private long calculateNumberOfOpenedCells() {
    return gameBoardSpy.getCells().stream().filter(Cell::isOpened).count();
  }
}
