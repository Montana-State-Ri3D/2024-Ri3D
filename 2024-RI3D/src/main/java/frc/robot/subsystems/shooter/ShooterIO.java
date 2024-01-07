package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface ShooterIO {
    @AutoLog
    class ShooterIOInputs{
        public double leftCurent;
        public double rightCurent;
        public double leftVelocity;
        public double rightVelocity;
        public double wristAngle;
        public double wristSetPoint;
    }
    default void updateInputs(ShooterIOInputs inputs){}
    default void setPowers(double leftPower, double rightPower) {}
    default void setWristAngle(double angle){}

}