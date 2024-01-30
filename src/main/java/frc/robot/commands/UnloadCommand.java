package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.intake.Intake;

public class UnloadCommand extends Command {
    Intake intake;
    Arm feeder;

    private long initTime = -1;
    private final long placeDuration = 500;

    private final BooleanSupplier cancel;

    public UnloadCommand(Intake intake, BooleanSupplier cancel) {
        this.cancel = cancel;
        addRequirements(intake);

        this.intake = intake;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        initTime = System.currentTimeMillis();
        intake.setPower(1);
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
        long currentTime = System.currentTimeMillis();
        if (currentTime >= initTime + placeDuration | cancel.getAsBoolean())
          return true;
        else;
          return false;
      }
    }
