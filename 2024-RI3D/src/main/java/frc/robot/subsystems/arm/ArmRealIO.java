package frc.robot.subsystems.arm;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

/** Add your docs here. */
public class ArmRealIO implements ArmIO {

        boolean isBrake;
        double targetAngle;

        private CANSparkMax arm;
        private SparkMaxPIDController armPIDController;
        private SparkMaxAbsoluteEncoder armAbsoluteEncoder;

        public ArmRealIO(int armCANID) {
            arm = new CANSparkMax(armCANID, MotorType.kBrushless);
            armPIDController = arm.getPIDController();
            armAbsoluteEncoder = arm.getAbsoluteEncoder(Type.kDutyCycle);

            armAbsoluteEncoder.setZeroOffset(ARM_Offset);

            armAbsoluteEncoder.setPositionConversionFactor(Math.PI*2);
            armAbsoluteEncoder.setVelocityConversionFactor(Math.PI*2/60);
            
            armPIDController.setP(0.2);
            armPIDController.setI(0);
            armPIDController.setD(0);

            armPIDController.setFeedbackDevice(armAbsoluteEncoder);
            armPIDController.setOutputRange(-1, 1);

            arm.setIdleMode(CANSparkMax.IdleMode.kBrake);
            isBrake = true;
        }


        public void updateInputs(ArmIOInputs inputs) {
            inputs.isBrake = isBrake;
            inputs.curent = arm.getOutputCurrent();
            inputs.curentAngle = armAbsoluteEncoder.getPosition();
            inputs.velocity = armAbsoluteEncoder.getVelocity();
            inputs.targetAngle = targetAngle;
            inputs.appliedPower = arm.getAppliedOutput();
        }

        public void setBreakMode(boolean isBrake) {
            this.isBrake = isBrake;
            if (isBrake) {
                arm.setIdleMode(CANSparkMax.IdleMode.kBrake);
            } else {
                arm.setIdleMode(CANSparkMax.IdleMode.kCoast);
            }
        }

        public void setAngle(double angle) {
            targetAngle = angle;
            armPIDController.setReference(angle, ControlType.kPosition);
        }

}
