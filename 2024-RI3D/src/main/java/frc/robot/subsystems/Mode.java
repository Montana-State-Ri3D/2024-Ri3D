// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Mode extends SubsystemBase {
  private Type mode;

  public enum Type {
    CONE,
    CUBE,
    EJECT
  }

  /** Creates a new Mode. */
  public Mode() {
    mode = Type.CONE;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Logger logger = Logger.getInstance();

    logger.recordOutput("Mode", mode.toString());
  }

  public Type getMode() {
    return mode;
  }

  public void setMode(Type mode) {
    this.mode = mode;
  }
}