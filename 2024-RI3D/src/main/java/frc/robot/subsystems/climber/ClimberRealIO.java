package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;

public class ClimberRealIO implements ClimberIO {

    private CANSparkMax climberWenchMotor;

    private RelativeEncoder climberWenchMotorEncoder;

    private boolean isBrake;

    public ClimberRealIO(int IDclimberWenchMotor) {
        climberWenchMotor = new CANSparkMax(IDclimberWenchMotor, MotorType.kBrushless);

        climberWenchMotorEncoder = climberWenchMotor.getEncoder();
        // Config
        climberWenchMotor.setSmartCurrentLimit(40);

        climberWenchMotor.setIdleMode(IdleMode.kBrake);

        climberWenchMotor.setInverted(true);

        climberWenchMotorEncoder.setPositionConversionFactor(WENCH_RADIO * Math.PI * 2);
        climberWenchMotorEncoder.setVelocityConversionFactor(WENCH_RADIO * Math.PI * 2 / 60);

        isBrake = true;
    }

    public void updateInputs(ClimberIOInputs inputs) {
        inputs.climberisBrakeWench = isBrake;
        inputs.climberCurrentWench = climberWenchMotor.getOutputCurrent();
        inputs.appliedPowerWench = climberWenchMotor.getAppliedOutput();
        inputs.angularPosWench = climberWenchMotorEncoder.getPosition();

    }

    public void setWenchPower(double power) {
        climberWenchMotor.set(power);
    }

    public void setBreak(boolean brake) {
        if (brake) {
            climberWenchMotor.setIdleMode(IdleMode.kBrake);
        } else {
            climberWenchMotor.setIdleMode(IdleMode.kCoast);
        }
        isBrake = brake;
    }
}