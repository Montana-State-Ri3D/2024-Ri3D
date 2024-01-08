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
        private SparkMaxPIDController armPIDController_l;
        private SparkMaxPIDController armPIDController_r;
        private SparkMaxAbsoluteEncoder armAbsoluteEncoder_l;
        private SparkMaxAbsoluteEncoder armAbsoluteEncoder_r;

        public ArmRealIO(int IDleftMotor, int IDrightMotor) {
            armMotor_l = new CANSparkMax(IDleftMotor, MotorType.kBrushless);
            armMotor_r = new CANSparkMax(IDrightMotor, MotorType.kBrushless);

            armPIDController_l = armMotor_l.getPIDController();
            armPIDController_r = armMotor_r.getPIDController();

            armAbsoluteEncoder_l = armMotor_l.getAbsoluteEncoder(Type.kDutyCycle);
            armAbsoluteEncoder_r = armMotor_r.getAbsoluteEncoder(Type.kDutyCycle);

            armMotor_r.follow(armMotor_l);

            armAbsoluteEncoder_l.setZeroOffset(ARM_Offset);

            armAbsoluteEncoder_l.setPositionConversionFactor(Math.PI*2);
            armAbsoluteEncoder_l.setVelocityConversionFactor(Math.PI*2/60);
            
            armPIDController_l.setP(0.2);
            armPIDController_l.setI(0);
            armPIDController_l.setD(0);

            armPIDController_l.setFeedbackDevice(armAbsoluteEncoder_l);
            armPIDController_l.setOutputRange(-1, 1);

            armMotor_l.setIdleMode(CANSparkMax.IdleMode.kBrake);
            isBrake = true;
        }


        public void updateInputs(ArmIOInputs inputs) {
            inputs.isBrake = isBrake;
            inputs.curent = armMotor_l.getOutputCurrent();
            inputs.curentAngle = armAbsoluteEncoder_l.getPosition();
            inputs.velocity = armAbsoluteEncoder_l.getVelocity();
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
            armPIDController_l.setReference(angle, ControlType.kPosition);
        }

}
