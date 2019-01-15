package frc.robot;

import com.revrobotics.*;

/*
This class sets up all the different things related to the PWM, plus some other stuff. It'll get passed in to other classes so they can get references back to the individual components.
*/
public class RobotMap {
    //bunch of references to motors and such

    public CANSparkMax Motor1 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANEncoder Encoder1;



    RobotMap() {
        //sets up stuff if necessary
        Encoder1 = new CANEncoder(Motor1);
    }
}
