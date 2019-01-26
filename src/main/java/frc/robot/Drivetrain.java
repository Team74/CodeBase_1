package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.RobotMap;
import frc.robot.Updateable;

public class Drivetrain implements Updateable {

    //public  spark_1;    //add more motors
    public SwerveModule lf;
    public SwerveModule lb;
    public SwerveModule rf;
    public SwerveModule rb;

    public double m_swerveVectors[][];//{ {lf_a, lf_m}, {rf_a, rf_m}, {lb_a, lb_m}, {rb_a, rf_a} }

    public AHRS gyro;

    private final double WIDTH;
    private final double LENGTH;

    Drivetrain(RobotMap robotmap) {

        lf = new SwerveModule( robotmap.Drive_0, robotmap.Drive_E_0, robotmap.Steering_0);
        lb = new SwerveModule( robotmap.Drive_1, robotmap.Drive_E_1, robotmap.Steering_1);
        rf = new SwerveModule( robotmap.Drive_2, robotmap.Drive_E_2, robotmap.Steering_2);
        rb = new SwerveModule( robotmap.Drive_3, robotmap.Drive_E_3, robotmap.Steering_3);

        gyro = robotmap.navX;

        WIDTH = robotmap.wheelBaseWidth;
        LENGTH = robotmap.wheelBaseDepth;

    }

    public void setMove(double speed, double angle, double rotation) {
      //rotation is in rad/sec
      //info for this section from https://www.chiefdelphi.com/t/paper-4-wheel-independent-drive-independent-steering-swerve/107383
        double[][] swerveVectors = new double[4][2];//{ {lf_a, lf_m}, {rf_a, rf_m}, {lb_a, lb_m}, {rb_a, rf_a} }
      
        double vx = speed*Math.sin(angle); //angle is measured from 0 being straight forward, positive turning right, from -pi to pi
        double vy = speed*Math.cos(angle);

        double A = vx - rotation*(LENGTH)/2;
        double B = vx + rotation*(LENGTH)/2;
        double C = vy - rotation*(WIDTH)/2;
        double D = vy + rotation*(WIDTH)/2;
        
        swerveVectors[0][1] = Math.sqrt(Math.pow(B,2) + Math.pow(D,2));//lf
        swerveVectors[1][1] = Math.sqrt(Math.pow(B,2) + Math.pow(C,2));//rf
        swerveVectors[2][1] = Math.sqrt(Math.pow(A,2) + Math.pow(D,2));//lb
        swerveVectors[3][1] = Math.sqrt(Math.pow(A,2) + Math.pow(C,2));//rb

        double max = swerveVectors[1][1]; max = swerveVectors[0][1] > max ? swerveVectors[0][1] : max; max = swerveVectors[2][1] > max ? swerveVectors[2][1] : max; max = swerveVectors[3][1] > max ? swerveVectors[3][1] : max;
        if (max > 1){
        swerveVectors[1][1] /= max; swerveVectors[0][1] /= max; swerveVectors[2][1] /= max; swerveVectors[3][1] /= max; //normalizing them
        }

        swerveVectors[0][0] = -Math.atan2(B,D);//lf    these are negative because for some reason we're doing angles in the opposite direction
        swerveVectors[1][0] = -Math.atan2(B,C);//rf    we should maybe change that
        swerveVectors[2][0] = -Math.atan2(A,D);//lb
        swerveVectors[3][0] = -Math.atan2(A,C);//rb

        manageModules(swerveVectors);
        //Args are, TargetAngle, TargetSpeed
    }

    public void manageModules(double swerveVectors[][]){
        m_swerveVectors = swerveVectors;
        lf.setModule(m_swerveVectors[0][0], m_swerveVectors[0][1]);
        rf.setModule(m_swerveVectors[1][0], m_swerveVectors[1][1]);
        lb.setModule(m_swerveVectors[2][0], m_swerveVectors[2][1]);
        rb.setModule(m_swerveVectors[3][0], m_swerveVectors[3][1]);
    }

    public void update(double dt) {
        //prob nothing here
    }

}
