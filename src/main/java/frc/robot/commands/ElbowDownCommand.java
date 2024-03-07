// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElbowSubsystem;

public class ElbowDownCommand extends CommandBase {
  /** Creates a new ClimberDownCommand. */

  private final ElbowSubsystem elbowSubsystem;

  public ElbowDownCommand( ElbowSubsystem subsystem) {
    elbowSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements( elbowSubsystem );
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  //  elbowSubsystem.ElbowDown();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  //  elbowSubsystem.ElbowStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
