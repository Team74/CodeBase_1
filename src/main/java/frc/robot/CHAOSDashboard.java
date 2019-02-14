package frc.robot;

import frc.robot.SubsystemManager;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.shuffleboard.*;

public class CHAOSDashboard implements Updateable {

    private SubsystemManager mSubsystemManager;

    public ShuffleboardTab testTab;
    public NetworkTableEntry sanityCheck;
    public UsbCamera camera;

    public CHAOSDashboard(SubsystemManager _subsystemManager){
        mSubsystemManager = _subsystemManager;
        testTab = Shuffleboard.getTab("Test tab");
        Shuffleboard.selectTab("Test tab");
        sanityCheck = testTab.add("Sanity Check", true).getEntry();
        testTab.add("testTalonSRX", mSubsystemManager.m_robotMap.Steering_0);
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setVideoMode(PixelFormat.kMJPEG, 320, 240, 25);// width, height, framerate
    }

    public void update(double dt){
    }
}