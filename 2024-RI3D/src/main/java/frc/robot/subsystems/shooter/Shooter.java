// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final ShooterIOInputsAutoLogged inputs = new ShooterIOInputsAutoLogged();

  private ShooterIO io;

  private Logger logger = Logger.getInstance();

  public Shooter(ShooterIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    logger.processInputs("Shooter", inputs);

    if (this.getCurrentCommand() != null) {

      logger.recordOutput("Shooter/CurentCommand", this.getCurrentCommand().getName());
    } else {
      logger.recordOutput("Shooter/CurentCommand", "none");
    }
  }

  public void setPowers(double leftPower, double rightPower) {

    io.setPowers(leftPower, rightPower);

    logger.recordOutput("Shooter/RightPower", rightPower);
    logger.recordOutput("Shooter/LeftPower", leftPower);
  }

  public double getLRPS() {
    return inputs.leftVelocity;
  }

  public double getRRPS() {
    return inputs.rightVelocity;
  }
}
