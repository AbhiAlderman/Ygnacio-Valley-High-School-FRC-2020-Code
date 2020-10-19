/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.AutoAiming;
import frc.robot.commands.DefaultArm;
import frc.robot.commands.DefaultControlPanel;
import frc.robot.commands.DefaultSwerveDrive;
import frc.robot.commands.IntakeDefault;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.MoveToBlue;
import frc.robot.commands.MoveToGreen;
import frc.robot.commands.MoveToRed;
import frc.robot.commands.MoveToYellow;
import frc.robot.commands.PanelFast;
import frc.robot.commands.PanelSlow;
import frc.robot.commands.ShootFast;
import frc.robot.commands.ShootSlow;
import frc.robot.commands.ShooterDefault;
import frc.robot.commands.StorageDefault;
import frc.robot.commands.StorageNegative;
import frc.robot.commands.StoragePositive;
import frc.robot.commands.SwerveResetGyro;
import frc.robot.commands.SwerveResetPosition;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import frc.robot.subsystems.SwerveKinematics;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  public final Constants constants = new Constants();

  //Intake mechanism files
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem(constants);
  private final IntakeDefault intakeDefault = new IntakeDefault(intakeSubsystem, constants);
  private final IntakeIn intakeIn = new IntakeIn(intakeSubsystem);
  private final IntakeOut intakeOut = new IntakeOut(intakeSubsystem);
  
  //Shooting mechanism files
  public final ShooterSubsystem shooterSubsystem = new ShooterSubsystem(constants);
  private final ShooterDefault shooterDefault = new ShooterDefault(shooterSubsystem);
  private final ShootFast shootFast = new ShootFast(shooterSubsystem);
  private final ShootSlow shootSlow = new ShootSlow(shooterSubsystem);

  //Storage mechanism files
  public final StorageSubsystem storageSubsystem = new StorageSubsystem(constants);
  private final StorageDefault storageDefault = new StorageDefault(storageSubsystem);
  private final StoragePositive storagePositive = new StoragePositive(storageSubsystem);
  private final StorageNegative storageNegative = new StorageNegative(storageSubsystem);

  //Swerve Drivetrain files
  public final SwerveKinematics swerveKinematics = new SwerveKinematics(constants);
  private final DefaultSwerveDrive defaultSwerveDrive = new DefaultSwerveDrive(swerveKinematics, constants);
  private final SwerveResetPosition swerveResetPosition = new SwerveResetPosition(swerveKinematics);
  private final SwerveResetGyro swerveResetGyro = new SwerveResetGyro(swerveKinematics);

  //Control Panel Spinner files
  private final ControlPanelSubsystem controlpanelsub = new ControlPanelSubsystem(constants);
  private final MoveToYellow moveToYellow = new MoveToYellow(controlpanelsub);
  private final MoveToBlue moveToBlue = new MoveToBlue(controlpanelsub);
  private final MoveToGreen moveToGreen = new MoveToGreen(controlpanelsub);
  private final MoveToRed moveToRed = new MoveToRed(controlpanelsub);
  private final DefaultControlPanel defaultControlPanel = new DefaultControlPanel(controlpanelsub);

  //Arm climber files
  private final ArmSubsystem armSubsystem = new ArmSubsystem(constants);
  private final DefaultArm defaultArm = new DefaultArm(armSubsystem);
  private final ArmUp armUp = new ArmUp(armSubsystem);
  private final ArmDown armDown = new ArmDown(armSubsystem, constants);

  private final AutoAiming autoAiming = new AutoAiming(swerveKinematics, constants);
  
  //Controllers for driver and gunner
  public final Joystick xboxController = constants.xbox;
  public final Joystick arcadeController = constants.arcade;

  private final PanelFast panelFast = new PanelFast(controlpanelsub);
  private final PanelSlow panelSlow = new PanelSlow(controlpanelsub);
  
  //Gunner Buttons
  private JoystickButton greenTop;
  private JoystickButton greenBottom;
  private JoystickButton yellowTop;
  private JoystickButton yellowBottom;
  private JoystickButton blueTop;
  private JoystickButton blueBottom;
  private JoystickButton redTop;
  private JoystickButton redBottom;
  private JoystickButton redPanel;
  private JoystickButton yellowPanel;
  private JoystickButton bluePanel;
  private JoystickButton greenPanel;

  //Driver Buttons
  private JoystickButton xButton;
  private JoystickButton yButton;
  private JoystickButton aButton;




  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    //Assign each button to each input

    //Gunner buttons
    greenTop = new JoystickButton(arcadeController, 1);
    greenBottom = new JoystickButton(arcadeController, 2);
    yellowTop = new JoystickButton(arcadeController, 3);
    yellowBottom = new JoystickButton(arcadeController, 4);
    blueTop = new JoystickButton(arcadeController, 5);
    blueBottom = new JoystickButton(arcadeController, 6);
    redTop = new JoystickButton(arcadeController, 7);
    redBottom = new JoystickButton(arcadeController, 8);
    greenPanel = new JoystickButton(arcadeController, 9);
    bluePanel = new JoystickButton(arcadeController, 10);
    yellowPanel = new JoystickButton(arcadeController, 11);
    redPanel = new JoystickButton(arcadeController, 12);
    
    //Driver buttons
    aButton = new JoystickButton(xboxController, 1);
    xButton = new JoystickButton(xboxController, 3);
    yButton = new JoystickButton(xboxController, 4);

    
    //Set the default commands of the subsystems
    intakeSubsystem.setDefaultCommand(intakeDefault);
    shooterSubsystem.setDefaultCommand(shooterDefault);
    storageSubsystem.setDefaultCommand(storageDefault);
    swerveKinematics.setDefaultCommand(defaultSwerveDrive);
    controlpanelsub.setDefaultCommand(defaultControlPanel);
    armSubsystem.setDefaultCommand(defaultArm);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //Assign which command executes during which button input
    greenTop.whileHeld(shootFast);
    greenBottom.whileHeld(shootSlow);

    blueTop.whileHeld(storagePositive);
    blueBottom.whileHeld(storageNegative);

    yellowBottom.toggleWhenPressed(intakeIn);
    yellowTop.whileHeld(autoAiming);
    

    aButton.whileHeld(swerveResetPosition);

    redTop.toggleWhenPressed(armUp);
    redBottom.whileHeld(armDown);

    yButton.whenPressed(swerveResetGyro);

    greenPanel.whileHeld(panelFast); //moves to the color before the wanted color for the sensor
    bluePanel.whileHeld(moveToBlue);
    yellowPanel.whileHeld(moveToYellow);
    redPanel.whileHeld(panelSlow);


  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

}
