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

  private double ARM_MAX_ANGLE = Units.degreesToRadians(277)  ;
  private double ARM_MIN_ANGLE = Units.degreesToRadians(35);

  public Arm(ArmIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    Logger.processInputs("Arm", inputs);

    if (this.getCurrentCommand() != null) {

      Logger.recordOutput("Arm/CurentCommand", this.getCurrentCommand().getName());
    } else {
      Logger.recordOutput("Arm/CurentCommand", "none");
    }
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

    Logger.recordOutput("Arm/Position", position);

    if (position.equals("INTAKE")) {
      setArmAngle(Units.degreesToRadians(36));
    } else if (position.equals("AMP")) {
      setArmAngle(Units.degreesToRadians(171));
    } else if (position.equals("SHOOT")) {
      setArmAngle(Units.degreesToRadians(274));
    } else if (position.equals("LATCHSTANDBY")) {
      setArmAngle(Units.degreesToRadians(171));
    } else if (position.equals("LATCH")) {
      setArmAngle(Units.degreesToRadians(217));
    } else if (position.equals("CLIMB")){
      setArmAngle(Units.degreesToRadians(277));
    }else if (position.equals("HI_INTAKE")){
      setArmAngle(Units.degreesToRadians(153));
    }
  }

  public double getAngle() {
    return inputs.curentAngle;
  }
}
