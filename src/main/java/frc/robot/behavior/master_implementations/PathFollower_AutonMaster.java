package frc.robot.behavior.master_implementations;

import frc.robot.SubsystemManager;
import frc.robot.behavior.AutonMaster;
import frc.robot.RobotMap;
import frc.robot.Drivetrain;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.SwerveModifier;

public class PathFollower_AutonMaster extends AutonMaster {

    public final double timeStep = 0.0;
    public RobotMap map = new RobotMap();
    public Drivetrain drive = new Drivetrain(RobotMap map);
    private SwerveModifier.Mode pathMode = SwerveModifier.Mode.SWERVE_DEFAULT;
    private Trajectory trajectory;
    private SwerveModifier modifier;
    private Trajectory lf;
    private Trajectory rf;
    private Trajectory lb;
    private Trajectory rb;
    private EncoderFollower lfFollower = new EncoderFollower();
    private EncoderFollower rfFollower = new EncoderFollower();
    private EncoderFollower lbFollower = new EncoderFollower();
    private EncoderFollower rbFollower = new EncoderFollower();

    public PathFollower_AutonMaster(SubsystemManager subsystem_manager) {
        super(subsystem_manager);
    }

    public void update(double dt) {  
        //Using Pathfinder to follow the path
        //https://github.com/JacisNonsense/Pathfinder/wiki/Pathfinder-for-FRC---Java
        
        //null needs to be replaced with a get encoder position
        drive.lf.setModule(Pathfinder.boundHalfDegrees(Pathfinder.r2d(lfFollower.getHeading())), lfFollower.calculate(null));
        drive.rf.setModule(Pathfinder.boundHalfDegrees(Pathfinder.r2d(rfFollower.getHeading())), rfFollower.calculate(null));
        drive.lb.setModule(Pathfinder.boundHalfDegrees(Pathfinder.r2d(lbFollower.getHeading())), lbFollower.calculate(null));
        drive.rb.setModule(Pathfinder.boundHalfDegrees(Pathfinder.r2d(rbFollower.getHeading())), rbFollower.calculate(null));
        
        

    }

    public void pathToTrajectory(Waypoint[] path){
        //Set up trajectory configs
        Trajectory.Config config = new Trajectory.Config(
            Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, timeStep, map.maxVel, map.maxAccel, map.maxJerk
        );
        //Create trajectory
        trajectory = Pathfinder.generate(path, config);
        //Create the modifier object
        modifier = new SwerveModifier(trajectory);
        // Generate the individual wheel trajectories using the original trajectory
        // as the centre
        modifier.modify(map.wheelBaseWidth, map.wheelBaseDepth, pathMode);

        lf = modifier.getFrontLeftTrajectory();       // Get the Front Left wheel
        rf = modifier.getFrontRightTrajectory();      // Get the Front Right wheel
        lb = modifier.getBackLeftTrajectory();        // Get the Back Left wheel
        rb = modifier.getBackRightTrajectory();       // Get the Back Right wheel
        lfFollower.setTrajectory(lf);
        rfFollower.setTrajectory(rf);
        lbFollower.setTrajectory(lb);
        rbFollower.setTrajectory(rb);
        //Null needs to be replaced with the getPosition() for encoders. Not the integrated NEOs though because they return doubles.
        lfFollower.configureEncoder(null , map.countsPerRev, map.wheelDiameter);
        rfFollower.configureEncoder(null, map.countsPerRev, map.wheelDiameter);
        lbFollower.configureEncoder(null, map.countsPerRev, map.wheelDiameter);
        rbFollower.configureEncoder(null, map.countsPerRev, map.wheelDiameter);
    }
}
