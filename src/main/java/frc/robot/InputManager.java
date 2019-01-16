package frc.robot;

/*
Class that handles *human* input; for sensors, see SensorManager. Since the various TeleopMaster subclasses are the only thing that should be touching this class, it can get updated from there.
*/
import java.util.HashMap;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Updateable;


public class InputManager implements Updateable {

    private XboxController m_controller_0 = new XboxController(0);    //not sure if this needs further setup to connect to the network
    private XboxController m_controller_1 = new XboxController(1);


    //All these are values that can be read from outside the class
    public HashMap<String, Boolean> m_buttons_pressed  = new HashMap<String, Boolean>();
    
    public double m_c1_l_joystick_x = 0; public double m_c1_l_joystick_y = 0;
    public double m_c1_r_joystick_x = 0; public double m_c1_r_joystick_y = 0;

    public double m_c2_l_joystick_x = 0; public double m_c2_l_joystick_y = 0;
    public double m_c2_r_joystick_x = 0; public double m_c2_r_joystick_y = 0;


    public InputManager() {
        m_buttons_pressed.put("x", Boolean.FALSE);
        m_buttons_pressed.put("y", Boolean.FALSE);
        m_buttons_pressed.put("a", Boolean.FALSE);
        m_buttons_pressed.put("b", Boolean.FALSE);
        m_buttons_pressed.put("l_trigger", Boolean.FALSE);    //assume for now we'll never need the amount they're pressed down
        m_buttons_pressed.put("r_trigger", Boolean.FALSE);
        m_buttons_pressed.put("l_bumper", Boolean.FALSE);
        m_buttons_pressed.put("r_bumper", Boolean.FALSE);

        //do whatever networktables voodoo is necessary to connect to the controller
    }

    public void update(double dt) {
        m_buttons_pressed.put("x", m_controller_0.getAButton());
        m_buttons_pressed.put("y", m_controller_0.getAButton());
        m_buttons_pressed.put("a", m_controller_0.getAButton());
        m_buttons_pressed.put("b", m_controller_0.getAButton());
        m_buttons_pressed.put("l_trigger", m_controller_0.getAButton());
        m_buttons_pressed.put("r_trigger", m_controller_0.getAButton());
        m_buttons_pressed.put("l_bumper", m_controller_0.getAButton());
        m_buttons_pressed.put("r_bumper", m_controller_0.getAButton());

    }

}
