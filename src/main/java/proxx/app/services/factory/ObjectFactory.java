package proxx.app.services.factory;

import proxx.app.model.board.GameBoard;
import proxx.app.model.board.PositionedHashMapBoard;
import proxx.app.services.board.BoardFacadeService;
import proxx.app.services.engine.GameEngine;
import proxx.app.services.engine.GameEngineImpl;
import proxx.app.services.notification.ConsoleLogNotificationService;
import proxx.app.services.notification.LifecycleNotificationService;

import java.util.logging.Logger;

/**
 * ObjectFactory class provides factory methods to get objects for the game.
 */
public class ObjectFactory {
  private ObjectFactory() {
  }

  private static final LifecycleNotificationService NOTIFICATION_SERVICE = new ConsoleLogNotificationService();
  private static final GameBoard GAME_BOARD = new PositionedHashMapBoard();
  private static final BoardFacadeService BOARD_FACADE_SERVICE = new BoardFacadeService(GAME_BOARD);
  private static final GameEngine GAME_ENGINE = new GameEngineImpl(BOARD_FACADE_SERVICE, NOTIFICATION_SERVICE);

  public static LifecycleNotificationService notificationService() {
    return NOTIFICATION_SERVICE;
  }

  public static GameEngine gameEngine() {
    return GAME_ENGINE;
  }

  public static Logger consoleLogger(Class classType) {
    return Logger.getLogger(classType.getName());
  }
}
