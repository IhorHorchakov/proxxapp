package proxx.app.services.validator;

import org.junit.Test;
import proxx.app.model.settings.GameMode;
import proxx.app.model.settings.InputSettings;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class InputSettingsValidatorTest {

  @Test
  public void should_pass_validation_for_custom_settings() {
    // given
    InputSettings inputSettings = InputSettings.of(20, 20, 10);

    // then
    assertThatNoException()
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings));
  }

  @Test
  public void should_pass_validation_for_easy_mode() {
    // given
    InputSettings inputSettings = InputSettings.of(GameMode.EASY);

    // then
    assertThatNoException()
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings));
  }

  @Test
  public void should_pass_validation_for_medium_mode() {
    // given
    InputSettings inputSettings = InputSettings.of(GameMode.MEDIUM);

    // then
    assertThatNoException()
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings));
  }

  @Test
  public void should_pass_validation_for_hard_mode() {
    // given
    InputSettings inputSettings = InputSettings.of(GameMode.HARD);

    // then
    assertThatNoException()
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings));
  }

  @Test
  public void should_throw_exception_when_settings_object_is_null() {
    // given
    InputSettings inputSettings = null;

    // then expect
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings))
            .withMessage("Wrong input settings: the settings must not be null");
  }

  @Test
  public void should_throw_exception_when_width_is_less_than_min_allowed_value() {
    // given
    InputSettings inputSettings = InputSettings.of(1, 10, 1);

    // then expect
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings))
            .withMessage("Wrong input settings: the width 1 less than minimum allowed width 5");
  }

  @Test
  public void should_throw_exception_when_height_is_less_than_min_allowed_value() {
    // given
    InputSettings inputSettings = InputSettings.of(10, 1, 1);

    // then expect
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings))
            .withMessage("Wrong input settings: the height 1 less than minimum allowed height 5");
  }

  @Test
  public void should_throw_exception_when_blackHolesNumber_is_less_than_min_allowed_value() {
    // given
    InputSettings inputSettings = InputSettings.of(10, 10, 0);

    // then expect
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings))
            .withMessage("Wrong input settings: the number of black holes 0 less than minimum allowed number 1");
  }

  @Test
  public void should_throw_exception_when_width_is_greater_than_max_allowed_value() {
    // given
    InputSettings inputSettings = InputSettings.of(100, 10, 1);

    // then expect
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings))
            .withMessage("Wrong input settings: the width 100 greater than maximum allowed width 40");
  }

  @Test
  public void should_throw_exception_when_height_is_greater_than_max_allowed_value() {
    // given
    InputSettings inputSettings = InputSettings.of(10, 100, 1);

    // then expect
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings))
            .withMessage("Wrong input settings: the height 100 greater than maximum allowed height 40");
  }

  @Test
  public void should_throw_exception_when_blackHolesNumber_is_greater_than_max_allowed_value() {
    // given
    InputSettings inputSettings = InputSettings.of(10, 10, 100);

    // then expect
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> InputSettingsValidator.validate(inputSettings))
            .withMessage("Wrong input settings: the number of black holes 100 greater than maximum allowed number 91");
  }
}
