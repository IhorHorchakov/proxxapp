package proxx.app.services.engine;

import proxx.app.model.cell.Cell;
import proxx.app.model.cell.Position;
import proxx.app.model.settings.InputSettings;
import proxx.app.services.board.BoardService;
import proxx.app.services.notification.LifecycleNotificationService;

public class GameEngineImpl implements GameEngine {
  private BoardService boardService;
  private LifecycleNotificationService notificationService;

  public GameEngineImpl(BoardService boardService, LifecycleNotificationService notificationService) {
    this.boardService = boardService;
    this.notificationService = notificationService;
  }

  @Override
  public void initialize(InputSettings inputSettings, Position startPosition) {
    boardService.initialize(inputSettings, startPosition);
    notificationService.notifyStart();
  }

  @Override
  public void onClick(Position position) {
    Cell cell = boardService.getCell(position);
    if (cell.isHidden()) {
      if (cell.isBlackHole()) {
        notificationService.notifyLoose();
        boardService.openBoard();
      } else {
        boardService.openAdjacentCells(cell);
        if (boardService.hasAllStandardCellsOpened()) {
          notificationService.notifyWin();
          boardService.openBoard();
        }
      }
    }
  }
}
