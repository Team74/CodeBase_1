package frc.robot;

import com.revrobotics.*;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.Updateable;

public class Drivetrain implements Updateable {

    //public CANSparkMax spark_1;    //add more motors
    public WPI_TalonSRX lf_motor;
    public WPI_TalonSRX lb_motor;
    public WPI_TalonSRX rf_motor;
    public WPI_TalonSRX rb_motor;


    Drivetrain(RobotMap robotmap) {
       // spark_1 = robotmap.Spark_1; // not part of the drivetrain probably
        lf_motor = robotmap.Talon_1;
        lb_motor = robotmap.Talon_2;
        rf_motor = robotmap.Talon_5;
        rb_motor = robotmap.Talon_6;

        lf_motor.setNeutralMode(NeutralMode.Brake);
        lb_motor.setNeutralMode(NeutralMode.Brake);
        rf_motor.setNeutralMode(NeutralMode.Brake);
        rb_motor.setNeutralMode(NeutralMode.Brake);

    }

    public void setMove(double amount, double angle) {
        //TODO: Make angle do something here

        lf_motor.set(amount);
        lb_motor.set(amount);
        rf_motor.set(amount);
        rb_motor.set(amount);

    }

    public void update(double dt) {
        //prob nothing here?
    }

}
