package frc.robot;

/*
Class that handles *human* input; for sensors, see SensorManager. Since the various TeleopMaster subclasses are the only thing that should be touching this class, it can get updated from there.
*/
import java.util.HashMap;

import edu.wpi.first.wpilibj.XboxController;

public class InputManager implements Updateable {

    private XboxController controller_0 = new XboxController(0);    //not sure if this needs further setup to connect to the network
    private XboxController controller_1 = new XboxController(1);


    //All these are values that can be read from outside the class
    public HashMap<String, Boolean> buttons_pressed  = new HashMap<String, Boolean>();
    
    public double c1_l_joystick_x = 0; public double c1_l_joystick_y = 0;
    public double c1_r_joystick_x = 0; public double c1_r_joystick_y = 0;

    public double c2_l_joystick_x = 0; public double c2_l_joystick_y = 0;
    public double c2_r_joystick_x = 0; public double c2_r_joystick_y = 0;


    public InputManager() {
        buttons_pressed.put("x", Boolean.FALSE);
        buttons_pressed.put("y", Boolean.FALSE);
        buttons_pressed.put("a", Boolean.FALSE);
        buttons_pressed.put("b", Boolean.FALSE);
        buttons_pressed.put("l_trigger", Boolean.FALSE);    //assume for now we'll never need the amount they're pressed down
        buttons_pressed.put("r_trigger", Boolean.FALSE);
        buttons_pressed.put("l_bumper", Boolean.FALSE);
        buttons_pressed.put("r_bumper", Boolean.FALSE);

        //do whatever networktables voodoo is necessary to connect to the controller
    }

    public void update(double dt) {
        //however it is that we get input from the controller
        //and store the values in buttons_pressed/etc.
    }

}
