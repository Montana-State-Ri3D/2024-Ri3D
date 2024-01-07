package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.climber.Climber;


public class ClimberCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  private final Climber climberSubsystem;

  private long initTime = -1;
  private final long climbDuration = 500;

  public ClimberCommand(Climber climberSubsystem) {
    this.climberSubsystem = climberSubsystem;
    addRequirements(climberSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climberSubsystem.setWinchPower(0.5);
    initTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.setWinchPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    long currentTime = System.currentTimeMillis();
    if (currentTime >= initTime + climbDuration)
      return true;
    else
      ;
    return false;
  }
}