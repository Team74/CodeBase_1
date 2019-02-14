package frc.robot;

import frc.robot.SubsystemManager;
import frc.robot.Updateable;
import frc.robot.Drivetrain;

public class DrivePlanner implements Updateable {
    private SubsystemManager m_subsystemManager;
    public Drivetrain m_drivetrain;

    public double speed = 0.0;
    public double angle = 0.0;
    public double rotation = 0.0;

    public static enum Speed{
        Low, Mid, High;
    }

    public DrivePlanner(SubsystemManager subsystemManager){
        m_subsystemManager = subsystemManager;
    }

    public void setSpeed(Speed _speed){
        switch(_speed){
            case Low:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = 1000;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = 1000;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = 1000;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = 1000;
            case Mid:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = 2000;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = 2000;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = 2000;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = 2000;
            case High:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = 3000;
            default:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = 2000;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = 2000;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = 2000;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = 2000;

        }
    }

    public void update(double dt){
        m_subsystemManager.m_drivetrain.setMove(speed, angle, rotation);
    }
}