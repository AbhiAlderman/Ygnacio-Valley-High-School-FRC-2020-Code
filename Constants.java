/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.SPI;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Talons are motors
    //Solenoids are for pneumatics
    //Booleans and doubles are values used in other files, placed here for convenient editing

    //Shooter
    public TalonFX flyWheel = new TalonFX(12); 
    public TalonFX feederWheel = new TalonFX(10);
    
    //Storage
    public TalonFX storageMotor = new TalonFX(11);

    //Intake
    public TalonFX intakeMotor = new TalonFX(0);
    public Solenoid intakePiston = new Solenoid(0);

    //Back Right Swerve Module
    public TalonSRX backRightAngle = new TalonSRX(6);
    public int backRightAngleOffset = -1755;
    public boolean backRightAngleSensorPhase = false;
    public boolean backRightAngleInverted = false;
    public TalonSRX backRightDrive = new TalonSRX(5);
    public boolean backRightDriveSensorPhase = true;
    public boolean backRightDriveInverted = true;
    public double backRightAngleKP = 2;
    public double backRightAngleKD = 100;

    //Back Left Swerve Module
    public TalonSRX backLeftAngle = new TalonSRX(4);
    public int backLeftAngleOffset = -908;
    public boolean backLeftAngleSensorPhase = true;
    public boolean backLeftAngleInverted = true;
    public TalonSRX backLeftDrive = new TalonSRX(2);
    public boolean backLeftDriveSensorPhase = false;
    public boolean backLeftDriveInverted = false;
    public double backLeftAngleKP = 2;
    public double backLeftAngleKD = 100;

    //Front Right Swerve Module
    public TalonSRX frontRightAngle = new TalonSRX(3);
    public int frontRightAngleOffset = 1566;
    public boolean frontRightAngleSensorPhase = true;
    public boolean frontRightAngleInverted = true;
    public TalonSRX frontRightDrive = new TalonSRX(9);
    public boolean frontRightDriveSensorPhase = false;
    public boolean frontRightDriveInverted = false;
    public double frontRightAngleKP = 2;
    public double frontRightAngleKD = 100;

    //Front Left Swerve Module
    public TalonSRX frontLeftAngle = new TalonSRX(7);
    public int frontLeftAngleOffset = -393;
    public boolean frontLeftAngleSensorPhase = false;
    public boolean frontLeftAngleInverted = false;
    public TalonSRX frontLeftDrive = new TalonSRX(8);
    public boolean frontLeftDriveSensorPhase = true;
    public boolean frontLeftDriveInverted = true;
    public double frontLeftAngleKP = 2;
    public double frontLeftAngleKD = 100;
    
    //Control Panel Mechanism
    public TalonFX panelMotor = new TalonFX(15);
    public Solenoid panelPiston = new Solenoid(2);

    //Thresholds for control panel colors
    public double blueBlueGreater = 0.34;
    public double blueRedLess = 0.2;
    public double yellowGreenGreater = 0.5;
    public double yellowBlueLess = 0.2;
    public double redRedGreater = 0.38;
    public double redGreenLess = 0.48;
    public double greenGreenGreater = 0.5;
    public double greenBlueGreater =0.24;

    //Arm Subsystem
    public Solenoid armPiston = new Solenoid(1);
    public TalonSRX armMotor = new TalonSRX(13);

    //Controllers
    public Joystick xbox = new Joystick(0);
    public Joystick arcade = new Joystick(1);

    //Gyro
    public AHRS gyro = new AHRS(SPI.Port.kMXP);
  
    //On board sensors
    public I2C.Port iPort = I2C.Port.kOnboard;
    public ColorSensorV3 colorsensor = new ColorSensorV3(iPort);

    //Constant variables for limelight camera
    public double limeLightKP = 0.05;
    public double limeLightMin = 0.2;
    

    

}
