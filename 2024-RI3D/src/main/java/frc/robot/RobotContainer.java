package frc.robot;

import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.climber.Climber;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.UnloadCommand;
import frc.robot.commands.Shoot;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.StopShooterCommand;
import frc.robot.subsystems.drivetrain.DriveTrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.utility.RobotIdentity;
import frc.robot.utility.SubsystemFactory;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

public class RobotContainer {

  // Creating Controlers
  @SuppressWarnings({ "unused" })
  private final CommandXboxController driveController = new CommandXboxController(DRIVE_CONTROLLER_PORT);
  @SuppressWarnings({ "unused" })
  private final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
  @SuppressWarnings({ "unused" })
  private final CommandXboxController testController = new CommandXboxController(TEST_CONTROLLER_PORT);

  private DriveTrain driveTrainSubsystem;
  private Intake intakeSubsystem;
  private Shooter shooterSubsystem;
  private Arm armSubsystem;
  private Climber climberSubsystem;

  private DriveCommand driveCommand;

  private SequentialCommandGroup shootRing;
  private SequentialCommandGroup intakeRing;
  private SequentialCommandGroup climber;

  private DoubleSupplier wenchSpeed;

  private RobotIdentity identity;

  public RobotContainer() {
    identity = RobotIdentity.getIdentity();

    createSubsystems(); // Create our subsystems.
    createCommands(); // Create our commands
    configureButtonBindings(); // Configure the button bindings
  }

  private void createSubsystems() {
    driveTrainSubsystem = SubsystemFactory.createDriveTrain(identity);
    climberSubsystem = SubsystemFactory.createClimber(identity);
    intakeSubsystem = SubsystemFactory.createIntake(identity);
    shooterSubsystem = SubsystemFactory.createShooter(identity);
    armSubsystem = SubsystemFactory.createArm(identity);
  }

  private void createCommands() {
    driveCommand = new DriveCommand(driveTrainSubsystem,
        () -> driveController.getLeftTriggerAxis() - driveController.getRightTriggerAxis(),
        () -> -driveController.getLeftX());
    driveTrainSubsystem.setDefaultCommand(driveCommand);

    //climberCommand = new ClimberCommand(climberSubsystem,
    //    () -> -operatorController.getLeftY());
    //climberSubsystem.setDefaultCommand(climberCommand);

    shootRing = new SequentialCommandGroup();

    shootRing.addCommands(new ShootCommand(shooterSubsystem));
    shootRing.addCommands(new UnloadCommand(intakeSubsystem, () -> driveController.b().getAsBoolean()));
    shootRing.addCommands(new StopShooterCommand(shooterSubsystem));

    intakeRing = new SequentialCommandGroup();

    intakeRing.addCommands(new InstantCommand(() ->armSubsystem.setPosition("INTAKE")));
    intakeRing.addCommands(new IntakeCommand(intakeSubsystem, () -> driveController.b().getAsBoolean()));
    intakeRing.addCommands(new InstantCommand(() ->armSubsystem.setPosition("SHOOT")));

    climber = new SequentialCommandGroup();
    
    climber.addCommands(new InstantCommand(() ->armSubsystem.setPosition("AMP")));
    climber.addCommands(new InstantCommand(() ->driveController.a().onTrue(new InstantCommand(() ->armSubsystem.setPosition("LATCH")))));
    climber.addCommands(new ClimberCommand(climberSubsystem, () -> -operatorController.getLeftY()));
    
    
    
  }



  private void configureButtonBindings() {

    //driveController.rightBumper().onTrue(new Shoot(shooterSubsystem));

    // Toggle Brake Mode with A
    driveController.b().onTrue(new InstantCommand(() -> armSubsystem.setArmAngle(Units.degreesToRadians(90)), driveTrainSubsystem));

    driveController.a().onTrue(new InstantCommand(() -> armSubsystem.setArmAngle(Units.degreesToRadians(0)), driveTrainSubsystem));

    driveController.y().onTrue(new InstantCommand(() -> armSubsystem.setArmAngle(Units.degreesToRadians(180)), driveTrainSubsystem));

    driveController.a().onTrue(new UnloadCommand(intakeSubsystem, () -> driveController.b().getAsBoolean()));

    driveController.rightBumper().onTrue(shootRing);
    
    driveController.leftBumper().onTrue(intakeRing);

    //testController.x().onTrue(new InstantCommand(() -> climberSubsystem.setBarPos(0), climberSubsystem));

    //testController.b().onTrue(new InstantCommand(() -> climberSubsystem.setBarPos(Math.PI*8), climberSubsystem));
  }


  public Command getAutonomousCommand() {
    return null;
  }
}