package frc.robot.subsystems.climber;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

import static frc.robot.Constants.*;

public class ClimberSimIO implements ClimberIO {
// Math goes here
  private final double moment = 0.05;

  /* private DCMotor climberDCMotor = DCMotor.getNEO(1);
  private final DCMotorSim climberDCMotorSim;

  private DCMotor climberDCWench = DCMotor.getNEO(1);
  private final DCMotorSim climberDCWenchSim; */

  private double inputWenchPower = 0;


  //private final DCMotorSim climberDCMotorSim = DCMotorSim(climberDCMotor,CLIMB_RADIO, moment);

  private boolean isBrakeBar;
  private boolean isBrakeWench;

  private double appliedPower;

  public ClimberSimIO(){
    // climberDCMotor = DCMotor.getCIM(1);
    // climberDCWench = DCMotor.getCIM(1);
    // climberDCMotorSim = new DCMotorSim(climberDCMotor,CLIMB_RADIO, moment);
    // climberDCWenchSim = new DCMotorSim(climberDCWench,CLIMB_RADIO, moment);


    isBrakeBar = true;
  }

  /* public void updateSetPoint() {
    m_pidController.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
} */

  @Override
  public void updateInputs(ClimberIOInputs inputs) {
    inputs.climberisBrake = isBrakeBar;
    inputs.climberisBrakeWench = isBrakeWench;
    inputs.vertPosBar = 0;
    inputs.vertPosWench = 0;
    inputs.climberCurrent = 0;
    inputs.climberCurrentWench = 0;
    inputs.appliedPower = appliedPower;
    inputs.appliedPowerWench = inputWenchPower;
    inputs.targetPos = 0;
    inputs.angularPos = 0;
    inputs.angularPosWench = 0;
  }

  @Override
  public void setWenchPower(double power) {
    inputWenchPower = power;
    //climberDCWenchSim.setInputVoltage(power);
    //climberDCWenchSim.update(0.020);  
  }
}
