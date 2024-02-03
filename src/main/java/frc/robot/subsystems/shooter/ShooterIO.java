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
        public double leftAppliedPower;
        public double rightApppliedPower;
        public double setPoint;
    }
    default void updateInputs(ShooterIOInputs inputs){}
    default void setPowers(double leftPower, double rightPower) {}
    default void setRPS(double leftRPS, double rightRPS) {}
}