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
        public double leftPosition;
        public double rightPosition;
    }
    default void updateInputs(ShooterIOInputs inputs){}
    default void setPowers(double leftPower, double rightPower) {}
    default void setRPS(double leftRPS, double rightRPS) {}
}