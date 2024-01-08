package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.intake.Intake;

public class ShootCommand extends CommandBase {
  Shooter shooter;
  Intake intake;

  private double RPS = 6000;

  public ShootCommand(Shooter shooter) {
    addRequirements(shooter);

    this.shooter = shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.setPowers(12, 12);
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
    if (shooter.getLRPS() > RPS && shooter.getRRPS() > RPS) {
      return true;
    } else {
      return false;
    }
  }
}
