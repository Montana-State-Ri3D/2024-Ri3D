package frc.robot.subsystems.climber;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

import static frc.robot.Constants.*;

public class ClimberSimIO implements ClimberIO {
// Math goes here
  private final double moment = 0.05;

  private static final double climberGearRadio = CLIMB_RADIO;

  private double inputClimberPower = 0;

  private DCMotor climberDCMotor = DCMotor.getNEO(1);
  private final DCMotorSim climberDCMotorSim;

  //private final DCMotorSim climberDCMotorSim = DCMotorSim(climberDCMotor,CLIMB_RADIO, moment);

  private boolean isBrake;

  private double appliedPower;

  public ClimberSimIO(){
    climberDCMotor = DCMotor.getCIM(1);
    climberDCMotorSim = new DCMotorSim(climberDCMotor,CLIMB_RADIO, moment);


    isBrake = false;
  }

  @Override
  public void updateInputs(ClimberIOInputs inputs) {
    inputs.climberisBrake = isBrake;
    inputs.vertPos = climberDCMotorSim.getAngularPositionRad();
    inputs.climberCurrent = climberDCMotorSim.getCurrentDrawAmps();
    inputs.appliedPower = appliedPower;
  }





}
