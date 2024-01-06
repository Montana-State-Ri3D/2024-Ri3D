package frc.robot.utility;

import static frc.robot.Constants.*;

import frc.robot.subsystems.drivetrain.DriveTrain;
import frc.robot.subsystems.drivetrain.DriveTrainRealIO;
import frc.robot.subsystems.drivetrain.DriveTrainSimIO;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeSimIO;


public final class SubsystemFactory {

    public static DriveTrain createDriveTrain(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new DriveTrain(new DriveTrainSimIO());
            default:
                return new DriveTrain(new DriveTrainRealIO(LEFT_FRONT_MOTOR, LEFT_BACK_MOTOR, RIGHT_FRONT_MOTOR,RIGHT_BACK_MOTOR));
            
        }
    }
    public static Intake createIntake(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Intake(new IntakeSimIO());
            default:
                return null;
            
        }
    }
}
