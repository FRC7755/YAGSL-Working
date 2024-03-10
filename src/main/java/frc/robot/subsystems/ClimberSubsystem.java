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

  private CANSparkMax ClimberSparkMax;
  // DigitalInput toplimitSwitch = new DigitalInput(0);
  // DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new Climber. */
  public ClimberSubsystem() {

    ClimberSparkMax = new CANSparkMax(Constants.kMotorPortClimber, MotorType.kBrushed);
    ClimberSparkMax.setInverted(false);
  }

  public void ClimberRaise() {
    ClimberSparkMax.set(Constants.kClimberSpeed);
  }

  public void ClimberLower() {
    ClimberSparkMax.set(-Constants.kClimberSpeed);
  }

  public void ClimberStop() {
    ClimberSparkMax.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
