/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

  //Create variables
  private final Constants m_constants;
  private final TalonSRX armMotor;
  private final Solenoid armPiston;
  /**
   * Creates a new ArmSubsystem.
   */
  public ArmSubsystem(Constants constants) {

    //Assign variables
    m_constants = constants;
    armMotor = m_constants.armMotor;
    armPiston = m_constants.armPiston;
  }

  //Method for moving arm piston
  public void setPiston(boolean x){
    armPiston.set(x);
  }

  //Method for spinning arm motor
  public void spinMotor(double percentOutput){
    armMotor.set(ControlMode.PercentOutput, percentOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
