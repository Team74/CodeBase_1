package frc.robot;

import com.revrobotics.*;
import frc.robot.RobotMap;
import frc.robot.Updateable;

public class Drivetrain implements Updateable {

    public CANSparkMax lf_motor;    //add more motors

    Drivetrain(RobotMap robotmap) {
        lf_motor = robotmap.Motor1;
        // robotmap.motor1.doStuff()
        // etc.
    }

    public void setMove(double amount, double angle) {
        //TODO: Make this actually work eventually
        lf_motor.set(amount);
        //presumably sets other motor
    }

    public void update(double dt) {
        //prob nothing here?
    }

}
