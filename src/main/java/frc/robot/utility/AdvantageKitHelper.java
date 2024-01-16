package frc.robot.utility;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;

import frc.robot.Robot;

public class AdvantageKitHelper {

    /**
     * setupLogger - Configure AdvantageKit logging for the robot.
     * 
     * @param doNetworkLogging - Boolean flag telling whether to log to the
     *                         NetworkTables
     * @return The instance of the logger
     */
    public static void setupLogger(boolean doNetworkLogging) {

        // If this is a physical robot (with a Rio) then we can log to a USB drive.
        if (Robot.isReal()) {
            //logger.addDataReceiver(new WPILOGWriter("/media/sda1/"));
        }

        // We don't add the NT4 receiver in competition matches to reduce network
        // traffic.
        if (doNetworkLogging) {
            Logger.addDataReceiver(new NT4Publisher());
        }
    }
}
