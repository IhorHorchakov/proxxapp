package proxx.app.services;

import proxx.app.model.cell.Position;
import proxx.app.model.settings.InputSettings;
import proxx.app.services.engine.GameEngine;
import proxx.app.services.factory.ObjectFactory;
import proxx.app.services.notification.LifecycleNotificationService;
import proxx.app.services.validator.InputSettingsValidator;
import proxx.app.services.validator.PositionValidator;

/**
 * The Processor is the entry point for handling user's click on a cell at some position.
 * A position should has coordinates inside the board, otherwise such a position will be considered as invalid.
 * The GameEngine helps to process a click on a valid position, and updates the state of the game board.
 * The LifecycleNotificationService enables to notify the user of some event happened in the game.
 *
 * @see GameEngine
 * @see LifecycleNotificationService
 */
public class Processor {
  private GameEngine gameEngine;
  private LifecycleNotificationService notificationService;
  private InputSettings inputSettings;
  private boolean initialized = false;

  public Processor(InputSettings inputSettings) {
    this.gameEngine = ObjectFactory.gameEngine();
    this.notificationService = ObjectFactory.notificationService();
    this.inputSettings = inputSettings;
  }

  public void processClick(Position position) {
    try {
      preProcess(position);
      process(position);
    } catch (Exception exception) {
      processError(position, exception);
    }
  }

  private void preProcess(Position position) {
    if (!initialized) {
      InputSettingsValidator.validate(inputSettings);
      gameEngine.initialize(inputSettings, position);
      initialized = true;
    }
    PositionValidator.validate(inputSettings, position);
  }

  private void process(Position position) {
    gameEngine.onClick(position);
  }

  private void processError(Position position, Exception exception) {
    String errorMessage = String.format("An error occurred while handling position %s. Cause: %s", position, exception.getCause());
    notificationService.notifyError(errorMessage);
  }
}
