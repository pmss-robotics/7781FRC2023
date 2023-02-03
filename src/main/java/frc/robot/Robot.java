// Robot.java: The main file of the robot
// - handles joystick controls

// Author: Alyn Shao
// CO-Author: Bob Xiong
// Date: Jul 13, 2021

package frc.robot;

/* LIBRARY IMPORTS */
//Import functions (written in same directory)

import static java.lang.Math.abs;

import edu.wpi.first.wpilibj.TimedRobot; //import timed robot
import edu.wpi.first.wpilibj.XboxController;

//autonomous period.
public class Robot extends TimedRobot{
	long servoTime = System.currentTimeMillis();
	
	long autonomousTime = System.currentTimeMillis();

	/* DEFINING FUNCTIONS */
	Function_Drive driveTrain = new Function_Drive();
	Function_Intake intake = new Function_Intake();
	Function_Arm arm = new Function_Arm();

	XboxController _gamepad = new XboxController(0);
	int c = 0;


	@Override
	public void autonomousInit() {
		driveTrain.driveSetup();
		intake.driveSetup();
		arm.driveSetup();
		//intake.spinIn(true);
	}

	@Override 
	public void autonomousPeriodic(){
		System.out.println(c);
		if(c<150){
            //Alyn Jul 13, autonomous period, move forward 0.5. x is forward
            //drivePeriodic(double ySpeed, double xSpeed, double grab)
			driveTrain.drivePeriodic(0.5, 0, 0);
		}
		c+=1;
		//System.out.println((System.currentTimeMillis()-autonomousTime));
		//intake.spinIn(false);
	}

    @Override
    public void teleopPeriodic() {	
        
		/*---- GAMEPAD ----*/
		// Jul 27: ONCE CHALLENGE ANNOUNCED, ADD CODE TO EACH BUTTON
		// EG. // trough servo
			//boolean changeIntakeState = _gamepad.getRawButton(1);
		
		// sensitivity control INVERTED ON LIANG'S CONTROLLER
		// Jul 27 Alyn: ASK BOB WHY USE THIS FORMULA FOR SENSATIVITY
		//double sensitivity = 1-( _gamepad.getThrottle() + 1)/2;
		double sensitivity = 0.3;
        
		/*---- DRIVE ----*/
		// Going forwards and backwards by tracking joystick position

        double backforth = 1 * _gamepad.getLeftX();
        // Using deadband so minor joystick movements will not pass through and move the robot
        backforth = Deadband(backforth);
		backforth = sensitivity*backforth;

		// Going left and right by tracking joystick position
		double leftright = 1 * _gamepad.getLeftY();
		leftright = Deadband(leftright);
		leftright = sensitivity*leftright;

		// Motor grab by tracking joystick position (bugged and corresponds to RT)
		double grab_intake = 1 * _gamepad.getRightY();
		double grab_outtake = -1 * _gamepad.getRightX();
		double grab = grab_intake + grab_outtake;
		grab = Deadband(grab);
		grab = sensitivity*grab;

		// Motor grab by tracking joystick position (for whatever reason getLeftTriggerAxis is a bit bugged and it corresponds to the controller's right y axis)
		double lift = -1 * _gamepad.getRightTriggerAxis();
		lift = Deadband(lift);
		lift = sensitivity*lift;


	
		// Use driveCartesian (y, x, z) [NOT X, Y, Z] to control robot movement
		driveTrain.drivePeriodic(backforth,0,leftright); 
		intake.grabPeriodic(grab);
		arm.grabPeriodic(lift, lift); //change the 2nd lift to extension later


    }

    /* UTILITY FUNCTIONS */
	/** Deadband 5 percent, used on the gamepad */
	double Deadband(double value) {
		// inside deadband
		if (abs(value) <= 0.05){
			return 0;
		}
		/* Outside deadband */
		return value;
	}
}