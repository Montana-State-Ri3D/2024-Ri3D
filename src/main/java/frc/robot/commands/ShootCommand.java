package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.intake.Intake;

public class ShootCommand extends Command {
  Shooter shooter;
  Intake intake;


  public ShootCommand(Shooter shooter, double Lpower,double Rpower) {
    addRequirements(shooter);

    this.shooter = shooter;

    shooter.setPowers(Lpower, Rpower);
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
    return true;
  }
}
