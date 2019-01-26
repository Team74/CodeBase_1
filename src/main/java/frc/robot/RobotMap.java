package frc.robot;

import com.revrobotics.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
/*
This class sets up all the different things related to the PWM, plus some other stuff. It'll get passed in to other classes so they can get references back to the individual components.
*/
public class RobotMap {
    //These need to be set in meters per second
    public final double wheelBaseWidth = 0.0;
    public final double wheelBaseDepth = 0.0;

    public final double wheelDiameter = 0.0;//Meters

    public final double countsPerRev = 0.0;//Encoder ticks per revolution for drive motors
    
    //These need to be in meters per second
    public final double maxVel = 0.0;
    public final double maxAccel = 0.0;
    public final double maxJerk = 0.0;

    //bunch of references to motors and such
    public CANSparkMax Drive_0 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);//Front left
    public CANSparkMax Drive_1 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);//Front right
    public CANSparkMax Drive_2 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);//Back left
    public CANSparkMax Drive_3 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);//Back right

    public CANEncoder Drive_E_0;
    public CANEncoder Drive_E_1;
    public CANEncoder Drive_E_2;
    public CANEncoder Drive_E_3;


    public WPI_TalonSRX Steering_0 = new WPI_TalonSRX(5);
    public WPI_TalonSRX Steering_1 = new WPI_TalonSRX(6);
    public WPI_TalonSRX Steering_2 = new WPI_TalonSRX(7);
    public WPI_TalonSRX Steering_3 = new WPI_TalonSRX(8);

    public AHRS navX = new AHRS(SPI.Port.kMXP, (byte)60);

    public RobotMap() {
        //sets up stuff if necessary
        Drive_E_0 = new CANEncoder(Drive_0);
        Drive_E_1 = new CANEncoder(Drive_1);
        Drive_E_2 = new CANEncoder(Drive_2);
        Drive_E_3 = new CANEncoder(Drive_3);

    }
}
