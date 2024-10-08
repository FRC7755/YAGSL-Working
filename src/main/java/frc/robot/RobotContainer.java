// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.XboxController.Button;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Constants.OperatorConstants;
//import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
//import frc.robot.commands.Command;
import frc.robot.commands.RunShooterForward100Command;
import frc.robot.commands.RunShooterForward50Command;
import frc.robot.commands.RunShooterForward75Command;
import frc.robot.commands.RunShooterBackwardCommand;
//import frc.robot.commands.FeedShooterCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.ArmLowerCommand;
import frc.robot.commands.ArmRaiseCommand;
//import frc.robot.commands.ClimberLowerCommand;
//import frc.robot.commands.ClimberRaiseCommand;
//import frc.robot.commands.ClimberLeftLowerCommand;
//import frc.robot.commands.ClimberRightLowerCommand;
//import frc.robot.commands.ClimberLeftRaiseCommand;
//import frc.robot.commands.ClimberRightRaiseCommand;
import frc.robot.commands.FeedShooterCommand;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;

import java.io.File;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  public final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/neo"));
  
  public final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public final ArmSubsystem armSubsystem = new ArmSubsystem();
  public final ClimberSubsystem climberSubsystem = new ClimberSubsystem();

  // CommandJoystick rotationController = new CommandJoystick(1);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  // CommandJoystick driverController = new CommandJoystick(1);

  // CommandJoystick driverController   = new CommandJoystick(3);//(OperatorConstants.DRIVER_CONTROLLER_PORT);
  public final XboxController driverXbox = new XboxController(0);
  // XboxController shooterXbox = new XboxController(1);
  public final GenericHID shooterXbox = new GenericHID(1);

 //  private final SendableChooser<Command> autoChooser;
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();
    SmartDashboard.putNumber("Left_Y",driverXbox.getLeftY());
    SmartDashboard.putNumber("Left_X",driverXbox.getLeftX());
    SmartDashboard.putNumber("Right_X",driverXbox.getRightX());


   NamedCommands.registerCommand("Intake", new IntakeCommand(intakeSubsystem));
   NamedCommands.registerCommand("Feed", new FeedShooterCommand(intakeSubsystem));
  //  NamedCommands.registerCommand("Intake Stop", intakeSubsystem.IntakeStop());
   NamedCommands.registerCommand("Shoot", new RunShooterForward100Command(shooterSubsystem));
   NamedCommands.registerCommand("Shooter Stop", shooterSubsystem.ShooterStop());
   NamedCommands.registerCommand("Arm Lower", new ArmLowerCommand(armSubsystem));
   NamedCommands.registerCommand("Arm Raise", new ArmRaiseCommand(armSubsystem));

// autoChooser = AutoBuilder.buildAutoChooser();
// SmartDashboard.putData("Auto Chooser", autoChooser);

/*    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
                                                                   () -> MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                   () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                OperatorConstants.LEFT_X_DEADBAND),
                                                                   () -> MathUtil.applyDeadband(driverXbox.getRightX(),
                                                                                                OperatorConstants.RIGHT_X_DEADBAND),
                                                                   driverXbox::getYButtonPressed,
                                                                   driverXbox::getAButtonPressed,
                                                                   driverXbox::getXButtonPressed,
                                                                   driverXbox::getBButtonPressed);
*/
    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation
/*    Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getRightX(), OperatorConstants.RIGHT_X_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getRightY(), OperatorConstants.RIGHT_Y_DEADBAND));
*/
    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(-driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(-driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> MathUtil.applyDeadband(-driverXbox.getRightX(), OperatorConstants.RIGHT_X_DEADBAND));

    Command driveFieldOrientedDirectAngleSim = drivebase.simDriveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverXbox.getRawAxis(2));

    drivebase.setDefaultCommand(
        !RobotBase.isSimulation() ? driveFieldOrientedAnglularVelocity : driveFieldOrientedDirectAngleSim);



  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    new JoystickButton(driverXbox, 8).onTrue((new InstantCommand(drivebase::zeroGyro)));
