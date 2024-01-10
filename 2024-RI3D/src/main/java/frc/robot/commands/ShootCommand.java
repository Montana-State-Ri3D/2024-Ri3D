package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.intake.Intake;

public class ShootCommand extends CommandBase {
  Shooter shooter;
  Intake intake;

  private double Lpower;
  private double Rpower;

  public ShootCommand(Shooter shooter, double Lpower,double Rpower) {
    addRequirements(shooter);
    System.out.println("Creating Shoot");

    this.Lpower = Lpower;
    this.Rpower = Rpower;

    this.shooter = shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.setPowers(Lpower, Rpower);
    System.out.println("Spining up");
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
