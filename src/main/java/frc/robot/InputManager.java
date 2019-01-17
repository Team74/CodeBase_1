package frc.robot;

/*
Class that handles *human* input; for sensors, see SensorManager. Since the various TeleopMaster subclasses are the only thing that should be touching this class, it can get updated from there.
*/
import java.util.HashMap;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Updateable;


public class InputManager implements Updateable {

    private XboxController m_controller_0 = new XboxController(0);
    private XboxController m_controller_1 = new XboxController(1);


    //All these are values that can be read from outside the class
    public HashMap<String, Boolean> m_buttons  = new HashMap<String, Boolean>();
    public HashMap<String, Double> m_joysticks  = new HashMap<String, Double>();



    public InputManager() {
        m_buttons.put("x", false);
        m_buttons.put("y", false);
        m_buttons.put("a", false);
        m_buttons.put("b", false);
        m_buttons.put("l_trigger", false);    //assume for now we'll never need the amount they're pressed down
        m_buttons.put("r_trigger", false);
        m_buttons.put("l_bumper", false);
        m_buttons.put("r_bumper", false);

        m_joysticks.put("0lx", (double)0);
        m_joysticks.put("0ly", (double)0);
        m_joysticks.put("0rx", (double)0);
        m_joysticks.put("0ry", (double)0);
        m_joysticks.put("1lx", (double)0);
        m_joysticks.put("1ly", (double)0);
        m_joysticks.put("1rx", (double)0);
        m_joysticks.put("1ry", (double)0);


        //do whatever networktables voodoo is necessary to connect to the controller
    }

    public void update(double dt) {
        m_buttons.put("x", m_controller_0.getXButton());
        m_buttons.put("y", m_controller_0.getYButton());
        m_buttons.put("a", m_controller_0.getAButton());
        m_buttons.put("b", m_controller_0.getBButton());
        //m_buttons.put("l_trigger", m_controller_0.getAButton());   Not sure how to grab triggers, look into it
        //m_buttons.put("r_trigger", m_controller_0.getAButton());
        m_buttons.put("l_bumper", m_controller_0.getBumper(Hand.kLeft));
        m_buttons.put("r_bumper", m_controller_0.getBumper(Hand.kRight));


        m_joysticks.put("0lx", m_controller_0.getX(Hand.kLeft));
        m_joysticks.put("0ly", m_controller_0.getY(Hand.kLeft));
        m_joysticks.put("0rx", m_controller_0.getX(Hand.kRight));
        m_joysticks.put("0ry", m_controller_0.getY(Hand.kRight));
        m_joysticks.put("1lx", m_controller_1.getX(Hand.kLeft));
        m_joysticks.put("1ly", m_controller_1.getY(Hand.kLeft));
        m_joysticks.put("1rx", m_controller_1.getX(Hand.kRight));
        m_joysticks.put("1ry", m_controller_1.getY(Hand.kRight));

    }

}
