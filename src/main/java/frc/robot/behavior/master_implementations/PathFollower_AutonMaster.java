package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;
import frc.robot.behavior.AutonMaster;

public class PathFollower_AutonMaster extends AutonMaster {
    public PathFollower_AutonMaster(SubsystemManager subsystem_manager) { super(subsystem_manager); }

    public void update(double dt) {  // !!  PSEUDOCODE ALERT  !!
        // getPathFromFile();
        // doStuffWithIt();    //Obviously these exact commands won't be used here, but something like them
        // subsystem_manager.autonMove(0.5, path.thingbob.angle, whatever)
        // subsystem_manager.lift_handle(10,5,numbers)
    }
}
