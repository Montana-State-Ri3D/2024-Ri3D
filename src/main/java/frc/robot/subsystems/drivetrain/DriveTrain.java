package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class DriveTrain extends SubsystemBase {

  private final DriveTrainIOInputsAutoLogged inputs = new DriveTrainIOInputsAutoLogged();

  private DriveTrainIO io;

  private TalonSRXConfiguration highCurrent;
  private TalonSRXConfiguration lowCurrent;

  private boolean lowCurrentMode = false;

  public DriveTrain(DriveTrainIO io) {
    this.io = io;

    highCurrent = new TalonSRXConfiguration();
    highCurrent.peakCurrentLimit = 15;
    highCurrent.peakCurrentDuration = 1500;
    highCurrent.continuousCurrentLimit = 10;

    lowCurrent = new TalonSRXConfiguration();
    lowCurrent.peakCurrentLimit = 5;
    lowCurrent.peakCurrentDuration = 10;
    lowCurrent.continuousCurrentLimit = 5;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    Logger.processInputs("DriveTrain", inputs);
    Logger.recordOutput("DriveTrain/LowCurentMode", lowCurrentMode);

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

  public void toggleMode() {
    io.toggleMode();
  }

  public void setLowCurrentMode() {
    lowCurrentMode = true;
    io.setPowerProfile(lowCurrent);
  }

  public void setHighCurrentMode() {
    lowCurrentMode = false;
    io.setPowerProfile(highCurrent);
  }
}
