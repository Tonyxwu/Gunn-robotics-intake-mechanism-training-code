// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.IntakePivotSetPositionCommand;
import frc.robot.commands.IntakeRollerCommand;

import frc.robot.subsystems.IntakePivotSubsystem;
import frc.robot.subsystems.IntakeRollerSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should bPe declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final IntakePivotSubsystem IntakePivotSubsystem;
  private final IntakeRollerSubsystem IntakeRollerSubsystem;
  private final XboxController controller = new XboxController(0);
  private final JoystickButton xButton = new JoystickButton(controller, XboxController.Button.kX.value);
  private final JoystickButton bButton = new JoystickButton(controller, XboxController.Button.kB.value);
  private final JoystickButton yButton = new JoystickButton(controller, XboxController.Button.kY.value);
  private final JoystickButton aButton = new JoystickButton(controller, XboxController.Button.kA.value);

  private final JoystickButton leftBumper = new JoystickButton(controller, XboxController.Button.kLeftBumper.value);
  private final JoystickButton rightBumper = new JoystickButton(controller, XboxController.Button.kRightBumper.value);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    IntakePivotSubsystem = new IntakePivotSubsystem();
    IntakeRollerSubsystem = new IntakeRollerSubsystem();
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {



      //check if note is in shooter position or amp position.
      //if there is no notes in the system:
        //extend pivot to position 1  
        //deploy inward roller command unti AMP sensor picks up on the note
        //retract the pivot (position 0)
      //if there is note
        //if the note is in the amp position your done
        //if it's in the shooter position you're cooked
    aButton.onTrue(
      new ConditionalCommand(
        new IntakePivotSetPositionCommand(IntakePivotSubsystem, 1).andThen(
        new IntakeRollerCommand(IntakeRollerSubsystem, 0.25,0.25).until(() -> IntakeRollerSubsystem.frontAmpGet()))

        //insert dummy command here if conditionalCommand actually requires a second command
        () -> (!IntakeRollerSubsystem.frontAmpGet())&&(!IntakeRollerSubsystem.shooterSensorGet())
      )
    );

    //check if note in amp position
      //if so, extend pivot to position 1
      //roll rollers inwards until Shooter Sensor picks up on the note
    yButton.onTrue(
        new ConditionalCommand(
          new IntakePivotSetPositionCommand(IntakePivotSubsystem, 1).andThen(
          new IntakeRollerCommand(IntakeRollerSubsystem, 0.25,0.25).until(() ->IntakeRollerSubsystem.shooterSensorGet()))
        //insert dummy command here if conditionalCommand actually requires a second command
        () -> IntakeRollerSubsystem.frontAmpGet())
    );

    //theoretically stops the rollers?
    bButton.onTrue(
      Commands.idle(IntakeRollerSubsystem).withTimeout(0));
    
    //stows the pivot thing
    xButton.onTrue(
      new IntakePivotSetPositionCommand(IntakePivotSubsystem, 0));

    //manual rollers
    //I forgot which direction is intake and outtake
    leftBumper.onTrue(
      new IntakeRollerCommand(IntakeRollerSubsystem, 0.25,0.25).until(() -> !leftBumper.getAsBoolean()));
    rightBumper.onTrue(
      new IntakeRollerCommand(IntakeRollerSubsystem, -0.25,-0.25).until(() -> !rightBumper.getAsBoolean()));;
    


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
