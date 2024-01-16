package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

public class DriveTrain extends SubsystemBase {

  private final DriveTrainIOInputsAutoLogged inputs = new DriveTrainIOInputsAutoLogged();

  private DriveTrainIO io;

  public DriveTrain(DriveTrainIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    Logger.processInputs("DriveTrain", inputs);

    if (this.getCurrentCommand() != null) {

      Logger.recordOutput("DriveTrain/CurentCommand", this.getCurrentCommand().getName());
    } else {
      Logger.recordOutput("DriveTrain/CurentCommand", "none");
    }
  }

  public void drive(double leftPower, double rightPower) {

    io.drive(leftPower, rightPower);

    Logger.recordOutput("DriveTrain/RightPower", rightPower);
    Logger.recordOutput("DriveTrain/LeftPower", leftPower);
  }

  public void toggleMode(){
    io.toggleMode();
  }
}
