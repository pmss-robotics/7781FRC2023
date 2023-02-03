/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;

// Angus Jan. 31: set up all the motors
/**
 * Add your docs here.
 */
public class Function_Arm {
    // establish motors
    Talon _armMotorTalon = new Talon(7);
    Talon _extendTalon = new Talon(8);
    public void driveSetup() {
		
		/* Set Neutral mode */
		_armMotorTalon.stopMotor();
        _armMotorTalon.setInverted(false);

        _extendTalon.stopMotor();
        _extendTalon.setInverted(false);
        
        //for mecanum, each wheel moves independently.
        //change setInverted for each direction for stick
        /* Configure output direction */
        
		/*_leftIntakeMotor.setInverted(false); // <<<<<< Adjust this until robot drives forward when stick is forward
		_rightIntakeMotor.setInverted(true); // <<<<<< Adjust this until robot drives forward when stick is forward*/
        
    }
    
    /*
     * Params
     * lift: Positive values move arm up, negative vice versa
     * extend: Positive values bring the arm out, negatice vice versa  
     */
    public void grabPeriodic(double lift, double extend) {
        //Alyn Jul 13, changed parameters of drivePeriodic to fit Mecanum driveCartesian
        _armMotorTalon.set(lift);
        _extendTalon.set(extend);
    }
}
