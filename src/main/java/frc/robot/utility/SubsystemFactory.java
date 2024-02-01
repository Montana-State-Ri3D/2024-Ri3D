package frc.robot.utility;

import static frc.robot.Constants.*;

import frc.robot.Camera;
import frc.robot.subsystems.drivetrain.DriveTrain;
import frc.robot.subsystems.drivetrain.DriveTrainRealIO;
import frc.robot.subsystems.drivetrain.DriveTrainSimIO;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeRealIO;
import frc.robot.subsystems.intake.IntakeSimIO;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterRealIO;
import frc.robot.subsystems.shooter.ShooterSimIO;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.ArmRealIO;
import frc.robot.subsystems.arm.ArmSimIO;
import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.climber.ClimberRealIO;
import frc.robot.subsystems.climber.ClimberSimIO;

public final class SubsystemFactory {

    public static DriveTrain createDriveTrain(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new DriveTrain(new DriveTrainSimIO());
            case ROBOT_2024:
                return new DriveTrain(
                        new DriveTrainRealIO(LEFT_FRONT_MOTOR, LEFT_BACK_MOTOR, RIGHT_FRONT_MOTOR, RIGHT_BACK_MOTOR));
            default:
                System.out.println("Not Valid Robot Identity");
                return new DriveTrain(new DriveTrainSimIO());
        }
    }

    public static Intake createIntake(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Intake(new IntakeSimIO());
            case ROBOT_2024:
                return new Intake(new IntakeRealIO(INTAKE_MOTOR, INTAKE_BEAM_BREAK));

            default:
                System.out.println("Not Valid Robot Identity");
                return new Intake(new IntakeSimIO());
        }
    }

    public static Shooter createShooter(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Shooter(new ShooterSimIO());

            case ROBOT_2024:
                return new Shooter(new ShooterRealIO(SHOOTER_LEFT, SHOOTER_RIGHT));

            default:
                System.out.println("Not Valid Robot Identity");
                return new Shooter(new ShooterSimIO());

        }
    }

    public static Climber createClimber(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Climber(new ClimberSimIO());
            case ROBOT_2024:
                return new Climber(new ClimberRealIO(CLIMBER_WENCH_MOTOR));
            default:
                System.out.println("Not Valid Robot Identity");
                return new Climber(new ClimberSimIO());

        }
    }

    public static Arm createArm(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Arm(new ArmSimIO());

            case ROBOT_2024:
                return new Arm(new ArmRealIO(ARM_MOTOR_LEFT, ARM_MOTOR_RIGHT));
            default:
                System.out.println("Not Valid Robot Identity");
                return new Arm(new ArmSimIO());
        }
    }

    public static Camera createCamera(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Camera();

            case ROBOT_2024:
                return new Camera();

            default:
                System.out.println("Not Valid Robot Identity");
                return new Camera();
        }
    }
}
