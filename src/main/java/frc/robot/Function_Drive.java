package frc.robot;

// Phoenix library

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//Alyn Jul 13, input Mecanum drive
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.math.controller.PIDController;

public class Function_Drive {
    /* MOTORS*/
	// Setting ID for motors
	// Drive train motors
	// Talons are masters; Victors are slaves

/*     // Set ID for front left motor as Talon SRX 3
    Talon _frontLeftMotor = new Talon(3);
    // Set ID for front right motor as Talon SRX 6
    Talon _frontRightMotor = new Talon(6);
    // Set ID for left slave motor as Victor SPX 4
    Talon _backLeftMotor = new Talon(4);
    // Set ID for right slave motor as Victor SPX 5
    Talon _backRightMotor = new Talon(5);
    DifferentialDrive _drive = new DifferentialDrive(_frontLeftMotor, _frontRightMotor); */

    // Alyn Jul 13, define driveA.
    // MUST CHANGE NUMBER ONCE IDed
    // Set ID for front left motor as Talon SRX 2
    Talon _frontLeftMotor = new Talon(4);
    // Set ID for front right motor as Talon SRX 3
    Talon _frontRightMotor = new Talon(2);
    // Set ID for rear left motor as Talon SPX 6
    Talon _rearLeftMotor = new Talon(1);
    // Set ID for rear right motor as Talon SPX 1
    Talon _rearRightMotor = new Talon(3);

    // //attempted to fix       
    // // Alyn Jul 13, define driveA.
    // // MUST CHANGE NUMBER ONCE IDed
    // // Set ID for front left motor as Talon SRX 2
    // Talon _frontLeftMotor = new Talon(2);
    // // Set ID for front right motor as Talon SRX 3
    // Talon _frontRightMotor = new Talon(3);
    // // Set ID for rear left motor as Talon SPX 6
    // Talon _rearLeftMotor = new Talon(1);
    // // Set ID for rear right motor as Talon SPX 1
    // Talon _rearRightMotor = new Talon(6);

    

    MecanumDrive _driveA = new MecanumDrive(_frontLeftMotor, _rearLeftMotor, _frontRightMotor, _rearRightMotor);



    public void driveSetup() {
		
		/* Set Neutral mode */
		_frontLeftMotor.stopMotor();
        _frontRightMotor.stopMotor();
        _rearLeftMotor.stopMotor();
		_rearRightMotor.stopMotor();
        
        //for mecanum, each wheel moves independently.
        //change setInverted for each direction for stick
        /* Configure output direction */
        
		_frontLeftMotor.setInverted(false); // <<<<<< Adjust this until robot drives forward when stick is forward
		_frontRightMotor.setInverted(true); // <<<<<< Adjust this until robot drives forward when stick is forward
        _rearLeftMotor.setInverted(false); // <<<<<< Adjust this until robot drives forward when stick is forward
		_rearRightMotor.setInverted(true); // <<<<<< Adjust this until robot drives forward when stick is forward
        
    }
    
    public void drivePeriodic(double ySpeed, double xSpeed, double zRotation) {
        //Alyn Jul 13, changed parameters of drivePeriodic to fit Mecanum driveCartesian
        _driveA.driveCartesian(ySpeed, xSpeed, zRotation);
    }
}