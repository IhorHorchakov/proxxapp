package proxx.app.services.notification;

/**
 * LifecycleNotificationService helps to keep a user updated about what happens in the application.
 * It has methods to describe a lifecycle flow of the game and to track an errors.
 */
public interface LifecycleNotificationService {

  /**
   * Notifies when the game has been started.
   */
  void notifyStart();

  /**
   * Notifies when the game has completed with the win.
   */
  void notifyWin();

  /**
   * Notifies when the game has completed with the loose.
   */
  void notifyLoose();

  /**
   * Notifies when the game has completed with the error.
   */
  void notifyError(String cause);
}
