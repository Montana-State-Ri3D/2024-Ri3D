package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.climber.ClimberIO;
import frc.robot.subsystems.climber.ClimberRealIO;
import frc.robot.subsystems.climber.ClimberSimIO;


import frc.robot.utility.Joystick;

public class ClimberCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  private final Climber ClimberSubsystem;
  private final DoubleSupplier motorPower;
  private final DoubleSupplier wenchPower;

  public ClimberCommand(Climber ClimberSubsystem, DoubleSupplier motorPower, DoubleSupplier wenchPower) {
    this.ClimberSubsystem = ClimberSubsystem;
    this.motorPower = motorPower;
    this.wenchPower = wenchPower;
    addRequirements(ClimberSubsystem);

  }

  public ClimberCommand(Climber climberSubsystem2, int i, int j) {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ClimberSubsystem.climb(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ClimberSubsystem.climb(motorPower.getAsDouble()/4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}