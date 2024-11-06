// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ChassisSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public ChassisSubsystem() {
    CANSparkMax leftFront = new CANSparkMax(ChassisConstants.kLeftFrontCanId, MotorType.kBrushless);
    CANSparkMax rightFront = new CANSparkMax(ChassisConstants.kRightFrontCanId, MotorType.kBrushless);
    CANSparkMax leftRear = new CANSparkMax(ChassisConstants.kLeftRearCanId, MotorType.kBrushless);
    CANSparkMax rightRear = new CANSparkMax(ChassisConstants.kRightRearCanId, MotorType.kBrushless);

    for(CANSparkMax motor: new CANSparkMax[]{
      leftFront,rightFront,leftRear,rightRear}){
        motor.setIdleMode(IdleMode.kBrake);
        motor.setSmartCurrentLimit(60);
        motor.setClosedLoopRampRate(0);
        motor.enableSoftLimit(SoftLimitDirection.kForward,false);
        motor.enableSoftLimit(SoftLimitDirection.kReverse.false);
    }
leftRear.follow(leftFront);
rightRear.follow(rightFront);
    leftFront.setInverted(false);
    rightFront.setInverted(true);
    leftRear.setInverted(true);
    rightRear.setInverted(true);
    
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
