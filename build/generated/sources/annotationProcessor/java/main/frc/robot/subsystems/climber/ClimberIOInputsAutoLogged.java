package frc.robot.subsystems.climber;

import java.lang.Cloneable;
import java.lang.Override;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class ClimberIOInputsAutoLogged extends ClimberIO.ClimberIOInputs implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("ClimberisBrakeWench", climberisBrakeWench);
    table.put("ClimberCurrentWench", climberCurrentWench);
    table.put("AngularPosWench", angularPosWench);
    table.put("AppliedPowerWench", appliedPowerWench);
  }

  @Override
  public void fromLog(LogTable table) {
    climberisBrakeWench = table.get("ClimberisBrakeWench", climberisBrakeWench);
    climberCurrentWench = table.get("ClimberCurrentWench", climberCurrentWench);
    angularPosWench = table.get("AngularPosWench", angularPosWench);
    appliedPowerWench = table.get("AppliedPowerWench", appliedPowerWench);
  }

  public ClimberIOInputsAutoLogged clone() {
    ClimberIOInputsAutoLogged copy = new ClimberIOInputsAutoLogged();
    copy.climberisBrakeWench = this.climberisBrakeWench;
    copy.climberCurrentWench = this.climberCurrentWench;
    copy.angularPosWench = this.angularPosWench;
    copy.appliedPowerWench = this.appliedPowerWench;
    return copy;
  }
}
