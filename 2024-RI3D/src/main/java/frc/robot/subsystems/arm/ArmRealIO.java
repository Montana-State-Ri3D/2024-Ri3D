package frc.robot.subsystems.arm;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/** Add your docs here. */
public class ArmRealIO implements ArmIO {

        boolean isBrake;
        double targetAngle;

        private CANSparkMax arm;
        private RelativeEncoder armEncoder;
        private SparkMaxPIDController armPIDController;
        private 


        public ArmRealIO() {
            arm = new CANSparkMax(ARM_MOTOR, MotorType.kBrushless);

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
