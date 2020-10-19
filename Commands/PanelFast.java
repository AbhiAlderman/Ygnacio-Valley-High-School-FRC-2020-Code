/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;

public class PanelFast extends CommandBase {
  private ControlPanelSubsystem m_controlPanelSubsystem;
  /**
   * Creates a new PanelFast.
   */
  public PanelFast(ControlPanelSubsystem controlPanelSubsystem){
    m_controlPanelSubsystem = controlPanelSubsystem;
    addRequirements(m_controlPanelSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_controlPanelSubsystem.spinFast();
    m_controlPanelSubsystem.raiseWheel();
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
