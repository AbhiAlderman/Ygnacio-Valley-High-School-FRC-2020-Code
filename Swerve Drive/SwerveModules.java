/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//This class is used to create each module of the swerve drivetrain
public class SwerveModules extends SubsystemBase{
  
  //The motor for controlling the angle of the wheel (Redline motor)
  private final TalonSRX angleMotor;

  //The motor for controlling the speed of the wheel (CIM motor)
  private final TalonSRX speedMotor;

  //Encoder ticks per 1 revolution
  private final int wrap;
  private final double current;
  
  
 /**
   * Creates a new SwerveModules.
   */
  public SwerveModules(TalonSRX m_angleMotor, TalonSRX m_driveMotor, boolean angleSensorphase, boolean angleInverted, boolean driveSensorphase, boolean driveInverted, double kP, double kD) {
    //Assign the motors
    angleMotor = m_angleMotor;
    speedMotor = m_driveMotor;

    /*Configure the speed motor. We do not need to be as precise because the "driving" of the drivetrain is
    less complicated and sensitive than the angle of the drivetrain*/
    speedMotor.setSensorPhase(driveSensorphase);
    speedMotor.setInverted(driveInverted);
    speedMotor.configFactoryDefault();

    //Configure the angle motor's speed controller. We need to be precise due to the nature of setting the angles of a swerve drive.
    angleMotor.setSensorPhase(angleSensorphase);
    angleMotor.configFactoryDefault();
    angleMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    angleMotor.configFeedbackNotContinuous(true, 30);
    angleMotor.setInverted(angleInverted);
    angleMotor.configNominalOutputForward(0,30);
    angleMotor.configNominalOutputReverse(0,30);
    angleMotor.configPeakOutputForward(1,30);
    angleMotor.configPeakOutputReverse(-1, 30);
    angleMotor.configAllowableClosedloopError(0, 0, 30);

    //PIDF controller for precise control of the angle motor. Search up PID Control for in depth explanation.
    angleMotor.config_kF(0,0,30);
    angleMotor.config_kP(0, kP, 30);
    angleMotor.config_kI(0,0,30);
    angleMotor.config_kD(0,kD,30);
    angleMotor.setSelectedSensorPosition(angleMotor.getSensorCollection().getPulseWidthPosition());
    angleMotor.configClosedloopRamp(0.03);
    
    //Encoder ticks for one full rotation of the wheel.
    wrap = 4096;

    //Set neutral mode to coast, so the motors don't stop suddenly. (Increases motor lifespan)
    angleMotor.setNeutralMode(NeutralMode.Coast);
    speedMotor.setNeutralMode(NeutralMode.Coast);

    //The current position of the angle motor
    current = angleMotor.getSelectedSensorPosition();
    

  }

  /* minChange and minDistance are the maths for the angle motor. Basically turns the ever increasing or
  ever decreasing value of ticks from the encoder into a 360 degree "map". Lets us point our wheel in the
  direction we desire without worrying about how many times the wheel has spun. */
private double minChange(double a, double b, double wrap){
    return Math.IEEEremainder(a - b, wrap);
}
private double minDistance(double a, double b, double wrap){
    return Math.abs(Math.IEEEremainder(a - b, wrap));
}

public void drive(double speed, double angle, int offset){

  //Maths for changing the position we want from degrees into an encoder position.
  //The speed is positive or negative to make a more efficient turn.
  //Example: To drive backwards, we don't rotate the wheel 180 degrees, we just drive in reverse.
  double desired = (int)Math.round(angle * wrap/360.0) + offset;
  double newPosition = (int) minChange(desired, current, wrap/2.0) + current;
  if(minDistance(newPosition, desired, wrap) < .001){
       speed = speed * 0.8;
  } else{
       speed = speed *-0.8;
  }

//Set the speed motor to the speed we want.
speedMotor.set(ControlMode.PercentOutput, speed);

//Set the angle motor to the position we want.
angleMotor.set(ControlMode.Position, newPosition);
}

//Manual drive for debugging
public void manualDrive(double drivespeed, double anglespeed){
  speedMotor.set(ControlMode.PercentOutput, drivespeed);
  angleMotor.set(ControlMode.PercentOutput, anglespeed);
}
@Override
public void periodic(){

}
 
}
