package frc.robot;

import frc.robot.SubsystemManager;

import frc.utils.Utilities;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.*;

public class CHAOSDashboard implements Updateable {

    private SubsystemManager mSubsystemManager;

    public ShuffleboardTab testTab;
    public NetworkTableEntry sanityCheck;
    
    public NetworkTableEntry lfAngle;
    public NetworkTableEntry lbAngle;
    public NetworkTableEntry rfAngle;
    public NetworkTableEntry rbAngle;

    public UsbCamera camera;

    public CHAOSDashboard(SubsystemManager _subsystemManager){
        mSubsystemManager = _subsystemManager;
        testTab = Shuffleboard.getTab("Test tab");
        Shuffleboard.selectTab("Test tab");

        sanityCheck = testTab.add("Sanity Check", true).getEntry();

        lfAngle = testTab.add("LF Angle", mSubsystemManager.m_drivetrain.lf.currentAngle).getEntry();
        lbAngle = testTab.add("LB Angle", mSubsystemManager.m_drivetrain.lf.currentAngle).getEntry();
        rfAngle = testTab.add("RF Angle", mSubsystemManager.m_drivetrain.lf.currentAngle).getEntry();
        rbAngle = testTab.add("RB Angle", mSubsystemManager.m_drivetrain.lf.currentAngle).getEntry();

        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setVideoMode(PixelFormat.kMJPEG, 320, 240, 25);// width, height, framerate
    }

    public void update(double dt){
        lfAngle.setDouble(mSubsystemManager.m_drivetrain.lf.currentAngle);
        lbAngle.setDouble(mSubsystemManager.m_drivetrain.lb.currentAngle);
        rfAngle.setDouble(mSubsystemManager.m_drivetrain.rf.currentAngle);
        rbAngle.setDouble(mSubsystemManager.m_drivetrain.rb.currentAngle);
    }
}