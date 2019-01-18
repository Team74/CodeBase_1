package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;
import frc.robot.InputManager;
import frc.robot.behavior.TeleopMaster;

public class Test1_TeleopMaster extends TeleopMaster {
    public Test1_TeleopMaster(SubsystemManager subsystem_manager, InputManager input_manager) { super(subsystem_manager, input_manager); }

    public void update(double dt) {  
        
        double magnitude = Math.sqrt(Math.pow(m_input_manager.m_joysticks.get("0lx"), 2) + Math.pow(m_input_manager.m_joysticks.get("0ly"), 2));
        //needs to be adjusted from square to circular coordinates -- divide by sqrt(2) on corners but just 1 on vert/horiz
        double angle = Math.atan2(m_input_manager.m_joysticks.get("0lx"), m_input_manager.m_joysticks.get("0ly"));
        double rotation = m_input_manager.m_joysticks.get("0rx");

        m_subsystem_manager.m_drivetrain.setMove(magnitude, angle, rotation);
    }
}
