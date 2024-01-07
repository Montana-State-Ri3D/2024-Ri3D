package frc.robot.subsystems.shooter;

import static frc.robot.Constants.*;

import javax.swing.text.AbstractDocument.LeafElement;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class ShooterRealIO implements ShooterIO {

    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;

    RelativeEncoder leftMotorEncoder;
    RelativeEncoder rightMotorEncoder;


    public ShooterRealIO(int IDleftMotor, int IDrightMotor) {
        leftMotor = new CANSparkMax(IDleftMotor, MotorType.kBrushless);
        rightMotor = new CANSparkMax(IDrightMotor, MotorType.kBrushless);

        leftMotorEncoder = leftMotor.getEncoder();
        rightMotorEncoder = rightMotor.getEncoder();

        leftMotor.setIdleMode(IdleMode.kCoast);
        rightMotor.setIdleMode(IdleMode.kCoast);

        leftMotor.setInverted(true);
        rightMotor.setInverted(false);

        leftMotor.setSmartCurrentLimit(40);
        rightMotor.setSmartCurrentLimit(40);

        leftMotor.setVoltage(0);
        rightMotor.setVoltage(0);

        leftMotorEncoder.setVelocityConversionFactor(SHOOTER_RADIO*2*Math.PI/60);
        rightMotorEncoder.setVelocityConversionFactor(SHOOTER_RADIO*2*Math.PI/60);
    }

    public void updateInputs(ShooterIOInputs inputs) {
        inputs.leftCurent = leftMotor.getOutputCurrent();
        inputs.rightCurent = rightMotor.getOutputCurrent();
        inputs.leftVelocity = leftMotorEncoder.getVelocity();
        inputs.rightVelocity = rightMotorEncoder.getVelocity();
    }

    public void setPowers(double leftPower, double rightPower) {
        leftMotor.setVoltage(leftPower);
        rightMotor.setVoltage(rightPower);
    }
}
