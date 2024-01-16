package frc.robot.subsystems.shooter;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class ShooterRealIO implements ShooterIO {

    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private SparkMaxPIDController leftPIDController;
    private SparkMaxPIDController rightPIDController;

    private RelativeEncoder leftMotorEncoder;
    private RelativeEncoder rightMotorEncoder;

    private double p = 0;
    private double i = 0;
    private double d = 0;

    public ShooterRealIO(int IDleftMotor, int IDrightMotor) {
        leftMotor = new CANSparkMax(IDleftMotor, MotorType.kBrushless);
        rightMotor = new CANSparkMax(IDrightMotor, MotorType.kBrushless);

        leftPIDController = leftMotor.getPIDController();
        rightPIDController = rightMotor.getPIDController();

        leftMotorEncoder = leftMotor.getEncoder();
        rightMotorEncoder = rightMotor.getEncoder();

        leftMotor.setInverted(false);
        rightMotor.setInverted(true);

        leftMotor.setSmartCurrentLimit(80);
        rightMotor.setSmartCurrentLimit(80);

        leftMotorEncoder.setVelocityConversionFactor(SHOOTER_RADIO*2*Math.PI/60);
        rightMotorEncoder.setVelocityConversionFactor(SHOOTER_RADIO*2*Math.PI/60);

        leftMotorEncoder.setPositionConversionFactor(SHOOTER_RADIO*2*Math.PI);
        rightMotorEncoder.setPositionConversionFactor(SHOOTER_RADIO*2*Math.PI);

        leftPIDController.setP(p);
        leftPIDController.setI(i);
        leftPIDController.setD(d);
        rightPIDController.setP(p);
        rightPIDController.setI(i);
        rightPIDController.setD(d);

        leftPIDController.setFeedbackDevice(leftMotorEncoder);
        rightPIDController.setFeedbackDevice(rightMotorEncoder);

        leftMotor.setIdleMode(IdleMode.kCoast);
        rightMotor.setIdleMode(IdleMode.kCoast);
    }

    public void updateInputs(ShooterIOInputs inputs) {
        inputs.leftCurent = leftMotor.getOutputCurrent();
        inputs.rightCurent = rightMotor.getOutputCurrent();
        inputs.leftVelocity = leftMotorEncoder.getVelocity();
        inputs.rightVelocity = rightMotorEncoder.getVelocity();
        inputs.leftPosition = leftMotorEncoder.getPosition();
        inputs.rightPosition = rightMotorEncoder.getPosition();
    }

    public void setPowers(double leftPower, double rightPower) {
        leftMotor.set(leftPower);
        rightMotor.set(rightPower);
    }

    public void setRPS(double leftRPS, double rightRPS) {
        leftPIDController.setReference(leftRPS, ControlType.kVelocity);
        rightPIDController.setReference(rightRPS, ControlType.kVelocity);
    }
}
