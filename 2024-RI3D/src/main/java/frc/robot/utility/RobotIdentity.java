package frc.robot.utility;

import edu.wpi.first.wpilibj.RobotBase;

public enum RobotIdentity {
    ROBOT_2024,
    SIMULATION;
    /**
     * Gets the identity of the robot.
     *
     * @return The detected identity of the robot.
     */
    public static RobotIdentity getIdentity() {
        // When we're running on a real robot we'll base the identity on its MAC address
        if (RobotBase.isReal()) {
            return ROBOT_2024;
        }
        

        // Otherwise we know we're running in a simulation
        return SIMULATION;
    }
}
