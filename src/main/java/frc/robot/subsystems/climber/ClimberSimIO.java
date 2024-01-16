package frc.robot.subsystems.climber;

public class ClimberSimIO implements ClimberIO {
// Math goes here

  /* private DCMotor climberDCMotor = DCMotor.getNEO(1);
  private final DCMotorSim climberDCMotorSim;

  private DCMotor climberDCWench = DCMotor.getNEO(1);
  private final DCMotorSim climberDCWenchSim; */

  private double inputWenchPower = 0;


  //private final DCMotorSim climberDCMotorSim = DCMotorSim(climberDCMotor,CLIMB_RADIO, moment);

  private boolean isBrakeWench;

  public ClimberSimIO() {
  }
  @Override
  public void updateInputs(ClimberIOInputs inputs) {
    inputs.climberisBrakeWench = isBrakeWench;
    inputs.climberCurrentWench = 0;
    inputs.appliedPowerWench = inputWenchPower;
    inputs.angularPosWench = 0;
  }

  @Override
  public void setWenchPower(double power) {
    inputWenchPower = power;
    //climberDCWenchSim.setInputVoltage(power);
    //climberDCWenchSim.update(0.020);  
  }
}