package frc.robot;

import com.revrobotics.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.RobotMap;
import frc.robot.Updateable;

public class Drivetrain implements Updateable {

    //public CANSparkMax spark_1;    //add more motors
    public WPI_TalonSRX lf_motor;
    public WPI_TalonSRX lb_motor;
    public WPI_TalonSRX rf_motor;
    public WPI_TalonSRX rb_motor;
    public AHRS gyro;


    Drivetrain(RobotMap robotmap) {
       // spark_1 = robotmap.Spark_1; // not part of the drivetrain probably
        lf_motor = robotmap.Talon_1;
        lb_motor = robotmap.Talon_2;
        rf_motor = robotmap.Talon_5;
        rb_motor = robotmap.Talon_6;
        gyro = robotmap.navX;

        rf_motor.setInverted(true);
        rb_motor.setInverted(true);

        lf_motor.setNeutralMode(NeutralMode.Brake);
        lb_motor.setNeutralMode(NeutralMode.Brake);
        rf_motor.setNeutralMode(NeutralMode.Brake);
        rb_motor.setNeutralMode(NeutralMode.Brake);

    }

    public void setMove(double left, double right) {
        //TODO: Make angle do something here

        lf_motor.set(left);
        lb_motor.set(left);
        rf_motor.set(right);
        rb_motor.set(right);

        System.out.println("Yaw: " + left);
        System.out.println("Pitch: " + right);

    }

    public void update(double dt) {
        //prob nothing here?
    }

}
