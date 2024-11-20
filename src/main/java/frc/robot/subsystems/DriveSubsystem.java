// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;



import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  DifferentialDrive m_drivetrain;
  public DriveSubsystem() {
    /** Creates a new DriveSubsystem. */
    CANSparkMax leftFront = new CANSparkMax(DriveConstants.kLeftFrontCANID, MotorType.kBrushless);
    CANSparkMax leftBack = new CANSparkMax(DriveConstants.kLeftBackCANID, MotorType.kBrushless);
    CANSparkMax rightFront = new CANSparkMax(DriveConstants.kRightFrontCANID, MotorType.kBrushless);
    CANSparkMax rightBack = new CANSparkMax(DriveConstants.kRightBackCANID, MotorType.kBrushless);
    for (CANSparkMax motor : new CANSparkMax[] { leftFront, leftBack, rightFront, rightBack }) {
      motor.setIdleMode(IdleMode.kBrake);
      motor.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
      motor.setSmartCurrentLimit(DriveConstants.kSmartCurrentLimit);
      motor.enableSoftLimit(CANSparkBase.SoftLimitDirection.kForward, false);
      motor.enableSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, false);
    }
    m_drivetrain = new DifferentialDrive(leftFront, rightFront);

leftBack.follow(leftFront);
rightBack.follow(rightFront);
leftFront.setInverted(true);
rightFront.setInverted(false);
leftBack.setInverted(false);
rightBack.setInverted(false);
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
   * An example method querying a boolean state of the subsystem (for example, a
   * digital sensor).
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
/*Method to control the drivetrain using arcade drive. Arcade drive takes a speed in the X (forward/back) direction
   * and a rotation about the Z (turning the robot about it's center) and uses these to control the drivetrain motors */
  public void arcadeDrive(double speed, double rotation) {
    double newSpeed = speed;
    if (speed > 0.5)
    {
      newSpeed = 0.5;
    }
    m_drivetrain.arcadeDrive(speed, rotation);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
