package proxx.app;

import proxx.app.model.cell.Position;
import proxx.app.model.settings.GameMode;
import proxx.app.model.settings.InputSettings;
import proxx.app.services.Processor;

public class GameStarter {
  public static void main(String[] args) {
    Processor processor = new Processor(InputSettings.of(GameMode.HARD));
    processor.processClick(Position.of(0, 0));
    processor.processClick(Position.of(12, 4));
    processor.processClick(Position.of(17, 10));
    processor.processClick(Position.of(10, 3));
  }
}
