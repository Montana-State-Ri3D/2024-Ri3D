package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.climber.Climber;
import frc.robot.utility.Joystick;


public class ClimberCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  private final Climber climberSubsystem;

  private final DoubleSupplier wenchSpeed;
  private double wenchPower;

  public ClimberCommand(Climber climberSubsystem, DoubleSupplier wenchSpeed) {
    this.climberSubsystem = climberSubsystem;
    this.wenchSpeed = wenchSpeed;
    addRequirements(climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climberSubsystem.setWinchPower(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    wenchPower = Joystick.JoystickInput(wenchSpeed.getAsDouble(), 2, 0.02, .75);
    System.out.println(wenchPower);
    climberSubsystem.setWinchPower(wenchPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.setWinchPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}