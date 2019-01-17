package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;
import frc.robot.InputManager;
import frc.robot.behavior.TeleopMaster;

public class Test1_TeleopMaster extends TeleopMaster {
    public Test1_TeleopMaster(SubsystemManager subsystem_manager, InputManager input_manager) { super(subsystem_manager, input_manager); }

    public void update(double dt) {  
        m_subsystem_manager.m_drivetrain.setMove(m_input_manager.m_joysticks.get("0ly"), m_input_manager.m_joysticks.get("0ry"));
    }
}
