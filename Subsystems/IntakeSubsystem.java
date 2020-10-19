/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

  //Create variables
  private final Constants m_constants;
  private final Solenoid intakePiston;
  private final TalonFX intakeMotor;
  /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem(Constants constants) {
    
  //Assign variables
    m_constants = constants;
    intakePiston = m_constants.intakePiston;
    intakeMotor = m_constants.intakeMotor;
  }

  //Method for moving the intake piston
  public void moveIntake(boolean x){
    intakePiston.set(x);
  }

  //Method for spinning the intake motor
  public void spinIntake(double percentOutput){
    intakeMotor.set(ControlMode.PercentOutput, percentOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
