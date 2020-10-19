/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private final Constants m_constants;
  private final TalonFX flyWheel;
  private final TalonFX feederWheel;
  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem(Constants constants) {
  m_constants = constants;
  flyWheel = m_constants.flyWheel;
  feederWheel = m_constants.feederWheel;
   
  flyWheel.configFactoryDefault();
  flyWheel.config_kF(0, 0.06, 30);
  flyWheel.config_kP(0, 0.9, 30);
  flyWheel.config_kD(0, 36, 30);
  //flyWheel.config_kI(0, 0.008, 30);
  flyWheel.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }

  public void ShootPercentOutput(double percentOutput){
   flyWheel.set(ControlMode.PercentOutput, percentOutput);
   feederWheel.set(ControlMode.PercentOutput, percentOutput * 10);
  }

  public void ShooterVelocity(double velocity){
    flyWheel.set(ControlMode.Velocity, velocity);
    feederWheel.setInverted(true);
    feederWheel.set(ControlMode.PercentOutput, 1);
    
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Right Velocity",flyWheel.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Left Velocity", feederWheel.getSelectedSensorVelocity());
    // This method will be called once per scheduler run
  }
}
