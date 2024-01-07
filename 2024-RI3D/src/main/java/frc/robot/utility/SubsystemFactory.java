package frc.robot.utility;

import static frc.robot.Constants.*;

import frc.robot.subsystems.drivetrain.DriveTrain;
import frc.robot.subsystems.drivetrain.DriveTrainRealIO;
import frc.robot.subsystems.drivetrain.DriveTrainSimIO;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeSimIO;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterRealIO;

import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.climber.ClimberRealIO;
import frc.robot.subsystems.climber.ClimberSimIO;


public final class SubsystemFactory {

    public static DriveTrain createDriveTrain(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new DriveTrain(new DriveTrainSimIO());
            default:
                //return new DriveTrain(new DriveTrainRealIO(LEFT_FRONT_MOTOR, LEFT_BACK_MOTOR, RIGHT_FRONT_MOTOR,RIGHT_BACK_MOTOR));
                return new DriveTrain(new DriveTrainSimIO());
        }
    }
    public static Intake createIntake(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Intake(new IntakeSimIO());
            default:
                return new Intake(new IntakeSimIO());
            
        }
    }
    public static Shooter createShooter(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return null;
            default:
                return new Shooter(new ShooterRealIO(SHOOTER_LEFT, SHOOTER_RIGHT, SHOOTER_WRIST));
            
        }
    }

    public static Climber createClimber(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Climber(new ClimberSimIO());
            default:
                return new Climber(new ClimberRealIO(CLIMBER_MOTOR, CLIMBER_WENCH_MOTOR));
            
        }
    }
}