//    new JoystickButton(driverXbox, 3).onTrue(new InstantCommand(drivebase::addFakeVisionReading));
    new JoystickButton(driverXbox,
                       2).whileTrue(
        Commands.deferredProxy(() -> drivebase.driveToPose(
                                   new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
                              ));
    //new JoystickButton(driverXbox, 4).whileTrue(new ClimberRaiseCommand(climberSubsystem));
    //new JoystickButton(driverXbox, 1).whileTrue(new ClimberLowerCommand(climberSubsystem));

/*  XBox Controller stuff
      new JoystickButton(shooterXbox, Button.kY.value).whileTrue(new RunShooterCommand(shooterSubsystem));
//    new JoystickButton(shooterXbox, Button.kY.value).whileTrue(new FeedShooterCommand(intakeSubsystem));
    new JoystickButton(shooterXbox, Button.kX.value).whileTrue(new IntakeCommand(intakeSubsystem));
    new JoystickButton(shooterXbox, Button.kB.value).whileTrue(new OuttakeCommand(intakeSubsystem));
    new JoystickButton(shooterXbox, Button.kRightBumper.value).whileTrue(new ArmLowerCommand(armSubsystem));
    new JoystickButton(shooterXbox, Button.kLeftBumper.value).whileTrue(new ArmRaiseCommand(armSubsystem));
*/


    //new JoystickButton(shooterXbox, 1).whileTrue(new ClimberRaiseCommand(climberSubsystem));
    //new JoystickButton(shooterXbox, 5).whileTrue(new ClimberLeftRaiseCommand(climberSubsystem));
    //new JoystickButton(shooterXbox, 3).whileTrue(new ClimberRightRaiseCommand(climberSubsystem));

    //new JoystickButton(shooterXbox, 2).whileTrue(new ClimberLowerCommand(climberSubsystem));
    //new JoystickButton(shooterXbox, 6).whileTrue(new ClimberLeftLowerCommand(climberSubsystem));
    //new JoystickButton(shooterXbox, 4).whileTrue(new ClimberRightLowerCommand(climberSubsystem));

    new JoystickButton(shooterXbox,11).whileTrue(new RunShooterForward50Command(shooterSubsystem));
    new JoystickButton(shooterXbox,12).whileTrue(new RunShooterBackwardCommand(shooterSubsystem));
    new JoystickButton(shooterXbox, 1).whileTrue(new RunShooterForward100Command(shooterSubsystem));
    new JoystickButton(shooterXbox, 2).whileTrue(new RunShooterForward75Command(shooterSubsystem));

    new JoystickButton(shooterXbox, 8).whileTrue(new IntakeCommand(intakeSubsystem));
    new JoystickButton(shooterXbox, 7).whileTrue(new OuttakeCommand(intakeSubsystem));

    new JoystickButton(shooterXbox, 9).whileTrue(new ArmLowerCommand(armSubsystem));
    new JoystickButton(shooterXbox, 10).whileTrue(new ArmRaiseCommand(armSubsystem));
    //    new JoystickButton(shooterXbox, Button.kY.value).whileTrue(new FeedShooterCommand(intakeSubsystem));

//    new Trigger((),DriverStation.isTeleopEnabled() && DriverStation.getMatchTime() <= 30 && DriverStation.getMatchTime() >= 29).onTrue(controllerRumbleCommand().withTimeout(2));

    new Trigger(
      () ->
      DriverStation.isTeleopEnabled()
       && DriverStation.getMatchTime() > 0
       && DriverStation.getMatchTime() <= 30)
       .onTrue(controllerRumbleCommand()
       .withTimeout(1));

    new Trigger(
      () ->
      DriverStation.isTeleopEnabled()
       && DriverStation.getMatchTime() > 0
       && DriverStation.getMatchTime() <= 20)
       .onTrue(controllerRumbleCommand()
       .withTimeout(2));

    new Trigger(
      () ->
      DriverStation.isTeleopEnabled()
       && DriverStation.getMatchTime() > 0
       && DriverStation.getMatchTime() <= 10)
       .onTrue(controllerRumbleCommand()
       .withTimeout(0.2)
       .andThen(Commands.waitSeconds(0.1))
       .repeatedly()
       .withTimeout(0.9)); // Rumble three times


    if (DriverStation.getMatchTime() <= 30 && DriverStation.getMatchTime() >= 29)
    {
      //controllerRumbleCommand();
      System.out.println("Rumble");
      driverXbox.setRumble(RumbleType.kRightRumble, Constants.kRumbleForce);
    }
    else if (DriverStation.getMatchTime() <= 20 && DriverStation.getMatchTime() >= 18)
    {
      //controllerRumbleCommand();
      driverXbox.setRumble(RumbleType.kBothRumble, Constants.kRumbleForce);
    }
    else if (DriverStation.getMatchTime() <= 10 && DriverStation.getMatchTime() >= 8)
    {
      //controllerRumbleCommand();
      driverXbox.setRumble(RumbleType.kBothRumble, Constants.kRumbleForce);
    }
    else if (DriverStation.getMatchTime() <= 5 && DriverStation.getMatchTime() >= 4.5)
    {
      //controllerRumbleCommand();
      driverXbox.setRumble(RumbleType.kBothRumble, Constants.kRumbleForce);
    }
    else if (DriverStation.getMatchTime() <= 4 && DriverStation.getMatchTime() >= 3.5)
    {
      //controllerRumbleCommand();
      driverXbox.setRumble(RumbleType.kBothRumble, Constants.kRumbleForce);
    }
    else if (DriverStation.getMatchTime() <= 3 && DriverStation.getMatchTime() >= 2.5)
    {
      //controllerRumbleCommand();
      driverXbox.setRumble(RumbleType.kBothRumble, Constants.kRumbleForce);
    }
    else if (DriverStation.getMatchTime() <= 2 && DriverStation.getMatchTime() >= 1.5)
    {
      //controllerRumbleCommand();
      driverXbox.setRumble(RumbleType.kBothRumble, Constants.kRumbleForce);
    }
    else if (DriverStation.getMatchTime() <= 1 && DriverStation.getMatchTime() >= 0.5)
    {
      //controllerRumbleCommand();
      driverXbox.setRumble(RumbleType.kBothRumble, Constants.kRumbleForce);
    }
    else
    {
      driverXbox.setRumble(RumbleType.kBothRumble, 0.0);
    }



  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
   return new PathPlannerAuto("Center Speaker to Center Ring");
//    return autoChooser.getSelected();
   // return null;
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }

private Command controllerRumbleCommand() {
    return Commands.startEnd(
        () -> {
          driverXbox.setRumble(RumbleType.kBothRumble, 1.0);
          //operatorXBox.setRumble(RumbleType.kBothRumble, 1.0);
        },
        () -> {
          driverXbox.setRumble(RumbleType.kBothRumble, 0.0);
          //operatorXbox.setRumble(RumbleType.kBothRumble, 0.0);
        });
  }

}
