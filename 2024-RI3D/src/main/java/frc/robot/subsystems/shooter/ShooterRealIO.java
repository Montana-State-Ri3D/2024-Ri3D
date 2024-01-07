package frc.robot.subsystems.shooter;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.util.Units;

public class ShooterRealIO implements ShooterIO {

    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private CANSparkMax wristMotor;

    private SparkMaxPIDController wristMotorPID;

    public ShooterRealIO(int IDleftMotor, int IDrightMotor, int IDwristMotor) {
        leftMotor = new CANSparkMax(IDleftMotor, MotorType.kBrushless);
        rightMotor = new CANSparkMax(IDrightMotor, MotorType.kBrushless);
        //wristMotor = new CANSparkMax(IDwristMotor, MotorType.kBrushless);

        //wristMotorPID = wristMotor.getPIDController();

        leftMotor.setIdleMode(IdleMode.kCoast);
        rightMotor.setIdleMode(IdleMode.kCoast);
        //wristMotor.setIdleMode(IdleMode.kBrake);

        leftMotor.setInverted(false);
        rightMotor.setInverted(true);
        //wristMotor.setInverted(false);

        leftMotor.setSmartCurrentLimit(40);
        rightMotor.setSmartCurrentLimit(40);
        //wristMotor.setSmartCurrentLimit(40);

        leftMotor.setVoltage(0);
        rightMotor.setVoltage(0);
        //wristMotor.setVoltage(0);

    }

    public void updateInputs(ShooterIOInputs inputs) {
        inputs.leftCurent = leftMotor.getOutputCurrent();
        inputs.rightCurent = rightMotor.getOutputCurrent();
        inputs.leftVelocity = Units.rotationsPerMinuteToRadiansPerSecond(leftMotor.getEncoder().getVelocity())*SHOOTER_RADIO;
        inputs.rightVelocity = Units.rotationsPerMinuteToRadiansPerSecond(rightMotor.getEncoder().getVelocity())*SHOOTER_RADIO;
        //inputs.wristAngle = wristMotor.getEncoder().getPosition();
        //inputs.wristSetPoint = 0;
    }

    public void setPowers(double leftPower, double rightPower) {
        leftMotor.setVoltage(leftPower);
        rightMotor.setVoltage(rightPower);
    }
    public void setWristAngle(double angle){
        //wristMotorPID.setReference(angle,ControlType.kPosition);
    }
}
