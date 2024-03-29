// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {
    // Xbox Controllers Port Indexes
    public static final int DRIVE_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;
    public static final int TEST_CONTROLLER_PORT = 2;

    public static final int PDP_CAN_ID = 0;

    // Sensors
    public static final int INTAKE_BEAM_BREAK = 0;

    // Drive Motors
    public static final int LEFT_FRONT_MOTOR = 4;
    public static final int LEFT_BACK_MOTOR = 1;
    public static final int RIGHT_FRONT_MOTOR = 2;
    public static final int RIGHT_BACK_MOTOR = 3;

    public static final int CLIMBER_WENCH_MOTOR = 6;

    public static final int INTAKE_MOTOR = 5;

    public static final int ARM_MOTOR_LEFT = 7;
    public static final int ARM_MOTOR_RIGHT = 8;

    public static final int SHOOTER_LEFT = 10;
    public static final int SHOOTER_RIGHT = 11;

    public static final double ARM_Offset = Units.degreesToRadians(319.0 - 180.0);

    // Radios
    public static final double INTAKE_RADIO = (1.0/3.2727);
    public static final double SHOOTER_RADIO = (1.0/1.0);
    public static final double WENCH_RADIO = (1.0/48.0);
    public static final double ARM_RADIO = (1.0/1.0);
    public static final double DRIVE_RADIO = (1.0/8.0);
}