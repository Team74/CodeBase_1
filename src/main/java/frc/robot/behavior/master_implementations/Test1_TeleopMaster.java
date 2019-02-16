package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;
import frc.robot.SwerveModule;
import frc.robot.InputManager;
import frc.robot.DrivePlanner;

import frc.robot.behavior.TeleopMaster;

import frc.utils.Utilities;

import java.util.HashMap;

public class Test1_TeleopMaster extends TeleopMaster {

    SwerveModule currentMotor;
    String output_extra;

    private DrivePlanner m_drivePlanner;

    private HashMap<String, Boolean> m_buttons;
    private HashMap<String, Double> m_joysticks;

    private double kDeadband = 0.1;
    
    public double lx;
    public double ly;
    public double rx;

    public double gyroVal;

    public Test1_TeleopMaster(SubsystemManager subsystem_manager, InputManager input_manager) {
        super(subsystem_manager, input_manager);

        currentMotor = m_subsystem_manager.m_drivetrain.lb;
        output_extra = "lb: ";

        m_drivePlanner = m_subsystem_manager.m_driveplanner;
        m_buttons = m_input_manager.m_buttons;
        m_joysticks = m_input_manager.m_joysticks;
     }

    public void update(double dt) {  
        /*
        if(m_input_manager.m_buttons.get("0a")) {
            currentMotor = m_subsystem_manager.m_drivetrain.lb;
            output_extra = "lb: ";
        }
        else if(m_input_manager.m_buttons.get("0x")) {
            currentMotor = m_subsystem_manager.m_drivetrain.lf;
            output_extra = "lf: ";
        }        
        else if(m_input_manager.m_buttons.get("0y")) {
            currentMotor = m_subsystem_manager.m_drivetrain.rf;
            output_extra = "rf: ";
        }       
        else if(m_input_manager.m_buttons.get("0b")) {
            currentMotor = m_subsystem_manager.m_drivetrain.rb;
            output_extra = "rb: ";
        }        
        double encoderCount = currentMotor.rotate_motor.getSelectedSensorPosition(0);
        double encoderVelocity = currentMotor.rotate_motor.getSelectedSensorVelocity(0);
        System.out.println(output_extra + encoderCount);
        if (m_input_manager.m_buttons.get("0r_bumper")){
            currentMotor.setMotors(
                0,
                0
                );
        } else if (m_input_manager.m_buttons.get("0l_bumper")) {
            currentMotor.setMotorsPercentOutput(
                m_input_manager.m_joysticks.get("0rx"),
                m_input_manager.m_joysticks.get("0ly"));
        } else {
            currentMotor.rotate_motor.stopMotor();
        }
        */
        if (m_buttons.get("0l_trigger")) {
            m_drivePlanner.currentSpeed = DrivePlanner.Speed.Low;
        } else if (m_buttons.get("0r_trigger")){
            m_drivePlanner.currentSpeed = DrivePlanner.Speed.High;
        } else {
            m_drivePlanner.currentSpeed = DrivePlanner.Speed.Mid;
        }

        if (m_buttons.get("0d_down") || m_buttons.get("0d_up") || m_buttons.get("0d_right") || m_buttons.get("0d_left")) {
            if(m_buttons.get("0d_down")) {
                m_drivePlanner.angle = Math.PI;
                m_drivePlanner.speed = .5;
                m_drivePlanner.rotation = 0;
            } else if (m_buttons.get("0d_up")) {
                m_drivePlanner.angle = 0;
                m_drivePlanner.speed = .5;
                m_drivePlanner.rotation = 0;
            } else if (m_buttons.get("0d_right")) {
                m_drivePlanner.angle = .5 * Math.PI;
                m_drivePlanner.speed = .5;
                m_drivePlanner.rotation = 0;
            } else if (m_buttons.get("0d_left")) {
                m_drivePlanner.angle = 1.5 * Math.PI;
                m_drivePlanner.speed = -.5;
                m_drivePlanner.rotation = 0;
            }
            //Handle the swerve drive
        } else {
            lx = m_joysticks.get("0lx");
            ly = m_joysticks.get("0ly");
            rx = m_joysticks.get("0rx");

            lx = Utilities.handleDeadband(lx, kDeadband);
            ly = Utilities.handleDeadband(ly, kDeadband);
            rx = Utilities.handleDeadband(rx, kDeadband);

            m_drivePlanner.speed = Math.hypot(lx, ly);
            m_drivePlanner.angle = Math.atan2(ly, lx);
            m_drivePlanner.rotation = rx/30;

            gyroVal = m_subsystem_manager.m_drivetrain.gyro.getAngle();
            gyroVal *= Math.PI / 180;

            m_drivePlanner.angle -= gyroVal;

            m_drivePlanner.angle %= 2*Math.PI;
            m_drivePlanner.angle += (m_drivePlanner.angle < -Math.PI) ? 2*Math.PI : 0;
            m_drivePlanner.angle -= (m_drivePlanner.angle > Math.PI) ? 2*Math.PI : 0;
            m_drivePlanner.angle += Math.PI/2;
        }        

        if (m_buttons.get("0back")){
            System.out.println("Gyro Reset");
            m_subsystem_manager.m_drivetrain.gyro.reset();
        }
    }//Function closing
}
