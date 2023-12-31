package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

public class DriveTrain extends SubsystemBase {

  private final DriveTrainIOInputsAutoLogged inputs = new DriveTrainIOInputsAutoLogged();

  private DriveTrainIO io;

  private Logger logger = Logger.getInstance();

  public DriveTrain(DriveTrainIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    Logger logger = Logger.getInstance();

    logger.processInputs("DriveTrain/Inputs", inputs);
  }

  public void drive(double leftPower, double rightPower) {

    io.drive(leftPower, rightPower);

    logger.recordOutput("DriveTrain/RightPower", rightPower);
    logger.recordOutput("DriveTrain/LeftPower", leftPower);
  }

  public void toggleMode(){
    io.toggleMode();
  }
}
