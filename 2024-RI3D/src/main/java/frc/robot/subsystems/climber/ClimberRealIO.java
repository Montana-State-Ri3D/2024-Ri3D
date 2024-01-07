package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;

import java.lang.annotation.Target;
import java.lang.reflect.GenericSignatureFormatError;

public class ClimberRealIO implements ClimberIO {

    private CANSparkMax climberMotor;
    private CANSparkMax climberWenchMotor;

    private RelativeEncoder climberMotorEncoder;
    private RelativeEncoder climberWenchMotorEncoder;

    private SparkMaxPIDController wenchPIDController;

    private boolean isBrake;

    public double targetPos;

    public double kP, kI, kD, kMaxOutput, kMinOutput, maxVel, minVel, maxAcc, allowedErr;

    public ClimberRealIO(int IDclimberMotor, int IDclimberWenchMotor) {
        climberMotor = new CANSparkMax(IDclimberMotor, MotorType.kBrushless);
        climberWenchMotor = new CANSparkMax(IDclimberWenchMotor, MotorType.kBrushless);

        climberMotor.restoreFactoryDefaults();
        climberWenchMotor.restoreFactoryDefaults();

        //Config
        climberMotor.setSmartCurrentLimit(45);
        climberWenchMotor.setSmartCurrentLimit(45);

        climberMotor.setIdleMode(IdleMode.kBrake);
        climberWenchMotor.setIdleMode(IdleMode.kBrake);

        climberMotor.setInverted(true);
        climberWenchMotor.setInverted(true);

        wenchPIDController = climberMotor.getPIDController();
        climberMotorEncoder = climberMotor.getEncoder();
        climberWenchMotorEncoder = climberWenchMotor.getEncoder();

        // PID coefficients
        kP = 0; 
        kI = 0;
        kD = 0;  
        kMaxOutput = 1; 
        kMinOutput = -1;

        wenchPIDController.setP(kP);
        wenchPIDController.setI(kI);
        wenchPIDController.setD(kD);
        wenchPIDController.setOutputRange(kMinOutput, kMaxOutput);

        climberMotorEncoder.setPositionConversionFactor(CLIMB_RADIO);
        climberWenchMotorEncoder.setPositionConversionFactor(CLIMB_RADIO);

        isBrake = true;
    }

    public void updateInputs(ClimberIOInputs inputs) {
        inputs.climberisBrake = isBrake;
        inputs.climberisBrakeWench = isBrake;
        inputs.vertPosBar = climberMotorEncoder.getPosition();
        inputs.vertPosWench = climberWenchMotorEncoder.getPosition();
        inputs.climberCurrent = climberMotor.getOutputCurrent();
        inputs.climberCurrentWench = climberWenchMotor.getOutputCurrent();
        inputs.appliedPower = climberMotor.getAppliedOutput();
        inputs.appliedPowerWench = climberWenchMotor.getAppliedOutput();
        inputs.targetPos = this.targetPos;
        inputs.angularPos = 0;
        inputs.angularPosWench = 0;

    }

    public void updateSetPoint(double setPoint) {
        wenchPIDController.setReference(setPoint, CANSparkMax.ControlType.kPosition);
        targetPos = setPoint;
    }

    public void setWenchPower(double power) {
        climberWenchMotor.set(power);
      }

    public void resetClimberEncoder(){
      climberMotorEncoder.setPosition(0);
    }

}