# Competition-2020
The code used on 7137's robot in 2020.
This project was created by Abhi Alderman, with help from his mentor Taylor.


The files not within a folder are the most important.

AutoAiming.java - The code for using a limelight camera to auto aim our shooter towards the goal.

Constants.java - Constant variables used throughout the program, located in one place for convenient editing.

Robot.java - Runs through the states of the game, executing code throughout.

RobotContainer.java - Declares the Commands and Subsystems, creates the controllers, and assigns buttons to execute specific commands.

Main.java - Not created or edited by me. Given and used by FRC when creating a robotics project.


The folders are organized as follows.


SUBSYSTEMS - 
All files within the subsystems are classes that create the different subsystems available on the robot.
Subsystems create multiple methods that are then used by Commands.


COMMANDS - 
Commands simply execute methods that were created in the subystem when an assigned button is pressed.


DEFAULTS - 
Defaults are what command is run for each subsystem when no button is being pressed. In most instances, default commands control the subsytem and set the motors to not move and the pistons to their desired default value.


SWERVE DRIVE - 
Swerve drive is a term for a drive train with 4 wheels that can face any direction. (Think of the front 2 wheels of a shopping cart.) 
Swerve drive is a complex drivetrain and a challenge for many teams. This was our first year attempting a swerve drive train, and ours worked fairly well. There was room for improvements, but our time was cut short by quarantine.
See this links for the basics of swerve drive.
Youtube demo by FRC team 9048 - https://www.youtube.com/watch?v=idizm--Qwlc&ab_channel=Philobots
Longer, more in depth video explaining swerve drive by FIRST - https://www.youtube.com/watch?v=9adMaBLfYZ4&ab_channel=OfficialFIRST
A beginner tutorial for programming swerve drive that helped me get started - https://jacobmisirian.gitbooks.io/frc-swerve-drive-programming/content/


This was my third ever programming project, and my second ever project using Java.

This project was created during my Senior year in the 6-8 weeks provided.

I self taught myself programming, specifically Java, throughout high school for my team to compete in these competitions.

Unfortunately, due to COVID-19, we were not able to complete this project. Aspects that I coded into this project, such as the robot Arm and the Control Panel Manipulator, are not fully functioning at this time. The school year being cut short lead to little footage of the robot.

Here is a short demo of the robots shooting mechanism - https://www.instagram.com/p/B85NxOqBzl6/
(That's me on the right!)

Another short demo, to see the scale and size of our robot. - https://www.facebook.com/project212yvhsrobotics/videos/847074879121941/
(That's me under the hoop!)


Special thanks to Taylor, my mentor, for teaching me so much this year! I would've been lost without you! 


