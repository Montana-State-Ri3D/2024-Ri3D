package frc.robot.utility;

public class Joystick {
    public static double JoystickInput(double value, double power, double deadband, double clamp) {
        if (power != 0) {
            value = Math.copySign(Math.pow(value, power), value);
        }
        if (deadband != 0) {
            if (Math.abs(value) > deadband) {
                if (value > 0) {
                    value = (value - deadband) / (1.0 - deadband);
                } else {
                    value = (value + deadband) / (1.0 - deadband);
                }
            } else {
                value = 0;
            }
        }
        if (clamp != 1) {
            value = Math.max(-clamp, Math.min(value, clamp));
        }
        return value;
    }

    public static double JoystickInput(double value) {
        return JoystickInput(value, 2, 0.02, 1.0);
    }

    public static double JoystickInput(double value, double deadband) {
        return JoystickInput(value, 2, deadband, 1.0);
    }
}