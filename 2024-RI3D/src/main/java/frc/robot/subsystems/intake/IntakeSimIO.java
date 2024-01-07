package frc.robot.subsystems.intake;

import static frc.robot.Constants.*;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class IntakeSimIO implements IntakeIO{
    
    private final double moment = 0.05;

    private boolean isBrake;
    private boolean intakeUp;
    private final DCMotor motor;
    private final DCMotorSim motorSim;

    public IntakeSimIO(){
        motor = DCMotor.getNEO(1);

        motorSim = new DCMotorSim(motor,INTAKE_RADIO,moment);

        isBrake = false;
        intakeUp = true;
    }
    public void updateInputs(IntakeIOInputs inputs){
        inputs.isBrake = isBrake;
        inputs.intakeUp = intakeUp;

        inputs.curent = motorSim.getCurrentDrawAmps();
        inputs.velocity = motorSim.getAngularVelocityRadPerSec();
    }
    public void setPower(double power){
        motorSim.setInputVoltage(power*12);
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
