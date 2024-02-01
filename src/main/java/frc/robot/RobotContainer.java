package frc.robot;

import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.climber.Climber;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.UnloadCommand;
import frc.robot.commands.WaitCommandWithExit;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.StopShooterCommand;
import frc.robot.commands.TurboDriveCommand;
import frc.robot.subsystems.drivetrain.DriveTrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.utility.AutoCommandChooser;
import frc.robot.utility.RobotIdentity;
import frc.robot.utility.SubsystemFactory;
import frc.robot.Camera;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static frc.robot.Constants.*;

@SuppressWarnings("unused")
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

  private AutoCommandChooser autoChooser;

  @SuppressWarnings("unused")
  private Camera camera;

  public DriveCommand defaultDriveCommand;
  public TurboDriveCommand turboDriveCommand;

  private SequentialCommandGroup shootRing;
  private SequentialCommandGroup intakeRing;
  private SequentialCommandGroup hiIntake;
  private SequentialCommandGroup climber;
  private SequentialCommandGroup feederPlace;
  private SequentialCommandGroup shootRingAuto;
  private SequentialCommandGroup autoShootDrive;
  private SequentialCommandGroup autoAmpBlue;
  

  private RobotIdentity identity;

  public RobotContainer() {
    identity = RobotIdentity.getIdentity();

    createSubsystems(); // Create our subsystems.
    createCommands(); // Create our commands
    configureButtonBindings(); // Configure the button bindings
    createAutoCommand();
  }

  private void createSubsystems() {
    driveTrainSubsystem = SubsystemFactory.createDriveTrain(identity);
    climberSubsystem = SubsystemFactory.createClimber(identity);
    intakeSubsystem = SubsystemFactory.createIntake(identity);
    shooterSubsystem = SubsystemFactory.createShooter(identity);
    armSubsystem = SubsystemFactory.createArm(identity);
    camera = SubsystemFactory.createCamera(identity);
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

    
    shootRing.addCommands(new InstantCommand(() -> driveTrainSubsystem.setLowCurrentMode()));
    shootRing.addCommands(new InstantCommand(() -> armSubsystem.setPosition("SHOOT")));
    shootRing.addCommands(new InstantCommand(() -> shooterSubsystem.setPowers(0.9, 0.9)));
    shootRing.addCommands(new WaitCommandWithExit(2, () -> driveController.b().getAsBoolean()));
    shootRing.addCommands(new UnloadCommand(intakeSubsystem, () -> driveController.b().getAsBoolean()));
    shootRing.addCommands(new InstantCommand(() -> driveTrainSubsystem.setHighCurrentMode()));
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
    climber.addCommands(new ClimberCommand(climberSubsystem, () -> driveController.getRightY(), armSubsystem,() -> driveController.b().getAsBoolean()));

    shootRingAuto = new SequentialCommandGroup();

    shootRingAuto.addCommands(new InstantCommand(() -> armSubsystem.setPosition("SHOOT")));
    shootRingAuto.addCommands(new InstantCommand(() -> shooterSubsystem.setPowers(0.9, 0.9)));
    shootRingAuto.addCommands(new WaitCommand(2));
    shootRingAuto.addCommands(new UnloadCommand(intakeSubsystem, () -> false));
    shootRingAuto.addCommands(new StopShooterCommand(shooterSubsystem));
    
    autoShootDrive = new SequentialCommandGroup();

    autoShootDrive.addCommands(shootRingAuto);
    autoShootDrive.addCommands(new InstantCommand(() -> driveTrainSubsystem.drive(0.25, 0.25), driveTrainSubsystem));
    autoShootDrive.addCommands(new WaitCommand(2));
    autoShootDrive.addCommands(new InstantCommand(() -> driveTrainSubsystem.drive(0.0, 0.0), driveTrainSubsystem));

    autoAmpBlue = new SequentialCommandGroup();
    autoAmpBlue.addCommands(new InstantCommand(() -> driveTrainSubsystem.drive(0.4, 0.4), driveTrainSubsystem));
    autoAmpBlue.addCommands(new WaitCommand(0.5));
    autoAmpBlue.addCommands(new InstantCommand(() -> driveTrainSubsystem.drive(-0.4, 0.4), driveTrainSubsystem));
    autoAmpBlue.addCommands(new WaitCommand(0.6));
    autoAmpBlue.addCommands(new InstantCommand(() -> driveTrainSubsystem.drive(0.0, 0.0), driveTrainSubsystem));

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

  private void createAutoCommand(){
    autoChooser = new AutoCommandChooser();

    // Register all the supported auto commands
    autoChooser.registerDefaultCreator("Do Nothing", null);

    autoChooser.registerCreator("Drive Forward", () -> autoShootDrive);
    autoChooser.registerCreator("Amp Side Left (Blue)", () -> autoAmpBlue);
    autoChooser.registerCreator("Amp Side Right (Red)", null);

    // Setup the chooser in shuffleboard
    autoChooser.setup("Driver", 0, 0, 3, 1);

  }

  public Command getAutonomousCommand() {
    return autoAmpBlue;
  }
}