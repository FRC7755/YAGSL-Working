// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShoulderTurnSubsystem extends SubsystemBase {

  private CANSparkMax shoulderTurnSparkMax;
  // DigitalInput toplimitSwitch = new DigitalInput(0);
  // DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new Climber. */
  public ShoulderTurnSubsystem() {

    shoulderTurnSparkMax = new CANSparkMax(Constants.kMotorPortShoulderTurn, MotorType.kBrushed);
    shoulderTurnSparkMax.setInverted(false);
  }

  public void ShoulderTurnRight() {
    shoulderTurnSparkMax.set(Constants.kShoulderTurnSpeed);
  }

  public void ShoulderTurnLeft() {
    shoulderTurnSparkMax.set(-Constants.kShoulderTurnSpeed);
  }

  public void ShoulderTurnStop() {
    shoulderTurnSparkMax.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
