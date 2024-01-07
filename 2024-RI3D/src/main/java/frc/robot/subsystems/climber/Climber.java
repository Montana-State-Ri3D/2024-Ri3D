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

    logger.processInputs("Climber/Inputs", inputs);
  }

  public void setWinchPower(double power) {

    io.setWenchPower(power);
  }
  public void setBarPos(double pos) {

    io.updateSetPoint(pos);
  }
}
