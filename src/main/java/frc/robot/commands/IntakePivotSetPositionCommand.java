package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakePivotSubsystem;

/** Sets the intake pivot position. */ 
public class IntakePivotSetPositionCommand extends Command {
    private final IntakePivotSubsystem pivotSubsystem;
    private double position;


    /**
     * Sets the intake pivot to a position.
     *
     * @param intakePivotSubsystem The {@link IntakePivotSubsystem} to set the pivot on.
     * @param position The position [0, 1] to set the pivot to. 0 is stowed, 1 is fully extended.
     */
    public IntakePivotSetPositionCommand(IntakePivotSubsystem intakePivotSubsystem, double position) {
        this.pivotSubsystem = intakePivotSubsystem;
        this.position = position;
        addRequirements(intakePivotSubsystem);
    }

    @Override
    public void initialize() {
        pivotSubsystem.setPosition(position);
        System.out.println("Intake Initialized");
    }

    @Override
    public void end(boolean interrupted) {
        pivotSubsystem.movePivot(0);
        System.out.println("Intake End");
        
    }

    @Override
    public boolean isFinished() {
        System.out.println("Intake Finished");
        return Math.abs(pivotSubsystem.getEncoderPosition() - position) < .1;
       
    }
}