package frc.robot.subsystems.arm;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

/** Add your docs here. */
public class ArmRealIO implements ArmIO {

        boolean isBrake;
        double targetAngle;

        private CANSparkMax arm;
        private SparkMaxPIDController armPIDController;
        private SparkMaxAbsoluteEncoder armAbsoluteEncoder;




        public ArmRealIO() {
            arm = new CANSparkMax(ARM_MOTOR, MotorType.kBrushless);
            armPIDController = arm.getPIDController();
            armAbsoluteEncoder = arm.getAbsoluteEncoder(Type.kDutyCycle);

            armAbsoluteEncoder.setZeroOffset(ARM_MOTOR);
            
            armPIDController.setP(0.1);
            armPIDController.setI(0);
            armPIDController.setD(0);

            armPIDController.setFeedbackDevice(armAbsoluteEncoder);
            armPIDController.setOutputRange(-1, 1);
        }


        public void updateInputs(ArmIOInputs inputs) {
            inputs.isBrake = isBrake;
            inputs.curent = arm.getOutputCurrent();
            inputs.curentAngle = armAbsoluteEncoder.getPosition();
            inputs.velocity = armAbsoluteEncoder.getVelocity();
            inputs.targetAngle = targetAngle;
        }

        public void setBreakMode(boolean isBrake) {
            this.isBrake = isBrake;
        }

        public void setAngle(double angle) {
            targetAngle = angle;    
        }

}
