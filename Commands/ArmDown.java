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
import frc.robot.subsystems.ArmSubsystem;

public class ArmDown extends CommandBase {
  private final ArmSubsystem armsub;
  private final Constants m_constants;
  private final Joystick controller;
  /**
   * Creates a new ArmDown.
   */
  public ArmDown(ArmSubsystem armSubsystem, Constants constants) {
    armsub = armSubsystem;
    m_constants = constants;
    controller = m_constants.arcade;
    addRequirements(armsub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    armsub.spinMotor(controller.getRawAxis(0) * 0.35);
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
