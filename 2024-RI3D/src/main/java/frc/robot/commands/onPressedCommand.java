package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class onPressedCommand extends CommandBase {

    private BooleanSupplier cancel;

    public onPressedCommand(BooleanSupplier cancel){
        this.cancel = cancel;

    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (cancel.getAsBoolean());
  }
}