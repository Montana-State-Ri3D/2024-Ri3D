package frc.robot.subsystems.shooter;

import java.lang.Cloneable;
import java.lang.Override;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class ShooterIOInputsAutoLogged extends ShooterIO.ShooterIOInputs implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("LeftCurent", leftCurent);
    table.put("RightCurent", rightCurent);
    table.put("LeftVelocity", leftVelocity);
    table.put("RightVelocity", rightVelocity);
    table.put("LeftPosition", leftPosition);
    table.put("RightPosition", rightPosition);
  }

  @Override
  public void fromLog(LogTable table) {
    leftCurent = table.get("LeftCurent", leftCurent);
    rightCurent = table.get("RightCurent", rightCurent);
    leftVelocity = table.get("LeftVelocity", leftVelocity);
    rightVelocity = table.get("RightVelocity", rightVelocity);
    leftPosition = table.get("LeftPosition", leftPosition);
    rightPosition = table.get("RightPosition", rightPosition);
  }

  public ShooterIOInputsAutoLogged clone() {
    ShooterIOInputsAutoLogged copy = new ShooterIOInputsAutoLogged();
    copy.leftCurent = this.leftCurent;
    copy.rightCurent = this.rightCurent;
    copy.leftVelocity = this.leftVelocity;
    copy.rightVelocity = this.rightVelocity;
    copy.leftPosition = this.leftPosition;
    copy.rightPosition = this.rightPosition;
    return copy;
  }
}
