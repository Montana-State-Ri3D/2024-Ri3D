package frc.robot.subsystems.intake;

import java.lang.Cloneable;
import java.lang.Override;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class IntakeIOInputsAutoLogged extends IntakeIO.IntakeIOInputs implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("IsBrake", isBrake);
    table.put("Curent", curent);
    table.put("Velocity", velocity);
    table.put("BeamBreak", beamBreak);
    table.put("Position", position);
  }

  @Override
  public void fromLog(LogTable table) {
    isBrake = table.get("IsBrake", isBrake);
    curent = table.get("Curent", curent);
    velocity = table.get("Velocity", velocity);
    beamBreak = table.get("BeamBreak", beamBreak);
    position = table.get("Position", position);
  }

  public IntakeIOInputsAutoLogged clone() {
    IntakeIOInputsAutoLogged copy = new IntakeIOInputsAutoLogged();
    copy.isBrake = this.isBrake;
    copy.curent = this.curent;
    copy.velocity = this.velocity;
    copy.beamBreak = this.beamBreak;
    copy.position = this.position;
    return copy;
  }
}
