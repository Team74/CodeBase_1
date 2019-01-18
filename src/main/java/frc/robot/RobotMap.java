package frc.robot;

import com.revrobotics.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
/*
This class sets up all the different things related to the PWM, plus some other stuff. It'll get passed in to other classes so they can get references back to the individual components.
*/
public class RobotMap {
    //bunch of references to motors and such

    public CANSparkMax Spark_1 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANEncoder Spark_1_E;

    public WPI_TalonSRX Talon_1 = new WPI_TalonSRX(1);
    public WPI_TalonSRX Talon_2 = new WPI_TalonSRX(2);
    public WPI_TalonSRX Talon_5 = new WPI_TalonSRX(5);
    public WPI_TalonSRX Talon_6 = new WPI_TalonSRX(6);

    public AHRS navX = new AHRS(SPI.Port.kMXP, (byte)60);

    //test

    RobotMap() {
        //sets up stuff if necessary
        Spark_1_E = new CANEncoder(Spark_1);

    }
}
