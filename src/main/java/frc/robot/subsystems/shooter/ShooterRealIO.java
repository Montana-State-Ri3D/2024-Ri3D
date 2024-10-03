package frc.robot.subsystems.shooter;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;



public class ShooterRealIO implements ShooterIO {

    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private SparkPIDController leftPIDController;
    private SparkPIDController rightPIDController;

    private RelativeEncoder leftMotorEncoder;
    private RelativeEncoder rightMotorEncoder;

    private double p = 0.0009;
    private double i = 0.00001;
    private double d = 0.0;

    private double setPoint = 0;

    public ShooterRealIO(int IDleftMotor, int IDrightMotor) {
        leftMotor = new CANSparkMax(IDleftMotor, MotorType.kBrushless);
        rightMotor = new CANSparkMax(IDrightMotor, MotorType.kBrushless);

        leftPIDController = leftMotor.getPIDController();
        rightPIDController = rightMotor.getPIDController();

        leftMotorEncoder = leftMotor.getEncoder();
        rightMotorEncoder = rightMotor.getEncoder();

        leftMotor.setInverted(false);
        rightMotor.setInverted(false);

        leftPIDController.setOutputRange(-1,1);
        rightPIDController.setOutputRange(-1, 1);

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
        inputs.leftAppliedPower = leftMotor.getAppliedOutput();
        inputs.rightApppliedPower = rightMotor.getAppliedOutput();
        inputs.setPoint = setPoint;
    }

    public void setPowers(double leftPower, double rightPower) {
        leftMotor.set(leftPower);
        rightMotor.set(rightPower);

        this.setPoint = 0.0;
    }

    public void setRPS(double leftRPS, double rightRPS) {
        leftPIDController.setReference(leftRPS, ControlType.kVelocity);
        rightPIDController.setReference(rightRPS, ControlType.kVelocity);

        this.setPoint = leftRPS;
    }
}
