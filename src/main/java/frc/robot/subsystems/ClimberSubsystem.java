// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

  private CANSparkMax ClimberLeftSparkMax;
  private CANSparkMax ClimberRightSparkMax;
  // DigitalInput toplimitSwitch = new DigitalInput(0);
  // DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new Climber. */
  public ClimberSubsystem() {

    ClimberLeftSparkMax = new CANSparkMax(Constants.kMotorPortClimberLeft, MotorType.kBrushed);
    ClimberRightSparkMax = new CANSparkMax(Constants.kMotorPortClimberRight, MotorType.kBrushed);
    ClimberLeftSparkMax.setInverted(true);
    ClimberRightSparkMax.setInverted(false);
  }

  public void ClimberRaise() {
    ClimberLeftSparkMax.set(Constants.kClimberSpeed);
    ClimberRightSparkMax.set(Constants.kClimberSpeed);
  }

  public void ClimberLower() {
    ClimberLeftSparkMax.set(-Constants.kClimberSpeed);
    ClimberRightSparkMax.set(-Constants.kClimberSpeed);
  }

  public void ClimberStop() {
    ClimberLeftSparkMax.set(0);
    ClimberRightSparkMax.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
