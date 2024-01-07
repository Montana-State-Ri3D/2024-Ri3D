package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;

public class ClimberRealIO implements ClimberIO {

    private CANSparkMax climberMotor;
    private CANSparkMax climberWenchMotor;

    private RelativeEncoder climberMotorEncoder;
    private RelativeEncoder climberWenchMotorEncoder;

    private SparkMaxPIDController m_pidController;

    private boolean isBrake;

    public double setPoint;

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

        m_pidController = climberMotor.getPIDController();
        climberMotorEncoder = climberMotor.getEncoder();
        climberWenchMotorEncoder = climberWenchMotor.getEncoder();

        // PID coefficients
        kP = 0; 
        kI = 0;
        kD = 0;  
        kMaxOutput = 1; 
        kMinOutput = -1;

        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);

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
        inputs.targetPos = 0;
        inputs.angularPos = 0;
        inputs.angularPosWench = 0;

    }

    public void updateSetPoint() {
        m_pidController.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
    }

    public void setWenchPower(double power) {
        climberWenchMotor.set(power);
      }
    
    public void setBarHeight(double height) {
        final double currentHeight = climberMotorEncoder.getPosition();

    }
    public void resetClimberEncoder(){
      climberMotorEncoder.setPosition(0);
    }

}