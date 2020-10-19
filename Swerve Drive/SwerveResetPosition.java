/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveKinematics;

//Simple class to reset the position of the angle motors.
public class SwerveResetPosition extends CommandBase {
  private final SwerveKinematics m_swervekinematics;
  /**
   * Creates a new SwerveResetPosition.
   */
  
  public SwerveResetPosition(SwerveKinematics swerveKinematics){
    m_swervekinematics = swerveKinematics;
    addRequirements(m_swervekinematics);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_swervekinematics.driveposition(0, 0);
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
