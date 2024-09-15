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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

  PowerDistribution PDH1 = new PowerDistribution(1, ModuleType.kRev);
  private VictorSPX ClimberLeftMotor;
  private VictorSPX ClimberRightMotor;
//  private CANSparkMax ClimberLeftMotor;
//  private CANSparkMax ClimberRightMotor;
  // DigitalInput toplimitSwitch = new DigitalInput(0);
  // DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new Climber. */
  public ClimberSubsystem() {

    ClimberLeftMotor = new VictorSPX(Constants.kMotorPortClimberLeft);
    ClimberRightMotor = new VictorSPX(Constants.kMotorPortClimberRight);
//    ClimberLeftMotor = new CANSparkMax(Constants.kMotorPortClimberLeft, MotorType.kBrushed);
//    ClimberRightMotor = new CANSparkMax(Constants.kMotorPortClimberRight, MotorType.kBrushed);
    ClimberLeftMotor.setInverted(true);
    ClimberRightMotor.setInverted(false);
    ClimberLeftMotor.setNeutralMode(NeutralMode.Brake);
    ClimberRightMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void ClimberRaise() {
    ClimberLeftMotor.set(VictorSPXControlMode.PercentOutput, Constants.kClimberSpeed);
    ClimberRightMotor.set(VictorSPXControlMode.PercentOutput, Constants.kClimberSpeed);
//    ClimberLeftMotor.set(Constants.kClimberSpeed);
//    ClimberRightMotor.set(Constants.kClimberSpeed);
  }

  public void ClimberLower() {
    ClimberLeftMotor.set(VictorSPXControlMode.PercentOutput, -Constants.kClimberSpeed);
    ClimberRightMotor.set(VictorSPXControlMode.PercentOutput, -Constants.kClimberSpeed);
//    ClimberLeftMotor.set(-Constants.kClimberSpeed);
//    ClimberRightMotor.set(-Constants.kClimberSpeed);
  }

  public void ClimberLeftRaise() {
    ClimberLeftMotor.set(VictorSPXControlMode.PercentOutput, Constants.kClimberSpeed);
//    ClimberLeftMotor.set(Constants.kClimberSpeed);
//    ClimberRightMotor.set(Constants.kClimberSpeed);
  }

  public void ClimberLeftLower() {
    ClimberLeftMotor.set(VictorSPXControlMode.PercentOutput, -Constants.kClimberSpeed);
//    ClimberLeftMotor.set(-Constants.kClimberSpeed);
//    ClimberRightMotor.set(-Constants.kClimberSpeed);
  }

  public void ClimberRightLower() {
    ClimberRightMotor.set(VictorSPXControlMode.PercentOutput, -Constants.kClimberSpeed);
//    ClimberLeftMotor.set(-Constants.kClimberSpeed);
//    ClimberRightMotor.set(-Constants.kClimberSpeed);
  }

  public void ClimberRightRaise() {
    ClimberRightMotor.set(VictorSPXControlMode.PercentOutput, Constants.kClimberSpeed);
//    ClimberLeftMotor.set(Constants.kClimberSpeed);
//    ClimberRightMotor.set(Constants.kClimberSpeed);
  }
  
  public void ClimberStop() {
    ClimberLeftMotor.set(VictorSPXControlMode.PercentOutput, 0);
    ClimberRightMotor.set(VictorSPXControlMode.PercentOutput, 0);
//    ClimberLeftMotor.set(0);
//    ClimberRightMotor.set(0);
  }

  public void ClimberLeftStop() {
    ClimberLeftMotor.set(VictorSPXControlMode.PercentOutput, 0);
//    ClimberLeftMotor.set(0);
//    ClimberRightMotor.set(0);
  }

  public void ClimberRightStop() {
    ClimberRightMotor.set(VictorSPXControlMode.PercentOutput, 0);
//    ClimberLeftMotor.set(0);
//    ClimberRightMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
