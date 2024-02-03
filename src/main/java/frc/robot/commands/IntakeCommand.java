package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.intake.Intake;
public class IntakeCommand extends Command {
    Intake intake;

    private final BooleanSupplier cancel;

    private double speed;

    public IntakeCommand(Intake intake, BooleanSupplier cancel,double speed) {
        this.cancel = cancel;
        this.speed = speed;
        addRequirements(intake);

        this.intake = intake;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        intake.setPower(speed);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.setPower(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (intake.getBeamBreakValue() | cancel.getAsBoolean());
    }
}
