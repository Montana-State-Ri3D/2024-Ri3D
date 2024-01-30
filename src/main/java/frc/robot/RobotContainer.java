package frc.robot;

import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.climber.Climber;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.UnloadCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.StopShooterCommand;
import frc.robot.commands.TurboDriveCommand;
import frc.robot.subsystems.drivetrain.DriveTrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.utility.RobotIdentity;
import frc.robot.utility.SubsystemFactory;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static frc.robot.Constants.*;

public class RobotContainer {

  // Creating Controlers
  @SuppressWarnings({ "unused" })
  private final CommandXboxController driveController = new CommandXboxController(DRIVE_CONTROLLER_PORT);
  @SuppressWarnings({ "unused" })
  private final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
  @SuppressWarnings({ "unused" })
  private final CommandXboxController testController = new CommandXboxController(TEST_CONTROLLER_PORT);

  public DriveTrain driveTrainSubsystem;
  private Intake intakeSubsystem;
  private Shooter shooterSubsystem;
  private Arm armSubsystem;
  private Climber climberSubsystem;

  public DriveCommand defaultDriveCommand;
  public TurboDriveCommand turboDriveCommand;

  private SequentialCommandGroup shootRing;
  private SequentialCommandGroup intakeRing;
  private SequentialCommandGroup hiIntake;
  private SequentialCommandGroup climber;
  private SequentialCommandGroup feederPlace;
  private SequentialCommandGroup shootRingAuto;
  private SequentialCommandGroup autoShootDrive;

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
    defaultDriveCommand = new DriveCommand(driveTrainSubsystem,
        () -> driveController.getLeftTriggerAxis() - driveController.getRightTriggerAxis(),
        () -> -driveController.getLeftX());
    shootRing = new SequentialCommandGroup();  

    turboDriveCommand = new TurboDriveCommand(driveTrainSubsystem,
        () -> driveController.getLeftTriggerAxis() - driveController.getRightTriggerAxis(),
        () -> -driveController.getLeftX());

    driveTrainSubsystem.setDefaultCommand(defaultDriveCommand);

    

    shootRing.addCommands(new InstantCommand(() -> armSubsystem.setPosition("SHOOT")));
    // TODO Check if the arm is in the right position
    shootRing.addCommands(new ShootCommand(shooterSubsystem, 0.8, 0.8));
    shootRing.addCommands(new WaitCommand(2));
    shootRing.addCommands(new UnloadCommand(intakeSubsystem, () -> driveController.b().getAsBoolean()));
    shootRing.addCommands(new StopShooterCommand(shooterSubsystem));

    intakeRing = new SequentialCommandGroup();

    intakeRing.addCommands(new InstantCommand(() -> armSubsystem.setPosition("INTAKE")));
    intakeRing.addCommands(new IntakeCommand(intakeSubsystem, () -> driveController.b().getAsBoolean()));
    intakeRing.addCommands(new InstantCommand(() -> armSubsystem.setPosition("SHOOT")));

    hiIntake = new SequentialCommandGroup();

    hiIntake.addCommands(new InstantCommand(() -> armSubsystem.setPosition("HI_INTAKE")));
    hiIntake.addCommands(new IntakeCommand(intakeSubsystem, () -> driveController.b().getAsBoolean()));
    hiIntake.addCommands(new InstantCommand(() -> armSubsystem.setPosition("SHOOT")));

    feederPlace = new SequentialCommandGroup();

    feederPlace.addCommands(new InstantCommand(() -> armSubsystem.setPosition("AMP")));
    feederPlace.addCommands(new WaitCommand(1));
    feederPlace.addCommands(new UnloadCommand(intakeSubsystem, () -> driveController.b().getAsBoolean()));
    feederPlace.addCommands(new InstantCommand(() -> armSubsystem.setPosition("SHOOT")));

    climber = new SequentialCommandGroup();

    climber.addCommands(new InstantCommand(() -> armSubsystem.setPosition("CLIMB")));
    //climber.addCommands(new ClimberCommand(climberSubsystem, () -> driveController.getRightY(), armSubsystem,() -> driveController.b().getAsBoolean()));

    shootRingAuto = new SequentialCommandGroup();

    shootRingAuto.addCommands(new InstantCommand(() -> armSubsystem.setPosition("SHOOT")));
    // TODO Check if the arm is in the right position
    shootRingAuto.addCommands(new ShootCommand(shooterSubsystem, 0.8, 0.8));
    shootRingAuto.addCommands(new WaitCommand(2));
    shootRingAuto.addCommands(new UnloadCommand(intakeSubsystem, () -> false));
    shootRingAuto.addCommands(new StopShooterCommand(shooterSubsystem));
    
    autoShootDrive = new SequentialCommandGroup();

    autoShootDrive.addCommands(shootRingAuto);
    autoShootDrive.addCommands(new InstantCommand(() -> driveTrainSubsystem.drive(0.5, 0.5), driveTrainSubsystem));
    autoShootDrive.addCommands(new WaitCommand(3));
    autoShootDrive.addCommands(new InstantCommand(() -> driveTrainSubsystem.drive(0.0, 0.0), driveTrainSubsystem));

  }

  private void configureButtonBindings() {

    driveController.rightBumper().onTrue(shootRing);
    driveController.a().onTrue(intakeRing);
    operatorController.leftBumper().onTrue(climber);
    driveController.y().onTrue(feederPlace);
    driveController.x().onTrue(hiIntake);

    driveController.leftBumper().whileTrue(turboDriveCommand);

    driveController.povLeft().onTrue(new InstantCommand(() -> armSubsystem.setPosition("LATCH")));
    driveController.povUp().onTrue(new InstantCommand(() -> armSubsystem.setPosition("LATCHSTANDBY")));
    driveController.povDown().onTrue(new InstantCommand(() -> armSubsystem.setPosition("SHOOT")));
  }

  public Command getAutonomousCommand() {
    return autoShootDrive;
  }
}