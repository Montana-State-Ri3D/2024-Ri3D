package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface ArmIO {
    @AutoLog
    class ArmIOInputs{
        public boolean isBrake;
        public double curent;
        public double curentAngle;
        public double velocity;
        public double targetAngle;
        public double appliedPower;
        public double relativePos_l;
        public double relativePos_r;
    }

    default void updateInputs(ArmIOInputs inputs){}
    default void setBreakMode(boolean isBrake){}
    default void setAngle(double angle){}
}