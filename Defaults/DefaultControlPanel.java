/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;

//Default command for the control panel manipulator subsystem.
public class DefaultControlPanel extends CommandBase {
  private final ControlPanelSubsystem controlpanelsubsystem;

  /**
   * Creates a new DefaultControlPanel.
   */
  public DefaultControlPanel(ControlPanelSubsystem controlPanelSubsystem){
    controlpanelsubsystem = controlPanelSubsystem;
    addRequirements(controlpanelsubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controlpanelsubsystem.spinPanel(0);
    controlpanelsubsystem.lowerWheel();
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
