package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;
import frc.robot.Updateable;

public class PositionTracker implements Updateable {
    public SubsystemManager m_subsystem_manager;
     
    public PositionTracker(SubsystemManager _subsystem_manager){
        m_subsystem_manager = _subsystem_manager;
    }

    //Use swerve module vectors to track positino over time
    public void update(double dt){

    }

}