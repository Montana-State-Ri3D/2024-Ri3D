package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;

public class WaitCommandWithExit extends Command {

    private long initTime = -1;
    private long duration = -1;

    private final BooleanSupplier cancel;

    public WaitCommandWithExit(double seconds, BooleanSupplier cancel) {
        this.cancel = cancel;
        this.duration = (int) (seconds * 1000);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        initTime = System.currentTimeMillis();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        long currentTime = System.currentTimeMillis();
        if (currentTime >= initTime + duration | cancel.getAsBoolean())
          return true;
        else;
          return false;
      }
    }
