package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.RobotMap;
import frc.robot.Updateable;

public class Drivetrain implements Updateable {

    //public  spark_1;    //add more motors
    public SwerveModule lf;
    public SwerveModule lb;
    public SwerveModule rf;
    public SwerveModule rb;

    public AHRS gyro;


    Drivetrain(RobotMap robotmap) {

        lf = new SwerveModule( robotmap.Drive_0, robotmap.Drive_E_0, robotmap.Steering_0);
        lb = new SwerveModule( robotmap.Drive_1, robotmap.Drive_E_1, robotmap.Steering_1);
        rf = new SwerveModule( robotmap.Drive_2, robotmap.Drive_E_2, robotmap.Steering_2);
        rb = new SwerveModule( robotmap.Drive_3, robotmap.Drive_E_3, robotmap.Steering_3);

        gyro = robotmap.navX;

    }

    public void setMove(double speed, double angle, double rotation) {

        //Here goes the swerve code!

    }

    public void update(double dt) {
        //prob nothing here
    }

}
