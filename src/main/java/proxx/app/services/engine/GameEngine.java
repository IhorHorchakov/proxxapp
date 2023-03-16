package proxx.app.services.engine;

import proxx.app.model.cell.Position;
import proxx.app.model.settings.InputSettings;

/**
 * The GameEngine handles user's click and updates the state of the game board accordingly.
 * GameEngine needs to be initialized first with the input settings,
 * and with the start position that user clicks on at the start of the game.
 *
 * @see Position
 * @see InputSettings
 */
public interface GameEngine {

  /**
   * Performs initialization of the underlying game engine
   *
   * @param inputSettings - input setting of the game
   * @param startPosition - position the user starts the game with
   */
  void initialize(InputSettings inputSettings, Position startPosition);

  /**
   * Handles user's click and updates the state of the game board accordingly
   *
   * @param position - position a user starts the game with
   */
  void onClick(Position position);
}
