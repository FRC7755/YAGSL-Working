// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
// import edu.wpi.first.wpilibj.DigitalInput;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
//import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

  PowerDistribution PDH1 = new PowerDistribution(1, ModuleType.kRev);
  private VictorSPX ClimberLeftSparkMax;
  private VictorSPX ClimberRightSparkMax;
//  private CANSparkMax ClimberLeftSparkMax;
//  private CANSparkMax ClimberRightSparkMax;
  // DigitalInput toplimitSwitch = new DigitalInput(0);
  // DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new Climber. */
  public ClimberSubsystem() {

    ClimberLeftSparkMax = new VictorSPX(Constants.kMotorPortClimberLeft);
    ClimberRightSparkMax = new VictorSPX(Constants.kMotorPortClimberRight);
//    ClimberLeftSparkMax = new CANSparkMax(Constants.kMotorPortClimberLeft, MotorType.kBrushed);
//    ClimberRightSparkMax = new CANSparkMax(Constants.kMotorPortClimberRight, MotorType.kBrushed);
    ClimberLeftSparkMax.setInverted(true);
    ClimberRightSparkMax.setInverted(false);
    ClimberLeftSparkMax.setNeutralMode(NeutralMode.Brake);
    ClimberRightSparkMax.setNeutralMode(NeutralMode.Brake);
  }

  public void ClimberRaise() {
    ClimberLeftSparkMax.set(VictorSPXControlMode.PercentOutput, Constants.kClimberSpeed);
    ClimberRightSparkMax.set(VictorSPXControlMode.PercentOutput, Constants.kClimberSpeed);
//    ClimberLeftSparkMax.set(Constants.kClimberSpeed);
//    ClimberRightSparkMax.set(Constants.kClimberSpeed);
  }

  public void ClimberLower() {
    ClimberLeftSparkMax.set(VictorSPXControlMode.PercentOutput, -Constants.kClimberSpeed);
    ClimberRightSparkMax.set(VictorSPXControlMode.PercentOutput, -Constants.kClimberSpeed);
//    ClimberLeftSparkMax.set(-Constants.kClimberSpeed);
//    ClimberRightSparkMax.set(-Constants.kClimberSpeed);
  }

  public void ClimberLeftRaise() {
    ClimberLeftSparkMax.set(VictorSPXControlMode.PercentOutput, Constants.kClimberSpeed);
//    ClimberLeftSparkMax.set(Constants.kClimberSpeed);
//    ClimberRightSparkMax.set(Constants.kClimberSpeed);
  }

  public void ClimberLeftLower() {
    ClimberLeftSparkMax.set(VictorSPXControlMode.PercentOutput, -Constants.kClimberSpeed);
//    ClimberLeftSparkMax.set(-Constants.kClimberSpeed);
//    ClimberRightSparkMax.set(-Constants.kClimberSpeed);
  }

  public void ClimberRightLower() {
    ClimberRightSparkMax.set(VictorSPXControlMode.PercentOutput, -Constants.kClimberSpeed);
//    ClimberLeftSparkMax.set(-Constants.kClimberSpeed);
//    ClimberRightSparkMax.set(-Constants.kClimberSpeed);
  }

  public void ClimberRightRaise() {
    ClimberRightSparkMax.set(VictorSPXControlMode.PercentOutput, Constants.kClimberSpeed);
//    ClimberLeftSparkMax.set(Constants.kClimberSpeed);
//    ClimberRightSparkMax.set(Constants.kClimberSpeed);
  }
  
  public void ClimberStop() {
    ClimberLeftSparkMax.set(VictorSPXControlMode.PercentOutput, 0);
    ClimberRightSparkMax.set(VictorSPXControlMode.PercentOutput, 0);
//    ClimberLeftSparkMax.set(0);
//    ClimberRightSparkMax.set(0);
  }

  public void ClimberLeftStop() {
    ClimberLeftSparkMax.set(VictorSPXControlMode.PercentOutput, 0);
//    ClimberLeftSparkMax.set(0);
//    ClimberRightSparkMax.set(0);
  }

  public void ClimberRightStop() {
    ClimberRightSparkMax.set(VictorSPXControlMode.PercentOutput, 0);
//    ClimberLeftSparkMax.set(0);
//    ClimberRightSparkMax.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
