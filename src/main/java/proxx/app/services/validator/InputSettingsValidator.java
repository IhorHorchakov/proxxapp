package proxx.app.services.validator;

import proxx.app.model.settings.InputSettings;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.String.format;
import static proxx.app.ApplicationProperties.MAX_HEIGHT;
import static proxx.app.ApplicationProperties.MAX_WIDTH;
import static proxx.app.ApplicationProperties.MIN_HEIGHT;
import static proxx.app.ApplicationProperties.MIN_NUMBER_OF_BLACK_HOLES;
import static proxx.app.ApplicationProperties.MIN_WIDTH;

/**
 * Validator for the input settings of the game.
 *
 * @see InputSettings
 */
public class InputSettingsValidator {

  /**
   * The rule to check whether the settings object is not null.
   */
  private static Consumer<InputSettings> CHECK_IS_NOT_NULL_RULE = settings -> {
    if (settings == null) {
      throw new IllegalArgumentException("Wrong input settings: the settings must not be null");
    }
  };

  /**
   * The rule to check whether the width, height, and number of black holes are greater
   * than MIN params from ApplicationProperties.
   */
  private static Consumer<InputSettings> CHECK_MINIMUM_ALLOWED_PROPERTIES_RULE = settings -> {
    if (settings.getWidth() < MIN_WIDTH) {
      String errorMessage = format("Wrong input settings: the width %d less than minimum allowed width %d", settings.getWidth(), MIN_WIDTH);
      throw new IllegalArgumentException(errorMessage);
    }
    if (settings.getHeight() < MIN_HEIGHT) {
      String errorMessage = format("Wrong input settings: the height %d less than minimum allowed height %d", settings.getHeight(), MIN_HEIGHT);
      throw new IllegalArgumentException(errorMessage);
    }
    if (settings.getNumberOfBlackHoles() < MIN_NUMBER_OF_BLACK_HOLES) {
      String errorMessage = format("Wrong input settings: the number of black holes %d less than minimum allowed number %d", settings.getNumberOfBlackHoles(), MIN_NUMBER_OF_BLACK_HOLES);
      throw new IllegalArgumentException(errorMessage);
    }
  };

  /**
   * The rule to check whether the width, height, and number of black holes are greater
   * than MAX params from ApplicationProperties.
   */
  private static Consumer<InputSettings> CHECK_MAXIMUM_ALLOWED_PROPERTIES_RULE = settings -> {
    if (settings.getWidth() > MAX_WIDTH) {
      String errorMessage = format("Wrong input settings: the width %d greater than maximum allowed width %d", settings.getWidth(), MAX_WIDTH);
      throw new IllegalArgumentException(errorMessage);
    }
    if (settings.getHeight() > MAX_HEIGHT) {
      String errorMessage = format("Wrong input settings: the height %d greater than maximum allowed height %d", settings.getHeight(), MAX_HEIGHT);
      throw new IllegalArgumentException(errorMessage);
    }
  };

  /**
   * The rule to check whether the number of black holes is less than the board size (width * height).
   */
  private static Consumer<InputSettings> CHECK_NUMBER_OF_BLACK_HOLES_RULE = settings -> {
    int boardSize = settings.getWidth() * settings.getHeight();
    int allowedMaxNumberOfBlackHoles = boardSize - 9;
    if ((settings.getNumberOfBlackHoles()) > allowedMaxNumberOfBlackHoles) {
      String errorMessage = format("Wrong input settings: the number of black holes %d greater than maximum allowed number %d", settings.getNumberOfBlackHoles(), allowedMaxNumberOfBlackHoles);
      throw new IllegalArgumentException(errorMessage);
    }
  };

  private static final List<Consumer<InputSettings>> RULES = Arrays.asList(
          CHECK_IS_NOT_NULL_RULE,
          CHECK_MINIMUM_ALLOWED_PROPERTIES_RULE,
          CHECK_MAXIMUM_ALLOWED_PROPERTIES_RULE,
          CHECK_NUMBER_OF_BLACK_HOLES_RULE
  );

  public static void validate(InputSettings inputSettings) {
    RULES.forEach(rule -> rule.accept(inputSettings));
  }
}
