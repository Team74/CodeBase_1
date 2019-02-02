package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;
import frc.robot.SwerveModule;
import frc.robot.InputManager;
import frc.robot.behavior.TeleopMaster;

public class Test1_TeleopMaster extends TeleopMaster {
public SwerveModule lf;

    public Test1_TeleopMaster(SubsystemManager subsystem_manager, InputManager input_manager) {
        super(subsystem_manager, input_manager);
     }

    public void update(double dt) {  

        m_subsystem_manager.m_drivetrain.lf.setMotorsPercentOutput(
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
