package frc.robot.behavior.master_implementations;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

//Using Pathfinder to create a path
//https://github.com/JacisNonsense/Pathfinder/wiki/Pathfinder-for-FRC---Java
public class Paths {

    Waypoint[] testPath;

    public Paths(){
        testPath = new Waypoint[] {
            new Waypoint(-4, -1, Pathfinder.d2r(-45)),
            new Waypoint(-2, -2, 0),
            new Waypoint(0, 0, 0)
        };
    }
}