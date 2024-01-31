package frc.robot.subsystems.intake;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeRealIO implements IntakeIO {
    private CANSparkMax intakeMotor;
    private DigitalInput beamBreak;

    private boolean isBrake;

    private RelativeEncoder intakeMotorEncoder;

    public IntakeRealIO(int IDintakeMotor, int IDBeamBreak) {
        intakeMotor = new CANSparkMax(IDintakeMotor, MotorType.kBrushless);
        beamBreak = new DigitalInput(IDBeamBreak);

        intakeMotorEncoder = intakeMotor.getEncoder();

        intakeMotor.setSmartCurrentLimit(100);

        intakeMotor.setIdleMode(IdleMode.kCoast);

        intakeMotor.setInverted(true);

        intakeMotorEncoder.setPositionConversionFactor(INTAKE_RADIO * Math.PI * 2);
        intakeMotorEncoder.setVelocityConversionFactor(INTAKE_RADIO * Math.PI * 2 / 60);

        isBrake = false;
    }

    public void updateInputs(IntakeIOInputs inputs) {
        inputs.isBrake = isBrake;
        inputs.curent = intakeMotor.getOutputCurrent();
        inputs.velocity = intakeMotorEncoder.getVelocity();
        inputs.beamBreak = !beamBreak.get();
        inputs.position = intakeMotorEncoder.getPosition();
        inputs.appliedOutput = intakeMotor.getAppliedOutput();

    }

    public void setPower(double power) {
        intakeMotor.set(power);
    }

    public void setBrake(boolean brake) {
        if (brake) {
            intakeMotor.setIdleMode(IdleMode.kBrake);
        } else {
            intakeMotor.setIdleMode(IdleMode.kCoast);
        }
        isBrake = brake;
    }
}
