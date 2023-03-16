package proxx.app.services.engine;

import org.junit.Test;
import org.mockito.Mockito;
import proxx.app.model.cell.Cell;
import proxx.app.model.cell.CellStatus;
import proxx.app.model.cell.CellType;
import proxx.app.model.cell.Position;
import proxx.app.model.settings.GameMode;
import proxx.app.model.settings.InputSettings;
import proxx.app.services.board.BoardService;
import proxx.app.services.notification.LifecycleNotificationService;

import java.util.ArrayList;


public class GameEngineTest {
  private BoardService boardServiceMock = Mockito.mock(BoardService.class);
  private LifecycleNotificationService notificationServiceMock = Mockito.mock(LifecycleNotificationService.class);
  private GameEngine gameEngine = new GameEngineImpl(boardServiceMock, notificationServiceMock);

  @Test
  public void shouldPassInitialization() {
    // given
    InputSettings inputSettings = InputSettings.of(GameMode.EASY);
    Position startPosition = Position.of(0, 0);
    Mockito.doNothing().when(boardServiceMock).initialize(inputSettings, startPosition);
    Mockito.doNothing().when(notificationServiceMock).notifyStart();

    // when
    gameEngine.initialize(inputSettings, startPosition);

    // then
    Mockito.verify(boardServiceMock, Mockito.times(1)).initialize(inputSettings, startPosition);
    Mockito.verify(notificationServiceMock, Mockito.times(1)).notifyStart();
  }

  @Test
  public void shouldWinOnClick() {
    // given
    Position clickedPosition = Position.of(0, 0);
    Cell clickedCell = Cell.defaultCell();
    Mockito.when(boardServiceMock.getCell(clickedPosition)).thenReturn(clickedCell);
    Mockito.doNothing().when(boardServiceMock).openAdjacentCells(clickedCell);
    Mockito.when(boardServiceMock.hasAllStandardCellsOpened()).thenReturn(true);
    Mockito.doNothing().when(boardServiceMock).openBoard();
    Mockito.doNothing().when(notificationServiceMock).notifyWin();

    // when
    gameEngine.onClick(clickedPosition);

    // then
    Mockito.verify(notificationServiceMock, Mockito.times(1)).notifyWin();
    Mockito.verify(boardServiceMock, Mockito.times(1)).openBoard();
  }

  @Test
  public void shouldLooseOnClick() {
    // given
    Position clickedPosition = Position.of(0, 0);
    Cell clickedCell = new Cell(CellStatus.HIDDEN, CellType.BLACK_HOLE, new ArrayList<>(), 0);
    Mockito.when(boardServiceMock.getCell(clickedPosition)).thenReturn(clickedCell);
    Mockito.doNothing().when(notificationServiceMock).notifyLoose();

    // when
    gameEngine.onClick(clickedPosition);

    // then
    Mockito.verify(notificationServiceMock, Mockito.times(1)).notifyLoose();
    Mockito.verify(boardServiceMock, Mockito.times(1)).openBoard();
  }

  @Test
  public void shouldContinueOnClick() {
    // given
    Position clickedPosition = Position.of(0, 0);
    Cell clickedCell = Cell.defaultCell();
    Mockito.when(boardServiceMock.getCell(clickedPosition)).thenReturn(clickedCell);
    Mockito.doNothing().when(boardServiceMock).openAdjacentCells(clickedCell);
    Mockito.when(boardServiceMock.hasAllStandardCellsOpened()).thenReturn(false);

    // when
    gameEngine.onClick(clickedPosition);

    // then
    Mockito.verify(notificationServiceMock, Mockito.times(0)).notifyWin();
    Mockito.verify(notificationServiceMock, Mockito.times(0)).notifyLoose();
    Mockito.verify(boardServiceMock, Mockito.times(0)).openBoard();
  }
}
