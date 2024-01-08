package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface IntakeIO {

    @AutoLog
    class IntakeIOInputs{
        public boolean isBrake;
        public double curent;
        public double velocity;
        public boolean beamBreak;
    }
    default void updateInputs(IntakeIOInputs inputs){}
    default void setPower(double power) {}
    default void setBrake(boolean brake) {}
}