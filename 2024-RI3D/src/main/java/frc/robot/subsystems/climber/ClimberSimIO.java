package frc.robot.subsystems.climber;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

import static frc.robot.Constants.*;

public class ClimberSimIO implements ClimberIO {
// Math goes here
  private final double moment = 0.05;

  private static final double climberGearRadio = CLIMB_RADIO;

  private double inputClimberPower = 0;

  private DCMotor climberDCMotors = DCMotor.getNEO(2);
  private final DCMotorSim climberDCMotorSim;

  //private final DCMotorSim climberDCMotorSim = DCMotorSim(climberDCMotor,CLIMB_RADIO, moment);

  private boolean isBrake;

  private double appliedPower;

  public ClimberSimIO(){
    climberDCMotors = DCMotor.getCIM(2);
    climberDCMotorSim = new DCMotorSim(climberDCMotors,CLIMB_RADIO, moment);


    isBrake = false;
  }

  @Override
  public void updateInputs(ClimberIOInputs inputs) {
    inputs.climberisBrake = isBrake;
    inputs.climberisBrakeWench = isBrake;
    inputs.vertPos = climberDCMotorSim.getAngularPositionRad();
    inputs.vertPosWench = climberDCMotorSim.getAngularPositionRad();
    inputs.climberCurrent = climberDCMotorSim.getCurrentDrawAmps();
    inputs.climberCurrentWench = climberDCMotorSim.getCurrentDrawAmps();
    inputs.appliedPower = appliedPower;
    inputs.appliedPowerWench = appliedPower;
  }

}
