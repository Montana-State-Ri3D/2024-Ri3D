package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.DigitalInput;

public class ClimberRealIO implements ClimberIO {

    private CANSparkMax climberMotor;

    private RelativeEncoder climberMotorEncoder;

    private SparkMaxPIDController m_pidController;

    private boolean isBrake;

    public double setPoint;

    public double kP, kI, kD, kMaxOutput, kMinOutput, maxVel, minVel, maxAcc, allowedErr;

    public ClimberRealIO(){
        initClimberBase(CLIMBER_MOTOR);
    }

    private void initClimberBase(int IDclimberMotor) {
        climberMotor = new CANSparkMax(IDclimberMotor, MotorType.kBrushless);

        climberMotor.restoreFactoryDefaults();
        climberMotor.restoreFactoryDefaults();

        //Config
        climberMotor.setSmartCurrentLimit(45);

        climberMotor.setIdleMode(IdleMode.kBrake);

        climberMotor.setInverted(true);

        m_pidController = climberMotor.getPIDController();
        climberMotorEncoder = climberMotor.getEncoder();

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

        isBrake = false;
    }

    public void updateInputs(ClimberIOInputs inputs) {
        inputs.climberisBrake = isBrake;
        inputs.vertPos = climberMotorEncoder.getPosition();
        inputs.climberCurrent = climberMotor.getOutputCurrent();
        inputs.appliedPower = climberMotor.getAppliedOutput();

    }

    public void teleopPeriodic() {
        m_pidController.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
    }

    public void resetClimberEncoder(){
      climberMotorEncoder.setPosition(0);
    }

}