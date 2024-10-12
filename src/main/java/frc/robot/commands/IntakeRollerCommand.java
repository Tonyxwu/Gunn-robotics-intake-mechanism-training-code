package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakePivotSubsystem;
import frc.robot.subsystems.IntakeRollerSubsystem;

/** Sets the intake pivot position. */ 
public class IntakeRollerCommand extends Command {
    private final IntakeRollerSubsystem rollerSubsystem;
    private double integrationMotorSpeed;
    private double frontMotorSpeed;
    //private int triggerConditional;
    /**
     * Sets the intake pivot to a position.
     *
     * @param intakeRollerSubsystem The {@link IntakePivotSubsystem} to set the pivot on.
     * @param integrationMotorSpeed Speed of motor -1 to 1
     * @param triggerConditional index of the set of conditionals for the command to stop executing at
     */
    public IntakeRollerCommand(IntakeRollerSubsystem intakeRollerSubsystem, double integrationMotorSpeed, double frontMotorSpeed) {
        this.rollerSubsystem = intakeRollerSubsystem;
        this.integrationMotorSpeed = integrationMotorSpeed;
        this.frontMotorSpeed = frontMotorSpeed;
        //this.triggerConditional = triggerConditional;
    }

    @Override
    public void initialize() {
        rollerSubsystem.setSpeed(integrationMotorSpeed, frontMotorSpeed);
        System.out.println("Rollerz are spinning");
    }

    @Override
    public void end(boolean interrupted) {
        rollerSubsystem.setSpeed(0.0, 0.0);
        System.out.println("done");
        
    }

    @Override
    public boolean isFinished() {
        System.out.println("Rollerz interrupted");
        return false;
        //return rollerSubsystem.conditionalStopStates(triggerConditional);
    }
}