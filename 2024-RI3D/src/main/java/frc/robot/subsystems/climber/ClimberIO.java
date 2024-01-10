// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climber;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface ClimberIO {
    @AutoLog
    class ClimberIOInputs{
        public boolean climberisBrakeWench;
        public double climberCurrentWench;
        public double angularPosWench;
        public double appliedPowerWench;
    }

    default void updateInputs(ClimberIOInputs inputs){}
    default void setWenchPower(double power){}
    default void setBreak(boolean brake){}

}