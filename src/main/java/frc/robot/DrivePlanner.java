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

    public Speed currentSpeed = Speed.Mid;

    public DrivePlanner(SubsystemManager subsystemManager){
        m_subsystemManager = subsystemManager;
    }

    public void setSpeed(){
        switch(currentSpeed){
            case Low:
                System.out.println("Set Speed: Low");
                m_subsystemManager.m_drivetrain.lf.kMaxVel = 1000;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = 1000;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = 1000;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = 1000;
                break;
            case Mid:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = 3000;
                break;
            case High:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = 5000;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = 5000;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = 5000;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = 5000;
                break;
            default:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = 3000;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = 3000;
                break;

        }
    }

    public void update(double dt){
        setSpeed();
        m_subsystemManager.m_drivetrain.setMove(speed, angle, rotation);
    }
}