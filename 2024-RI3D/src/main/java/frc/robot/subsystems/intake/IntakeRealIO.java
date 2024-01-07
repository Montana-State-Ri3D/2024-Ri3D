package frc.robot.subsystems.intake;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.subsystems.intake.IntakeIO.IntakeIOInputs;

public class IntakeRealIO {
    private CANSparkMax intakeMotor;

    private boolean isBrake;
    private boolean intakeUp;

    private RelativeEncoder intakeMotorEncoder;

    public IntakeRealIO(int IDintakeMotor) {
        intakeMotor = new CANSparkMax(IDintakeMotor, MotorType.kBrushless);

        intakeMotor.restoreFactoryDefaults();

        intakeMotorEncoder = intakeMotor.getEncoder();
        //Config
        intakeMotor.setSmartCurrentLimit(45);

        intakeMotor.setIdleMode(IdleMode.kCoast);

        intakeMotor.setInverted(true);

        intakeMotorEncoder.setPositionConversionFactor(INTAKE_MOTOR*Math.PI*2);
        intakeMotorEncoder.setVelocityConversionFactor(INTAKE_MOTOR*Math.PI*2/60);

        isBrake = false;
        intakeUp = true;
    }

    public void updateInputs(IntakeIOInputs inputs){
        inputs.isBrake = isBrake;
        inputs.intakeUp = intakeUp;

        inputs.curent = intakeMotor.getOutputCurrent();
        inputs.velocity = intakeMotorEncoder.getVelocity();
    }

    public void setPower(double power){
        intakeMotor.set(power*12);
    }

    public void toggleMode() {
        isBrake = !isBrake;
    }

    public void raiseIntake() {
        intakeUp = true;
    }
    public void lowerIntake(){
        intakeUp = false;
    }
}
