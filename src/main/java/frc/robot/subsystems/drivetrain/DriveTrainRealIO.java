// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import static frc.robot.Constants.*;

public class DriveTrainRealIO implements DriveTrainIO {

    private TalonSRX leftMotor_1;
    private TalonSRX leftMotor_2;
    private TalonSRX rightMotor_1;
    private TalonSRX rightMotor_2;

    private boolean isBrake;


    public DriveTrainRealIO(int IDleftMotor_1, int IDleftMotor_2, int IDrightMotor_1, int IDrightMotor_2) {
        leftMotor_1 = new TalonSRX(LEFT_FRONT_MOTOR);
        leftMotor_2 = new TalonSRX(LEFT_BACK_MOTOR);
        rightMotor_1 = new TalonSRX(RIGHT_FRONT_MOTOR);
        rightMotor_2 = new TalonSRX(RIGHT_BACK_MOTOR);

        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.peakCurrentLimit = 15;
        config.peakCurrentDuration = 1500;
        config.continuousCurrentLimit = 10;

        leftMotor_1.configAllSettings(config);
        leftMotor_2.configAllSettings(config);
        rightMotor_1.configAllSettings(config);
        rightMotor_2.configAllSettings(config);

        leftMotor_1.setNeutralMode(NeutralMode.Coast);
        leftMotor_2.setNeutralMode(NeutralMode.Coast);
        rightMotor_1.setNeutralMode(NeutralMode.Coast);
        rightMotor_2.setNeutralMode(NeutralMode.Coast);

        leftMotor_1.setInverted(true);
        leftMotor_2.setInverted(true);

        leftMotor_2.follow(leftMotor_1);
        rightMotor_2.follow(rightMotor_1);

        isBrake = false;
    }

    public void updateInputs(DriveTrainIOInputs inputs){
        inputs.isBrake = isBrake;
        inputs.leftCurent = leftMotor_1.getStatorCurrent()+ leftMotor_2.getStatorCurrent();
        inputs.rightCurent = rightMotor_2.getStatorCurrent()+ rightMotor_2.getStatorCurrent();
        inputs.leftAppliedPower = leftMotor_1.getMotorOutputPercent();
        inputs.rightAppliedPower = rightMotor_1.getMotorOutputPercent();
    }

    public void drive(double leftPower, double rightPower) {
        leftMotor_1.set(TalonSRXControlMode.PercentOutput, leftPower);
        rightMotor_1.set(TalonSRXControlMode.PercentOutput, rightPower);
    }

    private void setCoast() {
        leftMotor_1.setNeutralMode(NeutralMode.Coast);
        leftMotor_2.setNeutralMode(NeutralMode.Coast);
        rightMotor_1.setNeutralMode(NeutralMode.Coast);
        rightMotor_2.setNeutralMode(NeutralMode.Coast);

        isBrake = false;
    }

    private void setBrake() {
        leftMotor_1.setNeutralMode(NeutralMode.Brake);
        leftMotor_2.setNeutralMode(NeutralMode.Brake);
        rightMotor_1.setNeutralMode(NeutralMode.Brake);
        rightMotor_2.setNeutralMode(NeutralMode.Brake);

        isBrake = true;
    }

    public void toggleMode() {
        if (isBrake) {
            setCoast();
        } else {
            setBrake();
        }
    }
}