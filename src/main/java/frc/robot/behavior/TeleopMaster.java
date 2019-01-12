package frc.robot.behavior;

import frc.robot.behavior.Master;
import frc.robot.SubsystemManager;
import frc.robot.InputManager;

abstract public class TeleopMaster extends Master {

    protected InputManager input_manager;

    protected TeleopMaster(SubsystemManager subsystem_manager, InputManager _input_manager) {
        super(subsystem_manager);
        input_manager = _input_manager;
    }
}
