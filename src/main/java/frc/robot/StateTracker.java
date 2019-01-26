package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.SubsystemManager;
import frc.robot.Updateable;

public class StateTracker implements Updateable {
    public SubsystemManager m_subsystem_manager;
    private double swerveVectors[][];
    public DriverStation driverStation;
    public double xPose;
    public double yPose;
    public double heading;

    public StateTracker(SubsystemManager _subsystem_manager, double _initialX, double _initialY){
        m_subsystem_manager = _subsystem_manager;
        xPose = _initialX;
        yPose = _initialY;
    }

    //Use the swerve module vectors from PathFollower to track the robots Position as we move through auton
    public void update(double dt){
        double xPoseMod = 0;
        double yPoseMod = 0;    
        swerveVectors = m_subsystem_manager.m_drivetrain.m_swerveVectors;
        System.out.println(swerveVectors);
        xPose += xPoseMod;
        yPose += yPoseMod;
        heading = m_subsystem_manager.m_drivetrain.gyro.getAngle();
    }

}