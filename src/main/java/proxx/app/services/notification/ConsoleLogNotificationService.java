package proxx.app.services.notification;

import proxx.app.services.factory.ObjectFactory;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogNotificationService implements LifecycleNotificationService {
  private static final Logger logger = ObjectFactory.consoleLogger(ConsoleLogNotificationService.class);


  @Override
  public void notifyStart() {
    logger.log(Level.INFO, "The game has started. " + new Date());
  }

  @Override
  public void notifyWin() {
    logger.log(Level.INFO, "You win! " + new Date());
  }

  @Override
  public void notifyLoose() {
    logger.log(Level.INFO, "You lost! " + new Date());
  }

  @Override
  public void notifyError(String cause) {
    logger.log(Level.WARNING, cause);
  }
}
