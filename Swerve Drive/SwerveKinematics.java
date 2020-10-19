/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.kauailabs.navx.frc.AHRS;

public class SwerveKinematics extends SubsystemBase {

  //Length and Width of the drivetrain
  private final double Length;
  private final double Width;
  
  //Modules for each corner of the drivetrain
  private final SwerveModules backRight;
  private final SwerveModules backLeft;
  private final SwerveModules frontRight;
  private final SwerveModules frontLeft;

  //Offsets for each module to make them all equal to each other, found through testing
  private final int backRightOffset;
  private final int backLeftOffset;
  private final int frontRightOffset;
  private final int frontLeftOffset;

  //Constants file to access the motors
  private final Constants m_constants;

  //Gyro and adjustmentAngle for sensing current position.
  private final AHRS gyro;
  private double mAdjustmentAngle;

 

  
  /**
   * Creates a new SwerveKinematics.
   */
  public SwerveKinematics( Constants constants) {
  
  //Assign variables above to their values
  m_constants = constants;

  backRightOffset = m_constants.backRightAngleOffset;
  backLeftOffset = m_constants.backLeftAngleOffset;
  frontRightOffset = m_constants.frontRightAngleOffset;
  frontLeftOffset = m_constants.frontLeftAngleOffset;

  Length = 28;
  Width = 28;

  gyro = m_constants.gyro;
  
  backRight = new SwerveModules(m_constants.backRightAngle, m_constants.backRightDrive, m_constants.backRightAngleSensorPhase, m_constants.backRightAngleInverted, m_constants.backRightDriveSensorPhase, m_constants.backRightDriveInverted, m_constants.backRightAngleKP, m_constants.backRightAngleKD);
  backLeft = new SwerveModules(m_constants.backLeftAngle, m_constants.backLeftDrive, m_constants.backLeftAngleSensorPhase, m_constants.backLeftAngleInverted, m_constants.backLeftDriveSensorPhase, m_constants.backLeftDriveInverted, m_constants.backLeftAngleKP, m_constants.backLeftAngleKD);
  frontRight = new SwerveModules(m_constants.frontRightAngle, m_constants.frontRightDrive, m_constants.frontRightAngleSensorPhase, m_constants.frontRightAngleInverted, m_constants.frontRightDriveSensorPhase, m_constants.frontRightDriveInverted, m_constants.frontRightAngleKP, m_constants.frontRightAngleKD);
  frontLeft = new SwerveModules(m_constants.frontLeftAngle, m_constants.frontLeftDrive, m_constants.frontLeftAngleSensorPhase, m_constants.frontLeftAngleInverted, m_constants.frontLeftDriveSensorPhase, m_constants.frontLeftDriveInverted, m_constants.frontLeftAngleKP, m_constants.frontLeftAngleKD);
  }

  //Adjustment angles so the gyro knows where it is
  public double getAdjustmentAngle(){
    return mAdjustmentAngle;
}

public void setAdjustmentAngle(double adjustmentAngle){
    mAdjustmentAngle = adjustmentAngle;
}

public double getGyroAngle(){
    return (gyro.getAngle() - getAdjustmentAngle());
}


  //Method for driving the robot
  public void drive (double strafe, double forward, double rotation, boolean joystickreset){
        
     //Makes the drivetrain field oriented. "Forward" will always drive the robot towards the end of the field
     double angleRad = Math.toRadians(getGyroAngle());
     double temp = forward * Math.cos(angleRad) + strafe * Math.sin(angleRad);
     forward = -forward * Math.sin(angleRad) + strafe * Math.cos(angleRad);
     strafe = temp;
     
     
     //Hypotnuse of drivetrain
     double r = Math.sqrt((Length*Length) + (Width*Width));
     strafe *= -1;

     /*Math for controlling 8 motors intuitively using the 2 joysticks on an xbox controller
     Look up a swerve drivetrain programming tutorial for a more in depth understanding */
     double a = forward - rotation * (Length/r);
     double b = forward + rotation * (Length/r);
     double c = strafe - rotation * (Width/r);
     double d = strafe + rotation * (Width/r);

     double backRightSpeed = Math.sqrt((a*a) + (d*d));
     double backLeftSpeed = Math.sqrt((a*a) + (c*c));
     double frontRightSpeed = Math.sqrt((b*b) + (d*d));
     double frontLeftSpeed = Math.sqrt((b*b) + (c*c));

     double backRightAngle = Math.toDegrees(Math.atan2(a,d));
     double backLeftAngle = Math.toDegrees(Math.atan2(a,c));
     double frontRightAngle = Math.toDegrees(Math.atan2(b,d));
     double frontLeftAngle = Math.toDegrees(Math.atan2(b,c));

     
     //Joystick reset will reset the wheels to their original position.
     if(joystickreset == false){

     backRight.drive(backRightSpeed, backRightAngle, backRightOffset);
     backLeft.drive(backLeftSpeed, backLeftAngle, backLeftOffset);
     frontRight.drive(frontRightSpeed, frontRightAngle, frontRightOffset);
     frontLeft.drive(frontLeftSpeed, frontLeftAngle, frontLeftOffset);
     } else if(joystickreset == true){
     backLeft.manualDrive(0, 0);
     backRight.manualDrive(0, 0);
     frontLeft.manualDrive(0, 0);
     frontRight.manualDrive(0, 0);
     }
 }

//Commands the drivetrain to drive somewhere using numbers instead of the xbox joysticks
 public void driveposition(double speed, double angle){
  backRight.drive(speed, angle, 356);
  backLeft.drive(speed, angle, -755);
  frontRight.drive(speed, angle, -274);
  frontLeft.drive(speed, angle, -266);
     
}

//Reset the gyro
public void resetGyro(){
  gyro.reset();
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
