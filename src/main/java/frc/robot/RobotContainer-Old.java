// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import frc.robot.commands.ElbowDownCommand;
// import frc.robot.commands.ElbowUpCommand;
import frc.robot.commands.Elbow1UpCommand;
import frc.robot.commands.Elbow2UpCommand;
// import frc.robot.commands.ElbowMidCommand;
// import frc.robot.commands.ShoulderCommand;
import frc.robot.commands.AutoRun;
import frc.robot.commands.ClawOpenCommand;
// import frc.robot.commands.ShoulderTurnRightCommand;
// import frc.robot.commands.ShoulderTurnLeftCommand;
import frc.robot.commands.ShoulderUpCommand;
import frc.robot.commands.ShoulderDownCommand;
// import frc.robot.commands.ServoRightCommand;
// import frc.robot.commands.ServoLeftCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
// import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.Elbow1UpSubsystem;
import frc.robot.subsystems.Elbow2UpSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ShoulderSubsystem;
// import frc.robot.subsystems.ShoulderTurnSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  // private final ServoSubsystem servoSubsystem = new ServoSubsystem();
  private final Elbow1UpSubsystem elbow1UpSubsystem = new Elbow1UpSubsystem();
  private final Elbow2UpSubsystem elbow2UpSubsystem = new Elbow2UpSubsystem();
  private final ClawSubsystem clawSubsystem = new ClawSubsystem();
  private final ShoulderSubsystem shoulderSubsystem = new ShoulderSubsystem();
  // private final ShoulderTurnSubsystem shoulderTurnSubsystem = new ShoulderTurnSubsystem();

  XboxController m_driverController = new XboxController(Constants.kDriverControllerPort);
  XboxController m_manipulatorController = new XboxController(Constants.kManipulatorControllerPort);

  // JoystickButton B_button = new JoystickButton(m_driverController, 1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings(); {

      // B_button.whileTrue
      // new JoystickButton(m_manipulatorController, Button.kB.value)
      // .whileTrue(new ShoulderTurnRightCommand(shoulderTurnSubsystem));

      // X_button.whileTrue
      // new JoystickButton(m_manipulatorController, Button.kX.value)
      // .whileTrue(new ShoulderTurnLeftCommand(shoulderTurnSubsystem));

      // Y_button.whileTrue
      // new JoystickButton(m_manipulatorController, Button.kY.value)
      // .whileTrue(new ElbowDownCommand(elbowSubsystem));

      // A_button.whileTrue
      new JoystickButton(m_manipulatorController, Button.kRightBumper.value)
      .whileTrue(new Elbow1UpCommand(elbow1UpSubsystem));

      new JoystickButton(m_manipulatorController, Button.kLeftBumper.value)
      .whileTrue(new Elbow2UpCommand(elbow2UpSubsystem));

      // Left-Bumper_button.whileTrue
      new JoystickButton(m_manipulatorController, Button.kY.value)
      .whileTrue(new ClawOpenCommand(clawSubsystem));

      new JoystickButton(m_manipulatorController, Button.kB.value)
      .whileTrue(new ShoulderUpCommand(shoulderSubsystem));

      new JoystickButton(m_manipulatorController, Button.kX.value)
      .whileTrue(new ShoulderDownCommand(shoulderSubsystem));

      //new JoystickButton(m_driverController, Button.kStart.value)
      //.whileTrue(new WinchLiftCommand(climberSubsystem));


    }


    driveTrainSubsystem.setDefaultCommand(
      driveTrainSubsystem.arcadeDriveCommand(
            () -> (+m_driverController.getLeftTriggerAxis()-m_driverController.getRightTriggerAxis())*Constants.kSpeedControl, () -> -m_driverController.getLeftX()*Constants.kTurnSpeed));

    // shoulderSubsystem.setDefaultCommand(
    //   new ShoulderCommand(shoulderSubsystem, () -> new JoystickButton(m_manipulatorController, Button.kRightBumper.value).getAsBoolean())
    // );
    
//    driveTrainSubsystem.setDefaultCommand(
//      new RunCommand(() -> driveTrainSubsystem.mecanumDrive(getControllerLeftX(), getControllerRightY(), getControllerRightX()), driveTrainSubsystem)
//    );

  }

  private double deadZoneMod(double val) {
    if (Math.abs(val) <= Constants.DEADZONE) {
      return 0;
    } else {
      return ((val -0.2) * 1.25) ;
    }
  }

  public double getControllerRightX() {
    if ( m_driverController != null ) {
      return deadZoneMod(m_driverController.getRightX());
    } else {
      return 0;
    }
  }

  public double getControllerLeftX() {
    if (m_driverController != null ) {
      return deadZoneMod(m_driverController.getLeftX());
    } else {
      return 0;
    }
  }

  public double getControllerRightY() {
    if ( m_driverController != null ) {
      return deadZoneMod(m_driverController.getRightY());
    } else {
      return 0;
    }
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

   public Command getAutonomousCommand(){
    return new AutoRun(driveTrainSubsystem);
   }
}
 