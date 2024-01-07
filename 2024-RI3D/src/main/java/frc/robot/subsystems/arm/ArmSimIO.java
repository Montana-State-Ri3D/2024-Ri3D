package frc.robot.subsystems.arm;

/** Add your docs here. */
public class ArmSimIO implements ArmIO {

        boolean isBrake;
        double targetAngle;

        public ArmSimIO() {
        }


        public void updateInputs(ArmIOInputs inputs) {
            inputs.isBrake = isBrake;
            inputs.curent = 0;
            inputs.curentAngle = 0;
            inputs.velocity = 0;
            inputs.targetAngle = targetAngle;
        }

        public void setBreakMode(boolean isBrake) {
            this.isBrake = isBrake;
        }

        public void setAngle(double angle) {
            targetAngle = angle;    
        }

}
