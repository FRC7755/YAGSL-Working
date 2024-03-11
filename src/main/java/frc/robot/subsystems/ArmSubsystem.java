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

public class ArmSubsystem extends SubsystemBase {

  private CANSparkMax ArmSparkMax;
  // DigitalInput toplimitSwitch = new DigitalInput(0);
  // DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new Climber. */
  public ArmSubsystem() {

    ArmSparkMax = new CANSparkMax(Constants.kMotorPortArm, MotorType.kBrushed);
    ArmSparkMax.setInverted(false);
  }

  public Command ArmRaise() {
    ArmSparkMax.set(Constants.kArmSpeed);
    return null;
  }

  public Command ArmLower() {
    ArmSparkMax.set(-Constants.kArmSpeed);
    return null;
  }

  public Command ArmStop() {
    ArmSparkMax.set(0);
    return null;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
