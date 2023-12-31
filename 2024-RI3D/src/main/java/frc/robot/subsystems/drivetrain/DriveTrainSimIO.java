// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

import static frc.robot.Constants.*;

/** Add your docs here. */
public class DriveTrainSimIO implements DriveTrainIO  {

    private final double moment = 0.0005;

    private boolean isBrake;
    private final DCMotor lefMotors;
    private final DCMotor rightMotors;

    private final DCMotorSim lMotorSim;
    private final DCMotorSim rMotorSim;

    public DriveTrainSimIO(){
        lefMotors = DCMotor.getCIM(2);
        rightMotors = DCMotor.getCIM(2);
        lMotorSim = new DCMotorSim(lefMotors,DRIVE_RADIO, moment);
        rMotorSim = new DCMotorSim(rightMotors,DRIVE_RADIO, moment);


        isBrake = false;
    }
    public void updateInputs(DriveTrainIOInputs inputs){
        inputs.isBrake = isBrake;
        inputs.leftCurent = lMotorSim.getCurrentDrawAmps();
        inputs.rightCurent = rMotorSim.getCurrentDrawAmps();
    }
    public void drive(double leftPower, double rightPower){
        lMotorSim.setInput(leftPower);
        rMotorSim.setInput(rightPower);
    }
    public void toggleMode(){
        isBrake = !isBrake;
    }
}