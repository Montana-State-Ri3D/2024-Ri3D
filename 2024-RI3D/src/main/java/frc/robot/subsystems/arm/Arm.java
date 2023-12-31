// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

  private final ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();

  private ArmIO io;

  private Logger logger = Logger.getInstance();

  public Arm(ArmIO io) {
    this.io = io;
  }

 

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    logger.processInputs("Arm/Inputs", inputs);
  }
  public void setArmAngle(double angle) {
    io.setAngle(angle);

  }

  public void setPosition (String position) {
    if (position.equals("INTAKE")) {
      io.setAngle(0);
    }
    else if (position.equals("AMP")) {
      io.setAngle(90);
    }
    else if (position.equals("SHOOT")) {
      io.setAngle(160);
    }
    else if (position.equals("LATCHSTANDBY")) {
      io.setAngle(90);
    }
    else if (position.equals("LATCH")) {
      io.setAngle(120);
    }
  }

  
}
