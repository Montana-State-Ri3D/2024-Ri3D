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
        public double vertPosBar;
        public double vertPosWench;
        public double targetPos;
        public double angularPos;
        public double angularPosWench;
        public double appliedPower;
        public double appliedPowerWench;
        public double wenchPower;
    }

    default void updateInputs(ClimberIOInputs inputs){}
    default void setWenchPower(double power){}
}