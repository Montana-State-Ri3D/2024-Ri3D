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

        private CANSparkMax armMotor_l;
        private CANSparkMax armMotor_r;
        private SparkMaxPIDController armPIDController;
        private SparkMaxAbsoluteEncoder armAbsoluteEncoder;

        public ArmRealIO(int IDleftMotor, int IDrightMotor) {
            armMotor_l = new CANSparkMax(IDleftMotor, MotorType.kBrushless);
            armMotor_r = new CANSparkMax(IDrightMotor, MotorType.kBrushless);

            armPIDController = armMotor_l.getPIDController();
            armAbsoluteEncoder = armMotor_l.getAbsoluteEncoder(Type.kDutyCycle);

            armMotor_r.follow(armMotor_l);

            armAbsoluteEncoder.setZeroOffset(ARM_Offset);

            armAbsoluteEncoder.setPositionConversionFactor(Math.PI*2);
            armAbsoluteEncoder.setVelocityConversionFactor(Math.PI*2/60);
            
            armPIDController.setP(0.2);
            armPIDController.setI(0);
            armPIDController.setD(0);

            armPIDController.setFeedbackDevice(armAbsoluteEncoder);
            armPIDController.setOutputRange(-1, 1);

            armMotor_l.setIdleMode(CANSparkMax.IdleMode.kBrake);
            isBrake = true;
        }


        public void updateInputs(ArmIOInputs inputs) {
            inputs.isBrake = isBrake;
            inputs.curent = armMotor_l.getOutputCurrent();
            inputs.curentAngle = armAbsoluteEncoder.getPosition();
            inputs.velocity = armAbsoluteEncoder.getVelocity();
            inputs.targetAngle = targetAngle;
            inputs.appliedPower = armMotor_l.getAppliedOutput();
        }

        public void setBreakMode(boolean isBrake) {
            this.isBrake = isBrake;
            if (isBrake) {
                armMotor_l.setIdleMode(CANSparkMax.IdleMode.kBrake);
            } else {
                armMotor_l.setIdleMode(CANSparkMax.IdleMode.kCoast);
            }
        }

        public void setAngle(double angle) {
            targetAngle = angle;
            armPIDController.setReference(angle, ControlType.kPosition);
        }

}
