/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import frc.robot.subsystems.SwerveKinematics;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private Constants constants;
  private SwerveKinematics swerveKinematics;
  private ShooterSubsystem shooterSubsystem;
  private StorageSubsystem storageSubsystem;
  private Compressor compressor = new Compressor();
  private TalonFX shooter;
  
  private AHRS gyro;
  private Joystick xbox;

  double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  double steer_k;
  
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    
    m_robotContainer = new RobotContainer();
    constants = m_robotContainer.constants;
    swerveKinematics = m_robotContainer.swerveKinematics;
    compressor.enabled();
    compressor.start();
    gyro = constants.gyro;
    gyro.reset();
    xbox = constants.xbox;
    steer_k = constants.limeLightKP;
    shooter = constants.flyWheel;

    
    shooterSubsystem = m_robotContainer.shooterSubsystem;
    storageSubsystem = m_robotContainer.storageSubsystem;
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Angle", gyro.getYaw());
    SmartDashboard.putNumber("Velocity", shooter.getSelectedSensorVelocity());

    
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    
    gyro.reset();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  //Code for the autonomous period
  swerveKinematics.drive(0, 0, 0, false);

  Timer.delay(0.1);
  swerveKinematics.drive(0, 0.7, 0, false);
  Timer.delay(1.5);
  swerveKinematics.drive(0, 0, 0, false);
  Timer.delay(0.1);
  swerveKinematics.drive(0.6, 0, 0, false);
  Timer.delay(0.5);
  swerveKinematics.drive(0, 0, 0, false);
  Timer.delay(13); 

  /*swerveKinematics.drive(tx * steer_k, 0, 0, false);
  Timer.delay(5);
  shooterSubsystem.ShootPercentOutput(0.6);
  Timer.delay(3);
  storageSubsystem.spinPercentOutput(0.09);
  Timer.delay(4.8);
  */
  
  

    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
    gyro.reset();
    swerveKinematics.driveposition(0, 0);
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
