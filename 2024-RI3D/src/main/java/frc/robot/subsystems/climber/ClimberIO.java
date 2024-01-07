// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climber;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface ClimberIO {
    @AutoLog
    class ClimberIOInputs{
        public boolean climberisBrake;
        public boolean climberisBrakeWench;
        public double climberCurrent;
        public double climberCurrentWench;
        public double vertPos;
        public double vertPosWench;
        public double targetPos;
        public double targetPosWench;
        public double angularPos;
        public double angularPosWench;
        public double appliedPower;
        public double appliedPowerWench;
    }

    default void updateInputs(ClimberIOInputs inputs){}
    default void climb(double Power) {}
    default void toggleMode(){}

}