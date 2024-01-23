package frc.robot.subsystems.arm;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import com.revrobotics.SparkAbsoluteEncoder;

/** Add your docs here. */
public class ArmRealIO implements ArmIO {

        boolean isBrake;
        double targetAngle;

        private CANSparkMax armMotor_l;
        private CANSparkMax armMotor_r;
        private SparkPIDController armPIDController;
        private SparkAbsoluteEncoder armAbsoluteEncoder;

        public ArmRealIO(int IDleftMotor, int IDrightMotor) {
            armMotor_l = new CANSparkMax(IDleftMotor, MotorType.kBrushless);
            armMotor_r = new CANSparkMax(IDrightMotor, MotorType.kBrushless);

            armMotor_l.setIdleMode(CANSparkMax.IdleMode.kBrake);
            armMotor_r.setIdleMode(CANSparkMax.IdleMode.kBrake);
            isBrake = true;

            armMotor_r.setInverted(true);

            armPIDController = armMotor_r.getPIDController();

            armAbsoluteEncoder = armMotor_r.getAbsoluteEncoder(Type.kDutyCycle);

            armMotor_l.follow(armMotor_r, true);

            armAbsoluteEncoder.setInverted(true);
            armAbsoluteEncoder.setPositionConversionFactor(Math.PI*2);
            armAbsoluteEncoder.setVelocityConversionFactor(Math.PI*2/60);

            armAbsoluteEncoder.setZeroOffset(ARM_Offset);
            
            armPIDController.setP(0.4);
            armPIDController.setI(0.0);
            armPIDController.setD(0.00001);

            armPIDController.setFeedbackDevice(armAbsoluteEncoder);
            armPIDController.setOutputRange(-1.00, 1.00);   
            
            armMotor_r.setSmartCurrentLimit(80);
            armMotor_l.setSmartCurrentLimit(80);
        }


        public void updateInputs(ArmIOInputs inputs) {
            inputs.isBrake = isBrake;
            inputs.curent = armMotor_r.getOutputCurrent()+ armMotor_l.getOutputCurrent();
            inputs.curentAngle = armAbsoluteEncoder.getPosition();
            inputs.velocity = armAbsoluteEncoder.getVelocity();
            inputs.targetAngle = targetAngle;
            inputs.appliedPower = armMotor_r.getAppliedOutput();
            inputs.relativePos_l = armMotor_l.getEncoder().getPosition();
            inputs.relativePos_r = armMotor_r.getEncoder().getPosition();
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
