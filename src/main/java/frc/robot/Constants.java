// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  /** Constants for the intake subsystems. */
  public static class IntakeConstants {
    //MOTOR IDs
    public static final int INTEGRATION_MOTOR_ID = 19;
    public static final int PIVOT_MOTOR_ID = 16;
    public static final int FRONT_MOTOR_ID = 17;

    //PID CONSTANTS FOR PIVOT
    public static final double PIVOT_P = 1; //old 2.5
    public static final double PIVOT_I = 0;
    public static final double PIVOT_D = 0;
    public static final double PIVOT_CONVERSION_FACTOR = 0.2142;
    
    //SENSOR IDs
    public static final int FRONT_SENSOR_ID = 3;
    public static final int AMP_SENSOR_ID = 5;
    public static final int SHOOTER_SENSOR_ID = 4;
    

}
}
