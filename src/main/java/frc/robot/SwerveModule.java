package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANPIDController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class SwerveModule {
    public final double maxVel = 0.0;

    public CANSparkMax drive_motor;
    public CANEncoder drive_encoder;
    public WPI_TalonSRX rotate_motor;

    private double targetRotation;
    private double targetSpeed;

    public final int kSlotIdx = 0;
    public final int kPIDLoopIdx = 0;
    public final int kTimeoutMs = 30;

    CANPIDController velController = new CANPIDController(drive_motor);

    public SwerveModule(CANSparkMax _drive_motor, CANEncoder _drive_encoder, WPI_TalonSRX _rotate_motor) {
        drive_motor = _drive_motor;
        rotate_motor = _rotate_motor;
        drive_encoder = _drive_encoder;

    }

    public void instantiateSteeringPID(double kP, double kI, double kD, double kF, double kIZone, boolean kSensorPhase, boolean kMotorInvert){
        //HandleEncoder encoder = new HandleEncoder(rotate_motor, kSensorPhase);
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

    public void instantiateVelocityPID(double kP, double kI, double kD, double kF, double kIZone) {
        //We're going to tune the gains using the tuner than hardcode them.
        /*
        velController.setP(kP, kSlotIdx);
        velController.setI(kI, kSlotIdx);
        velController.setD(kD, kSlotIdx);
        velController.setFF(kF, kSlotIdx);
        velController.setIZone(kIZone, kSlotIdx);
        velController.setOutputRange(-1.0, 1.0, kSlotIdx);
        drive_motor.burnFlash();
        */


    }

    public void setModule(double targetAngle, double _targetSpeed){
        double totalRotation;
        double currentRotation;
        double currentAngle;
        double angleDelta;
        double angleModifier;
        double absAngleDelta;
        //Get current angle
        totalRotation= rotate_motor.getSelectedSensorPosition();
        currentRotation = totalRotation % 2048;
        //Change currentRotation to be in radians
        currentAngle = 2*Math.PI*(currentRotation/2048);
        //Change target angle to be on the 0 to 2pi range
        targetAngle = targetAngle < 0 ? targetAngle + 2*(Math.PI) : targetAngle;
        //Calculate the differance in angle
        angleDelta = (targetAngle - currentAngle)/Math.PI*180;  //convert to degrees
        absAngleDelta = Math.abs(angleDelta);
        //Begin checking cases
        targetSpeed = _targetSpeed;

        if (0 <= absAngleDelta && absAngleDelta <= 90) {
            angleModifier = angleDelta;
        }else if (90 < absAngleDelta && absAngleDelta <= 180) {
            angleModifier = angleDelta - 180;//or -(180-delta)
            //Reverse wheel direction
            targetSpeed = _targetSpeed * -1;
        }else if (180 < absAngleDelta && absAngleDelta < 270) {
            angleModifier = angleDelta -180;
            //Reverse wheel direction
            targetSpeed = _targetSpeed * -1;
        }else if (270 <= absAngleDelta && absAngleDelta < 360) {
            angleModifier = angleDelta - 360;// or -(360-delta)
        }else {
            angleModifier = 0;
        }
        //Convert angle modifier to encoder
        angleModifier = angleToEncoder(angleModifier);
        //Add modifier to current total rotation to get new referance point
        targetRotation = currentRotation + angleModifier;
        //targetRotation = (double)(Math.round(targetRotation));
        if(targetRotation < 0) {
          targetRotation += 2048;
        }
        setMotors(targetRotation, targetSpeed);
    }

    public void setMotors(double _targetRotation, double _targetSpeed) {
        //This needs to be converted to the "native units of RPM" for the SparkMax, whatever that means, from the Meters per Seconds.
        targetSpeed = _targetSpeed * maxVel;
        velController.setReference(targetSpeed, ControlType.kVelocity);
        rotate_motor.set(ControlMode.Position, _targetRotation);
    }
    public double angleToEncoder(double angle){
        double encoder;
        encoder = (angle/360) * 2048;
        return encoder;
    }
    }
