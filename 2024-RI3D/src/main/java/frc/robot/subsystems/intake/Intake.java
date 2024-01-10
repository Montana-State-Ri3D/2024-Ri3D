// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private final IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

  private IntakeIO io;

  private Logger logger = Logger.getInstance();

  public Intake(IntakeIO io) {
    this.io = io;

  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    logger.processInputs("Intake", inputs);

    if (this.getCurrentCommand() != null) {

      logger.recordOutput("Intake/CurentCommand", this.getCurrentCommand().getName());
    } else {
      logger.recordOutput("Intake/CurentCommand", "none");
    }
  }

  public void setPower(double power) {
    io.setPower(power);
  }

  public boolean getBeamBreakValue() {
    return inputs.beamBreak;
  }
}
