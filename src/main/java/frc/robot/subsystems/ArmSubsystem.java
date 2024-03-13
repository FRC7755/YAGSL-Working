// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.Solenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  private final Solenoid arm = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

  /**Creates a new Pneumatics subsystem with a PCM and two solenoids */

  /**Runs the toggle() method on both solenoids.*/

  public Command ArmLower() {
    //  ArmSparkMax.set(Constants.kArmSpeed);
    arm.set(true);
    return null;
  }

  public Command ArmRaise() {
    //  ArmSparkMax.set(Constants.kArmSpeed);
    arm.set(false);
    return null;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
