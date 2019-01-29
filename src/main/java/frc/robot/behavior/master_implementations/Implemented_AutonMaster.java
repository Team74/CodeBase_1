package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;

import frc.robot.behavior.AutonMaster;

import frc.robot.behavior.master_implementations.Paths;
import frc.robot.behavior.master_implementations.PathFollower;

import jaci.pathfinder.Waypoint;

public class Implemented_AutonMaster extends AutonMaster {

    public PathFollower pathfollower;
    public Paths paths = new Paths();

    public Waypoint[] currentPath;
    public String currentAuto;

    public Implemented_AutonMaster(SubsystemManager subsystem_manager){
        super(subsystem_manager);
        pathfollower = new PathFollower(subsystem_manager);

        currentPath = paths.m_paths.get(currentAuto);
    }

     public void update(double dT){     }
}
