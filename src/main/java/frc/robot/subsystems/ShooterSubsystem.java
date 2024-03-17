// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

  private CANSparkMax ShooterUpperSparkMax;
  private CANSparkMax ShooterLowerSparkMax;
  // DigitalInput toplimitSwitch = new DigitalInput(0);
  // DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new Climber. */
  public ShooterSubsystem() {

    ShooterUpperSparkMax = new CANSparkMax(Constants.kMotorPortShooterUpper, MotorType.kBrushed);
    ShooterLowerSparkMax = new CANSparkMax(Constants.kMotorPortShooterLower, MotorType.kBrushed);
    ShooterUpperSparkMax.setInverted(true);
    ShooterLowerSparkMax.setInverted(false);
  }

  public Command ShooterOn() {
    ShooterUpperSparkMax.set(Constants.kShooterSpeed);
    ShooterLowerSparkMax.set(Constants.kShooterSpeed);
    return null;
  }

  public Command ShooterStop() {
    ShooterUpperSparkMax.set(0);
    ShooterLowerSparkMax.set(0);
    return null;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
