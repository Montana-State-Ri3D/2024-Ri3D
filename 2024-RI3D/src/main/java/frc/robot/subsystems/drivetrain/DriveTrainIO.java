// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface DriveTrainIO {
    @AutoLog
    class DriveTrainIOInputs{
        public boolean isBrake;
        public double leftCurent;
        public double rightCurent;
    }
    default void updateInputs(DriveTrainIOInputs inputs){}
    default void drive(double leftPower, double rightPower) {}
    default void toggleMode(){}

}