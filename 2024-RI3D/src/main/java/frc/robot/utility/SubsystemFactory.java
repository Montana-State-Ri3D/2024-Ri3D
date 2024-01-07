package frc.robot.utility;

import static frc.robot.Constants.*;

import frc.robot.subsystems.drivetrain.DriveTrain;
import frc.robot.subsystems.drivetrain.DriveTrainRealIO;
import frc.robot.subsystems.drivetrain.DriveTrainSimIO;

import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.climber.ClimberRealIO;
import frc.robot.subsystems.climber.ClimberSimIO;


public final class SubsystemFactory {

    public static DriveTrain createDriveTrain(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new DriveTrain(new DriveTrainSimIO());
            default:
                return new DriveTrain(new DriveTrainRealIO(LEFT_FRONT_MOTOR, LEFT_BACK_MOTOR, RIGHT_FRONT_MOTOR,RIGHT_BACK_MOTOR));
            
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
