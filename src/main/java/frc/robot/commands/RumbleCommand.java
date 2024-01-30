package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;

public class RumbleCommand extends Command {
  private int Power;
  private final XboxController HID = new XboxController(0);

/** Creates a new DriveCommand. */

  public RumbleCommand(int Power) {
    this.Power = Power;

    HID.setRumble(RumbleType.kLeftRumble, Power);

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
    HID.setRumble(RumbleType.kLeftRumble, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
