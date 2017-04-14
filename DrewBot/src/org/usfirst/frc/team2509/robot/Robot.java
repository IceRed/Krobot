package org.usfirst.frc.team2509.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	//Define Variables 
	 RobotDrive myRobot;
	 Joystick stick1;
	 Talon motorFL;
	 Talon motorFR;
	 Talon motorBL;
	 Talon motorBR;
	 Talon sweeper;
	 UsbCamera cam;
	 
	 //Robot startup (Initialize)
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		stick1 = new Joystick(0);
		motorFL = new Talon(0);
		motorFR = new Talon(1);
		motorBL = new Talon(2);
		motorBR = new Talon(3);
		sweeper = new Talon(4);
		
		cam = CameraServer.getInstance().startAutomaticCapture("Camera", 0);
		cam.setResolution(160, 120);
		
		myRobot = new RobotDrive(motorFL, motorFR, motorBL, motorBR);
		
		
	}

	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
	}

	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	public void teleopPeriodic() {
		myRobot.mecanumDrive_Cartesian(stick1.getX(), stick1.getY(), stick1.getZ(), 0);
		if(stick1.getRawButton(1)){
			sweeper.set(1);
		}else{
			sweeper.set(0);
		}
	}
	public void testPeriodic() {
	}
}

