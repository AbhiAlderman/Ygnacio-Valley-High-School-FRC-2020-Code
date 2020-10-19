/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//Note: color wheel and control panel are used interchangebly by the team
public class ControlPanelSubsystem extends SubsystemBase {

  //Create variables
  private static Constants m_constants;
  
  private final TalonFX m_panelMotor;
  private Solenoid m_panelPiston;

  private double blueBlueGreater;
  private double blueRedLess;
  private double yellowGreenGreater;
  private double yellowBlueLess;
  private double redRedGreater = 0.35;
  private double redGreenLess;
  private double greenGreenGreater;
  private double greenBlueGreater;

  

    
  private final ColorSensorV3 colorsensor;
   
  

  /**
   * Creates a new ControlPanelSubsystem.
   */
  public ControlPanelSubsystem(Constants constants) {
  //Assign variables
  m_constants = constants; 
  m_panelMotor = m_constants.panelMotor;
  m_panelPiston = m_constants.panelPiston;
  m_panelMotor.setNeutralMode(NeutralMode.Brake);
  colorsensor = m_constants.colorsensor;

  blueBlueGreater = m_constants.blueBlueGreater;
  blueRedLess = m_constants.blueRedLess;
  yellowGreenGreater = m_constants.yellowGreenGreater;
  yellowBlueLess = m_constants.yellowBlueLess;
  redRedGreater = m_constants.redRedGreater;
  redGreenLess = m_constants.redGreenLess;
  greenGreenGreater = m_constants.greenGreenGreater;
  greenBlueGreater = m_constants.greenBlueGreater;

  

  }

  //Method to spin the color wheel until red
  public void spinToRed(){
    colorsensor.getColor();
    if(colorsensor.getColor().green < redGreenLess && colorsensor.getColor().red > redRedGreater){
      m_panelMotor.set(ControlMode.PercentOutput, 0);
    } else if(colorsensor.getColor().green > greenGreenGreater && colorsensor.getColor().blue > greenBlueGreater){
      m_panelMotor.set(ControlMode.PercentOutput, -0.05);
    } else {
      m_panelMotor.set(ControlMode.PercentOutput, -0.08);
    }
  }

  //Method to spin color wheel until blue
  public void spinToBlue(){
    colorsensor.getColor();
    if(colorsensor.getColor().blue > blueBlueGreater && colorsensor.getColor().red < blueRedLess){
      m_panelMotor.set(ControlMode.PercentOutput, 0);
    } else if(colorsensor.getColor().green > yellowGreenGreater && colorsensor.getColor().blue> yellowBlueLess){
      m_panelMotor.set(ControlMode.PercentOutput, -0.05);
    } else {
      m_panelMotor.set(ControlMode.PercentOutput, -0.08);
    }
  }

  //Method to spin color wheel until green
  public void spinToGreen(){
    colorsensor.getColor();
    if(colorsensor.getColor().green> greenGreenGreater && colorsensor.getColor().blue > greenBlueGreater){
      m_panelMotor.set(ControlMode.PercentOutput, 0);
    } else if(colorsensor.getColor().blue > blueBlueGreater && colorsensor.getColor().red < blueRedLess){
      m_panelMotor.set(ControlMode.PercentOutput, -0.05);
    } else {
      m_panelMotor.set(ControlMode.PercentOutput, -0.08);
    }
  }

  //Method to spin color wheel until yellow
  public void spinToYellow(){
    colorsensor.getColor();
    if(colorsensor.getColor().green > yellowGreenGreater && colorsensor.getColor().blue > yellowBlueLess){
      m_panelMotor.set(ControlMode.PercentOutput, 0);
    } else if(colorsensor.getColor().red > redRedGreater && colorsensor.getColor().green < redGreenLess){
      m_panelMotor.set(ControlMode.PercentOutput, -0.05);
    } else {
      m_panelMotor.set(ControlMode.PercentOutput, -0.08);
    }
  }

  //Method to spin the color wheel fast using a button
  public void spinFast(){
    m_panelMotor.set(ControlMode.PercentOutput, 0.2);
  }

  //Method to spin the color wheel slow using a button
  public void spinSlow(){
    m_panelMotor.set(ControlMode.PercentOutput, 0.08);
  }

  //Method to raise the motor to the height of the color wheel
  public void raiseWheel(){
    m_panelPiston.set(true);
  }
  
  //Method to lower the motor back into the robot
  public void lowerWheel(){
    m_panelPiston.set(false);
  }
  //Method to spin the control panel using a joystick
  public void spinPanel(double speed){
  m_panelMotor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
