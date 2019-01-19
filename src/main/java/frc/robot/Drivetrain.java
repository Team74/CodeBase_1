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

        private double angle;

        public SwerveModule(CANSparkMax _drive_motor, CANEncoder _drive_encoder, WPI_TalonSRX _rotate_motor) {
            drive_motor = _drive_motor;
            rotate_motor = _rotate_motor;
            drive_encoder = _drive_encoder;
        }

        public void setIndividualMove(double in_angle, double in_magnitude) {
            //angle should be in range -pi to pi, 0 is straight ahead. magnitude -1 to 1
            double diff_angle = in_angle - angle;
            double new_angle = diff_angle;
            if(Math.abs(diff_angle) > Math.PI / 2) {
                new_angle -= Math.signum(diff_angle) * Math.PI;
            }
            if(Math.abs(new_angle) > Math.PI){
                new_angle -= Math.signum(new_angle) * 2 * Math.PI;
            }

            angle = new_angle;
            rotate_motor.set(angle);    //assuming it can rotate 90 degrees in 1 tick // or PID nonsense
            drive_motor.set(in_magnitude);
        }
    }



    //public  spark_1;    //add more motors
    public SwerveModule lf;
    public SwerveModule lb;
    public SwerveModule rf;
    public SwerveModule rb;

    public AHRS gyro;

    private final double WIDTH = 0.6;
    private final double LENGTH = 0.8;


    Drivetrain(RobotMap robotmap) {

        lf = new SwerveModule( robotmap.Drive_0, robotmap.Drive_E_0, robotmap.Steering_0);
        lb = new SwerveModule( robotmap.Drive_1, robotmap.Drive_E_1, robotmap.Steering_1);
        rf = new SwerveModule( robotmap.Drive_2, robotmap.Drive_E_2, robotmap.Steering_2);
        rb = new SwerveModule( robotmap.Drive_3, robotmap.Drive_E_3, robotmap.Steering_3);

        gyro = robotmap.navX;

    }

    public void setMove(double speed, double angle, double rotation) {
      //rotation is in rad/sec
      //info for this section from https://www.chiefdelphi.com/t/paper-4-wheel-independent-drive-independent-steering-swerve/107383
        double vx = speed*Math.sin(angle); //angle is measured from 0 being straight forward, positive turning right, from -pi to pi
        double vy = speed*Math.cos(angle);

        double A = vx - rotation*LENGTH/2;
        double B = vx + rotation*LENGTH/2;
        double C = vy - rotation*WIDTH/2;
        double D = vy + rotation*WIDTH/2;

        double rf_m = Math.sqrt(Math.pow(B,2) + Math.pow(C,2));
        double lf_m = Math.sqrt(Math.pow(B,2) + Math.pow(D,2));
        double lb_m = Math.sqrt(Math.pow(A,2) + Math.pow(D,2));
        double rb_m = Math.sqrt(Math.pow(A,2) + Math.pow(C,2));

        double max = rf_m; max = lf_m > max ? lf_m : max; max = lb_m > max ? lb_m : max; max = rb_m > max ? rb_m : max;
        rf_m /= max; lf_m /= max; lb_m /= max; rb_m /= max; //normalizing them

        double rf_a = -Math.atan2(B,C); //these are negative because for some reason we're doing angles in the opposite direction
        double lf_a = -Math.atan2(B,D); //we should maybe change that
        double lb_a = -Math.atan2(A,C);
        double rb_a = -Math.atan2(A,C);

        rf.setIndividualMove(rf_a, rf_m);
        lf.setIndividualMove(lf_a, lf_m);
        lb.setIndividualMove(lb_a, lb_m);
        rb.setIndividualMove(rb_a, rb_m);

    }

    public void update(double dt) {
        //prob nothing here
    }

}
