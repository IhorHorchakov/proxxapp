package proxx.app.services.validator;

import org.junit.Test;
import proxx.app.model.cell.Position;
import proxx.app.model.settings.InputSettings;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class PositionValidatorTest {

  @Test
  public void should_pass_validation_when_position_is_inside_the_board() {
    // given
    InputSettings inputSettings = InputSettings.of(20, 20, 10);
    Position position = Position.of(19, 19);

    // then
    assertThatNoException()
            .isThrownBy(() -> PositionValidator.validate(inputSettings, position));
  }

  @Test
  public void should_throw_exception_when_settings_object_is_null() {
    // given
    InputSettings inputSettings = null;
    Position position = Position.of(0, 0);

    // then
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> PositionValidator.validate(inputSettings, position))
            .withMessage("Wrong input settings: the settings must not be null");
  }

  @Test
  public void should_throw_exception_when_position_object_is_null() {
    // given
    InputSettings inputSettings = InputSettings.of(20, 20, 10);
    Position position = null;

    // then
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> PositionValidator.validate(inputSettings, position))
            .withMessage("Wrong input position: the position must not be null");
  }

  @Test
  public void should_throw_exception_when_position_is_outside_the_board() {
    // given
    InputSettings inputSettings = InputSettings.of(20, 20, 10);
    Position position = Position.of(21, 21);

    // then
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> PositionValidator.validate(inputSettings, position))
            .withMessage("Wrong input position: the position of user's click ({width=21, height=21}) is outside the game board");
  }

  @Test
  public void should_throw_exception_when_position_has_negative_properties() {
    // given
    InputSettings inputSettings = InputSettings.of(20, 20, 10);
    Position position = Position.of(-1, -5);

    // then
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> PositionValidator.validate(inputSettings, position))
            .withMessage("Wrong input position: the position of user's click ({width=-1, height=-5}) has negative properties");
  }
}
