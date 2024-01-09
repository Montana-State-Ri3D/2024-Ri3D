package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.climber.Climber;
import frc.robot.utility.Joystick;


public class ClimberCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  private final Climber climberSubsystem;
  private final Arm armSubsystem;

  private final DoubleSupplier wenchSpeed;
  private final BooleanSupplier cancel;
  private double wenchPower;

  public ClimberCommand(Climber climberSubsystem, DoubleSupplier wenchSpeed, Arm armSubsystem, BooleanSupplier cancle) {
    this.climberSubsystem = climberSubsystem;
    this.wenchSpeed = wenchSpeed;
    this.armSubsystem = armSubsystem;
    this.cancel = cancle;
    addRequirements(climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climberSubsystem.setWinchPower(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (armSubsystem.getAngle() >= Units.degreesToRadians(277)) {
      wenchPower = 0;
      } else {
      wenchPower = Joystick.JoystickInput(wenchSpeed.getAsDouble(), 2, 0.02, .75);
    }
    climberSubsystem.setWinchPower(wenchPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.setWinchPower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cancel.getAsBoolean();
  }
}