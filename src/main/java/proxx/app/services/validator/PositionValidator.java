package proxx.app.services.validator;

import proxx.app.model.cell.Position;
import proxx.app.model.settings.InputSettings;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static java.lang.String.format;

/**
 * Validator for the position of user's click in a scope of input settings.
 *
 * @see InputSettings
 */
public class PositionValidator {

  /**
   * The rule to check whether the given position is inside the game board.
   */
  private static final BiConsumer<InputSettings, Position> CHECK_POSITION_IS_INSIDE_THE_BOARD_RULE = (inputSettings, position) -> {
    if (position.getWidth() < 0 || position.getHeight() < 0) {
      String errorMessage = format("Wrong input position: the position of user's click (%s) has negative properties", position);
      throw new IllegalArgumentException(errorMessage);
    }
    if (position.getWidth() * position.getHeight() > inputSettings.getWidth() * inputSettings.getHeight()) {
      String errorMessage = format("Wrong input position: the position of user's click (%s) is outside the game board", position);
      throw new IllegalArgumentException(errorMessage);
    }
  };
  /**
   * The rule to check whether the position object and settings object are not null.
   */
  private static BiConsumer<InputSettings, Position> CHECK_IS_NOT_NULL_RULE = (inputSettings, position) -> {
    if (inputSettings == null) {
      throw new IllegalArgumentException("Wrong input settings: the settings must not be null");
    }
    if (position == null) {
      throw new IllegalArgumentException("Wrong input position: the position must not be null");
    }
  };

  private static final List<BiConsumer<InputSettings, Position>> RULES = Arrays.asList(
          CHECK_IS_NOT_NULL_RULE,
          CHECK_POSITION_IS_INSIDE_THE_BOARD_RULE
  );

  public static void validate(InputSettings inputSettings, Position position) {
    RULES.forEach(rule -> rule.accept(inputSettings, position));
  }
}
