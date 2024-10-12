package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/** The subsystem for the pivot on the intake. */
public class IntakeRollerSubsystem extends SubsystemBase {

    private final CANSparkMax frontMotor;
    private final TalonSRX integrationMotor;
    private final DigitalInput frontSensor;
    private final DigitalInput ampSensor;
    private final DigitalInput shooterSensor;

    /**
     * Subsystem for controlling the rollers and sensors on the Intake
     */
    public IntakeRollerSubsystem() {
        integrationMotor = new TalonSRX(IntakeConstants.INTEGRATION_MOTOR_ID);
        frontMotor = new CANSparkMax(IntakeConstants.FRONT_MOTOR_ID, MotorType.kBrushless);;
        frontSensor = new DigitalInput(IntakeConstants.FRONT_SENSOR_ID);
        ampSensor = new DigitalInput(IntakeConstants.AMP_SENSOR_ID);
        shooterSensor = new DigitalInput(IntakeConstants.SHOOTER_SENSOR_ID);
    } 

    
    /**
     * sets the speed of the rollers
     * @param integrationMotorSpeed set speed of front motor on range -1 < x < 1
     * @param frontMotorSpeed set speed of front motor on range -1 < x < 1
     */
    public void setSpeed(double integrationMotorSpeed, double frontMotorSpeed) {
        integrationMotor.set(TalonSRXControlMode.PercentOutput,integrationMotorSpeed);
        frontMotor.set(frontMotorSpeed);
        
    }

    //self explanatory, 
    //TRUE = something detected in the sensor
    //FALSE = nothing detected :(
    public boolean frontSensorGet(){
        return frontSensor.get();
    }
    public boolean frontAmpGet(){
        return ampSensor.get();
    }
    public boolean shooterSensorGet(){
        return shooterSensor.get();
    }

    @Override
    public void periodic() {
  
    }
}