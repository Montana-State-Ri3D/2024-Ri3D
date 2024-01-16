package frc.robot.subsystems.drivetrain;

import java.lang.Cloneable;
import java.lang.Override;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class DriveTrainIOInputsAutoLogged extends DriveTrainIO.DriveTrainIOInputs implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("IsBrake", isBrake);
    table.put("LeftCurent", leftCurent);
    table.put("RightCurent", rightCurent);
    table.put("LeftPos", leftPos);
    table.put("RightPos", rightPos);
    table.put("DistTraveled", distTraveled);
  }

  @Override
  public void fromLog(LogTable table) {
    isBrake = table.get("IsBrake", isBrake);
    leftCurent = table.get("LeftCurent", leftCurent);
    rightCurent = table.get("RightCurent", rightCurent);
    leftPos = table.get("LeftPos", leftPos);
    rightPos = table.get("RightPos", rightPos);
    distTraveled = table.get("DistTraveled", distTraveled);
  }

  public DriveTrainIOInputsAutoLogged clone() {
    DriveTrainIOInputsAutoLogged copy = new DriveTrainIOInputsAutoLogged();
    copy.isBrake = this.isBrake;
    copy.leftCurent = this.leftCurent;
    copy.rightCurent = this.rightCurent;
    copy.leftPos = this.leftPos;
    copy.rightPos = this.rightPos;
    copy.distTraveled = this.distTraveled;
    return copy;
  }
}
