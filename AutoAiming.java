/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveKinematics;

public class AutoAiming extends CommandBase {
  double kP;
  SwerveKinematics m_swerveKinematics;
  Constants m_constants;
  double minCommand;
  Joystick xbox;
  
  /**
   * Creates a new AutoAiming.
   */
  public AutoAiming(SwerveKinematics swerveKinematics, Constants constants) {

    //Define variables
    m_swerveKinematics = swerveKinematics;
    m_constants = constants;
    kP = m_constants.limeLightKP;
    minCommand = m_constants.limeLightMin;
    xbox = m_constants.xbox;
    addRequirements(m_swerveKinematics);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /* This is executed to automatically face the drivetrain towards the goal using the limelight camera.
    tx is the difference between the center of the limelight and the center of the goal.
    The smartdashboard is a display for the driver to see the current difference.

    The robot aims by rotating the drivetrain toward the goal based on tx.
    If tx is negative, move in the positive direction.
    If tx is positive, move in the negative direction.
    If tx is 0, you are on target.*/
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    SmartDashboard.putNumber("TX", tx);
    if(tx < 1.0){
      m_swerveKinematics.drive(xbox.getRawAxis(0), -xbox.getRawAxis(1), (kP * tx) - minCommand, false);
      
    } else if(tx > 1.0){
      m_swerveKinematics.drive(xbox.getRawAxis(0), -xbox.getRawAxis(1), (kP * tx) + minCommand, false);
      
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
