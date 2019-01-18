package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.RobotMap;
import frc.robot.Updateable;

public class Drivetrain implements Updateable {

    public class SwerveModule {
        public CANSparkMax drive_motor;
        public CANEncoder drive_encoder;
        public WPI_TalonSRX rotate_motor;

        public SwerveModule(CANSparkMax _drive_motor, CANEncoder _drive_encoder, WPI_TalonSRX _rotate_motor) {
            drive_motor = _drive_motor;
            rotate_motor = _rotate_motor;
            drive_encoder = _drive_encoder;
        }
    }



    //public  spark_1;    //add more motors
    public SwerveModule lf;
    public SwerveModule lb;
    public SwerveModule rf;
    public SwerveModule rb;

    public AHRS gyro;


    Drivetrain(RobotMap robotmap) {

        lf = new SwerveModule( robotmap.Spark_3, robotmap.Spark_E_3, robotmap.Talon_1);
        lb = new SwerveModule( robotmap.Spark_4, robotmap.Spark_E_4, robotmap.Talon_2);
        rf = new SwerveModule( robotmap.Spark_7, robotmap.Spark_E_7, robotmap.Talon_5);
        rb = new SwerveModule( robotmap.Spark_8, robotmap.Spark_E_8, robotmap.Talon_6);

        gyro = robotmap.navX;

    }

    public void setMove(double speed, double angle, double rotation) {

        //Here goes the swerve code!

    }

    public void update(double dt) {
        //prob nothing here
    }

}
