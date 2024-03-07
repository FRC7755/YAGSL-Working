// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax intakeSparkMax;
  DigitalInput GamePieceDetector = new DigitalInput(0);
  // DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new Climber. */
  public IntakeSubsystem() {

    intakeSparkMax = new CANSparkMax(Constants.kMotorPortIntake, MotorType.kBrushed);
    intakeSparkMax.setInverted(false);
  }

  public void IntakeGamePiece() {
    if(GamePieceDetector.get() = true){
      intakeSparkMax.set(Constants.kIntakeSpeed);
    }
  }

  public void OuttakeGamePiece() {
    intakeSparkMax.set(Constants.kIntakeSpeed * -1);
  }


  public void IntakeStop() {
    intakeSparkMax.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
