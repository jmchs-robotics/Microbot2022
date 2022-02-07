// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.robot.Constants;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Drivetrain extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private PWMTalonFX left1;
private PWMTalonFX left2;
private PWMTalonFX left3;
private MotorControllerGroup leftMotors;
private PWMTalonFX right1;
private PWMTalonFX right2;
private PWMTalonFX right3;
private MotorControllerGroup rightMotors;
private DifferentialDrive drive;

private WPI_TalonSRX testLeft1;
private WPI_TalonSRX testRight1;
private WPI_TalonSRX testLeft2;
private WPI_TalonSRX testRight2;
private WPI_TalonSRX testLeft3;
private WPI_TalonSRX testRight3;

public static final double kPGyroConstant = 0.01;
public static final double kPGyroTurnConstant = 0.01;
public static final double kEncoderTicksPerInch = 325.9493209;
private final ADXRS450_Gyro gyro = RobotContainer.roborioGyro;
private static final double minVBusOutVal = 0.2;

    public Drivetrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

//MICROBOT MOTORS

/*
left1 = new PWMTalonFX(0);
 addChild("left1",left1);
 left1.setInverted(false);

left2 = new PWMTalonFX(1);
 addChild("left2",left2);
 left2.setInverted(false);

left3 = new PWMTalonFX(2);
 addChild("left3",left3);
 left3.setInverted(false);

right1 = new PWMTalonFX(3);
 addChild("right1",right1);
 right1.setInverted(true);

right2 = new PWMTalonFX(4);
 addChild("right2",right2);
 right2.setInverted(true);

right3 = new PWMTalonFX(5);
 addChild("right3",right3);
 right3.setInverted(true);
 */

//RIPLEY TEST MOTORS

testLeft1 = new WPI_TalonSRX(Constants.ripleyLMID);
 addChild("testleft1",testLeft1);
 testLeft1.setInverted(false);

testLeft2 = new WPI_TalonSRX(Constants.ripleyLF1ID);
 addChild("testleft2",testLeft2);
 testLeft2.setInverted(false);

testLeft3 = new WPI_TalonSRX(Constants.ripleyLF2ID);
 addChild("testleft3",testLeft3);
 testLeft3.setInverted(false);

testRight1 = new WPI_TalonSRX(Constants.ripleyRMID);
 addChild("testright1",testRight1);
 testRight1.setInverted(true);

testRight2 = new WPI_TalonSRX(Constants.ripleyRF1ID);
 addChild("testright2",testRight2);
 testRight2.setInverted(true);

testRight3 = new WPI_TalonSRX(Constants.ripleyRF2ID);
 addChild("testright3",testRight3);
 testRight3.setInverted(true);

leftMotors = new MotorControllerGroup(testLeft1, testLeft2 , testLeft3);
 addChild("leftMotors",leftMotors);

rightMotors = new MotorControllerGroup(testRight1, testRight2, testRight3);
 addChild("rightMotors",rightMotors);
 

drive = new DifferentialDrive(leftMotors, rightMotors);
 addChild("Drive",drive);
drive.setSafetyEnabled(true);
drive.setExpiration(0.1);
drive.setMaxOutput(1.0);



        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    public DifferentialDrive getDrive() {
        return drive;
    }

    public void arcadeDrive() {
		drive.arcadeDrive(-RobotContainer.getdriveStick().getLeftY(), RobotContainer.getdriveStick().getLeftX());
	}

    public void tankDrive() {
		leftMotors.set(RobotContainer.getdriveStick().getLeftY());
		rightMotors.set(RobotContainer.getdriveStick().getRightY());
	}
    
    public void tankDrive(double leftVal, double rightVal) {
		leftMotors.set(leftVal);
		rightMotors.set(rightVal);
	}

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public double getLeftEncoderPos(int pidIdx) {
		return testLeft2.getSelectedSensorPosition(pidIdx);
	}

	public double getRightEncoderPos(int pidIdx) {
		return testRight2.getSelectedSensorPosition(pidIdx);
	}	

	//goodies for gyro
	private void initGyro() {
		gyro.calibrate();
	}

	public void resetGyro() {
		gyro.reset();
	}

	public double getGyroHeading() {
		return gyro.getAngle();
	}

	public double thresholdVBus(double val) {
		if(Math.abs(val) < minVBusOutVal) {
			val = Math.signum(val) * minVBusOutVal;
		}
		return val;
	}

	public void resetEncoders() {
		testLeft2.setSelectedSensorPosition(0, 0, 20);
		testRight2.setSelectedSensorPosition(0, 0, 20);
	}

}
