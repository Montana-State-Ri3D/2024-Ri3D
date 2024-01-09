// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

  private final ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();

  private ArmIO io;

  private Logger logger = Logger.getInstance();

  private double ARM_MAX_ANGLE = Units.degreesToRadians(277)  ;
  private double ARM_MIN_ANGLE = Units.degreesToRadians(37);

  public Arm(ArmIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    logger.processInputs("Arm/Inputs", inputs);
  }

  public void setArmAngle(double angle) {
    if (angle > ARM_MAX_ANGLE) {
      System.out.println("Arm angle is too big");
    } else if (angle < ARM_MIN_ANGLE) {
      System.out.println("Arm angle is too small");
    }

    else {
      io.setAngle(angle);
    }

  }

  public void setPosition(String position) {

    logger.recordOutput("Arm/Position", position);

    if (position.equals("INTAKE")) {
      setArmAngle(Units.degreesToRadians(0));
    } else if (position.equals("AMP")) {
      setArmAngle(Units.degreesToRadians(0));
    } else if (position.equals("SHOOT")) {
      setArmAngle(Units.degreesToRadians(0));
    } else if (position.equals("LATCHSTANDBY")) {
      setArmAngle(Units.degreesToRadians(0));
    } else if (position.equals("LATCH")) {
      setArmAngle(Units.degreesToRadians(0));
    }
  }

}
