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
    private final double kLowMaxVel = 2000;
    private final double kMidMaxVel = 3000;
    private final double  kHighMaxVel = 5000;

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
                m_subsystemManager.m_drivetrain.lf.kMaxVel = kLowMaxVel;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = kLowMaxVel;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = kLowMaxVel;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = kLowMaxVel;
                break;
            case Mid:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = kMidMaxVel;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = kMidMaxVel;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = kMidMaxVel;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = kMidMaxVel;
                break;
            case High:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = kHighMaxVel;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = kHighMaxVel;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = kHighMaxVel;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = kHighMaxVel;
                break;
            default:
                m_subsystemManager.m_drivetrain.lf.kMaxVel = kMidMaxVel;
                m_subsystemManager.m_drivetrain.lb.kMaxVel = kMidMaxVel;
                m_subsystemManager.m_drivetrain.rf.kMaxVel = kMidMaxVel;
                m_subsystemManager.m_drivetrain.rb.kMaxVel = kMidMaxVel;
                break;

        }
    }

    public void update(double dt){
        setSpeed();
        m_subsystemManager.m_drivetrain.setMove(speed, angle, rotation);
    }
}