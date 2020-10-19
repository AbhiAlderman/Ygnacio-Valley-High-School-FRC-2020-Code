/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StorageSubsystem extends SubsystemBase {
  //Create Variables
  private static Constants m_constants;
  private static TalonFX storageMotor;
  /**
   * Creates a new StorageSubsystem.
   */
  public StorageSubsystem(Constants constants) {
  //Assign Variables
  m_constants = constants;
  storageMotor = m_constants.storageMotor;
  }

  //Method for spinning the storage wheel based on power
  public void spinPercentOutput(double percentOutput){
    storageMotor.set(ControlMode.PercentOutput, percentOutput);
    
  }

  //Method for spinning the storage wheel to a certain position (unused)
  public void spinPosition(double position){
    storageMotor.set(ControlMode.Position, position);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
