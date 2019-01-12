package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.SubsystemManager;
import frc.robot.InputManager;
import frc.robot.behavior.master_implementations.PathFollower_AutonMaster;
import frc.robot.behavior.master_implementations.PrimaryTeleopMaster;

public class Robot extends TimedRobot {

    SubsystemManager subsystem_manager;
    InputManager input_manager;

    Timer timer = new Timer();
    double dt = 0;
    private void updateTime() {
        dt = timer.get();
        timer.reset();
    }


    public void robotInit() {
        subsystem_manager = new SubsystemManager(); //this will set up the whole robot and its subsystems
        input_manager = new InputManager(); //this is the significantly smaller bit of code that handles input from the xbox controllers and such

        timer.stop();
        timer.reset();
    }
    public void autonomousInit() {
        subsystem_manager.setCurrentMaster(new PathFollower_AutonMaster(subsystem_manager));
         //or whatever other auton we want -- we'll probably need something for SmartDashboard eventually
         timer.start();
        }   
    public void autonomousPeriodic() {
        updateTime();
        subsystem_manager.update(dt); 
    }
    public void teleopInit() {
        subsystem_manager.setCurrentMaster(new PrimaryTeleopMaster(subsystem_manager, input_manager));
        //similarly, if we want to do, say, a different control scheme, a different teleopmaster could be subbed in that would interpret the inputs differently
        timer.reset();
        timer.start();
    }
    public void teleopPeriodic() {
        updateTime();
        input_manager.update(dt);
        subsystem_manager.update(dt);
    }

}
