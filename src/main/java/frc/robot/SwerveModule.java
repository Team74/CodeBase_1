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
        rotate_motor.configFeedbackNotContinuous(false, kTimeoutMs);
    }

    public void setModuleDirection(double targetAngle, double target_speed){
        double currentRotation = rotate_motor.getSelectedSensorPosition();
        currentRotation %= 2048;
        //Change currentRotation to be in radians
        currentRotation = Math.PI*(currentRotation/2048);
        //Change target angle to be on the 0 to 2pi range
        targetAngle = targetAngle < 0 ? targetAngle + 2*(Math.PI) : targetAngle;
        


    }

    public void setMotors(double targetRotation, double targetSpeed) {
        drive_motor.set(targetSpeed);
        rotate_motor.set(ControlMode.Position, targetRotation);
    }

    public double angleToEncoder(double diff_angle){ 
        double currentEncoderValue = rotate_motor.getSelectedSensorPosition();
        double targetRotation = currentEncoderValue;
        return targetRotation;
    }
}
