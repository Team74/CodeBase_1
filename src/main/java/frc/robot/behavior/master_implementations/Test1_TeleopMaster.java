package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;
import frc.robot.SwerveModule;
import frc.robot.InputManager;
import frc.robot.behavior.TeleopMaster;

public class Test1_TeleopMaster extends TeleopMaster {

    SwerveModule currentMotor;
    String output_extra;


    public Test1_TeleopMaster(SubsystemManager subsystem_manager, InputManager input_manager) {
        super(subsystem_manager, input_manager);

        currentMotor = m_subsystem_manager.m_drivetrain.lb;
        output_extra = "lb: ";
     }

    public void update(double dt) {  


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
        System.out.println(output_extra + encoderCount);
        currentMotor.setMotorsPercentOutput(
            m_input_manager.m_joysticks.get("0rx"),
            m_input_manager.m_joysticks.get("0ly")
            );

        /*
        double magnitude = Math.sqrt(Math.pow(m_input_manager.m_joysticks.get("0lx"), 2) + Math.pow(m_input_manager.m_joysticks.get("0ly"), 2));
        double angle = Math.atan2(m_input_manager.m_joysticks.get("0lx"), m_input_manager.m_joysticks.get("0ly"));
        double rotation = m_input_manager.m_joysticks.get("0rx");

        m_subsystem_manager.m_drivetrain.setMove(magnitude, angle, rotation);
        */
    }
}
