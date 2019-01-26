package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.SubsystemManager;
import frc.robot.Updateable;

public class StateTracker implements Updateable {
    public SubsystemManager m_subsystem_manager;
    private double swerveVectors[][];
    public DriverStation driverStation;
     
    public StateTracker(SubsystemManager _subsystem_manager){
        m_subsystem_manager = _subsystem_manager;
    }

    //Use the swerve module vectors from PathFollower to track the robots Position as we move through auton
    public void update(double dt){    
        swerveVectors = m_subsystem_manager.m_drivetrain.m_swerveVectors;
        System.out.println(swerveVectors);
    }

}