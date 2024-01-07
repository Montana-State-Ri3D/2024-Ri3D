package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

public class Climber extends SubsystemBase {
  
  private final ClimberIOInputsAutoLogged inputs = new ClimberIOInputsAutoLogged();

  private ClimberIO io;

  private Logger logger = Logger.getInstance();

  public Climber(ClimberIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    Logger logger = Logger.getInstance();

    logger.processInputs("Climber/Inputs", inputs);
  }

  public void climb(double Power) {

    io.climb(Power);

    logger.recordOutput("Climber/Power", Power);
  }

  public void toggleMode(){
    io.toggleMode();
  }
}
