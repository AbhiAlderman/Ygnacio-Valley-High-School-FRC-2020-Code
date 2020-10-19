/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveKinematics;

public class DefaultSwerveDrive extends CommandBase {
  private final SwerveKinematics m_swerveDriveKinematics;
  private final Constants m_constants;
  private final Joystick xbox;
  
  
  /**
   * Creates a new DefaultSwerveDrive.
   */

   /*Default settings for the drive train. By default, the drivetrain is set to move based on the positon of the
    xbox joysticks. */
  public DefaultSwerveDrive(SwerveKinematics swerveDriveKinematics, Constants constants) {
    m_swerveDriveKinematics = swerveDriveKinematics;
    m_constants = constants;
    xbox = m_constants.xbox;
    addRequirements(m_swerveDriveKinematics);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(xbox.getRawAxis(1)) + Math.abs(xbox.getRawAxis(0)) + Math.abs(xbox.getRawAxis(4)) < 0.2){
      m_swerveDriveKinematics.drive(xbox.getRawAxis(0), -xbox.getRawAxis(1), xbox.getRawAxis(4), true);
    } else {
      m_swerveDriveKinematics.drive(xbox.getRawAxis(0), -xbox.getRawAxis(1), xbox.getRawAxis(4), false);
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
