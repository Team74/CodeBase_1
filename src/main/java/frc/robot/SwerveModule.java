package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

import frc.robot.RobotMap;
import frc.robot.Updateable;

public class SwerveModule {
    public CANSparkMax drive_motor;
    public CANEncoder drive_encoder;
    public WPI_TalonSRX rotate_motor;

    private double angle;
    private double diff_angle;
    private double new_angle;
    private double targetPosition;
    private double currentPosition;

    public static int kSlotIdx = 0;
    public static int kPIDLoopIdx = 0;
    public static int kTimeoutMs = 30;
    public static boolean kSensorPhase = false;
    public static boolean kMotorInvert = false;

    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
    public static double kF = 0;
    public static double kIZone = 0;

    public SwerveModule(CANSparkMax _drive_motor, CANEncoder _drive_encoder, WPI_TalonSRX _rotate_motor) {
        drive_motor = _drive_motor;
        rotate_motor = _rotate_motor;
        drive_encoder = _drive_encoder;
    }

    public void instantiateSteeringPID(double _kP, double _kI, double _kD, double _kF, double _kIZone, boolean _kSensorPhase, boolean _kMotorInvert){
        //HandleEncoder encoder = new HandleEncoder(rotate_motor, kSensorPhase);
        kP = _kP;
        kI = _kI;
        kD = _kD;
        kF = _kF;
        kIZone = _kIZone;
        kSensorPhase = _kSensorPhase;
        kMotorInvert = _kMotorInvert;

        rotate_motor.setInverted(kMotorInvert);

        rotate_motor.configNominalOutputForward(0, kTimeoutMs);
		rotate_motor.configNominalOutputReverse(0, kTimeoutMs);
		rotate_motor.configPeakOutputForward(1, kTimeoutMs);
        rotate_motor.configPeakOutputReverse(-1, kTimeoutMs);

        rotate_motor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

        rotate_motor.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
		rotate_motor.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
		rotate_motor.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
        rotate_motor.config_kD(kPIDLoopIdx, kD, kTimeoutMs);

        rotate_motor.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDLoopIdx, kTimeoutMs);
        rotate_motor.setSensorPhase(kSensorPhase);

    }

    public void setIndividualMove(double in_angle, double in_magnitude) {
        //angle should be in range -pi to pi, 0 is straight ahead. magnitude -1 to 1
        diff_angle = in_angle - angle;
        new_angle = diff_angle;
        if(Math.abs(diff_angle) > Math.PI / 2) {
            new_angle -= Math.signum(diff_angle) * Math.PI;
        }
        if(Math.abs(new_angle) > Math.PI){
            new_angle -= Math.signum(new_angle) * 2 * Math.PI;
        }
        double targetRotation = angleToEncoder(new_angle);

        rotate_motor.set(ControlMode.Position, targetRotation);
        drive_motor.set(in_magnitude);
    }

    public double angleToEncoder(double angle){ 
        double encoderValue = ((angle / Math.PI) + 2) * 512;
        return encoderValue;
    }

    /*public class HandleEncoder{
        private double rotationCount = 1;
        private double lastPosition = 0;
        private double currentPosition;

        public HandleEncoder(WPI_TalonSRX motor, boolean kSensorPhase){
            rotate_motor.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDLoopIdx, kTimeoutMs);
            rotate_motor.setSensorPhase(kSensorPhase);
        }

        public double getPosition(){
            currentPosition = rotate_motor.getSelectedSensorPosition();
            if (currentPosition < 24 && lastPosition > 1000){
                if (rotationCount == 1){rotationCount += 1;}
                else{rotationCount -= 1;}
            } else if (currentPosition > 1000 && lastPosition < 24){
                if (rotationCount == 1){rotationCount += 1;}
                else{rotationCount -= 1;}                
            }
            lastPosition = currentPosition;
            if (rotationCount == 2){return currentPosition + 1024;}
            else {return currentPosition;}


        }
    }*/
}
