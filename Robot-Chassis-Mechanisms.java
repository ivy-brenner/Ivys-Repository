/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

private WPI_TalonSRX leftMaster = new WPI_TalonSRX(3);
private WPI_TalonSRX rightMaster = new WPI_TalonSRX(1);
private WPI_VictorSPX leftSlave = new WPI_VictorSPX(1);
private WPI_VictorSPX rightSlave = new WPI_VictorSPX(2);

private WPI_TalonSRX armMotor = new WPI_TalonSRX(5);
private WPI_VictorSPX armSlave = new WPI_VictorSPX(3);

private WPI_TalonSRX rightRoller = new WPI_TalonSRX(4);
private WPI_TalonSRX leftRoller = new WPI_TalonSRX(2);
private WPI_TalonSRX topRoller = new WPI_TalonSRX(6);

private Compressor compressor = new Compressor();
private DoubleSolenoid piston = new DoubleSolenoid(0,1);
private DoubleSolenoid frontClimb = new DoubleSolenoid(2,3);
private DoubleSolenoid backClimb = new DoubleSolenoid(4,5);
private DigitalInput armLimitSwtich = new DigitalInput(0);

// joysticks
private Joystick driverJoystick = new Joystick(0);
private Joystick operatorJoystick = new Joystick(1);

// unit conversion
private final double kDriveTick2Feet = 1.0 / 4096 * 6 * Math.PI / 12;
private final double kArmTick2Deg = 360.0 / 512 * 26 / 42 * 18 / 60 * 18 / 84;
private final double kDriveSecondsToMaxPower = 0.5;

@Override
public void robotPeriodic() {
  SmartDashboard.putNumber("Arm Encoder Value", armMotor.getSelectedSensorPosition() * kArmTick2Deg);
  SmartDashboard.putNumber("Left Drive Encoder Value", leftMaster.getSelectedSensorPosition() * kDriveTick2Feet);
  SmartDashboard.putNumber("Right Drive Encoder Value", rightMaster.getSelectedSensorPosition() * kDriveTick2Feet);
}


  @Override
  public void robotInit() {
    // inverted settings
    leftMaster.setInverted(true);
    rightMaster.setInverted(true);
    armMotor.setInverted(false);

    // save setups
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
    armSlave.follow(armMotor);

    leftSlave.setInverted(InvertType.FollowMaster);
    rightSlave.setInverted(InvertType.FollowMaster);
    armSlave.setInverted(InvertType.FollowMaster);

    // init encoders
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

    leftMaster.setSensorPhase(false);
    rightMaster.setSensorPhase(true);
    armMotor.setSensorPhase(true);

    // reset encoders to zero
    leftMaster.setSelectedSensorPosition(0, 0, 10);
    rightMaster.setSelectedSensorPosition(0, 0, 10);
    armMotor.setSelectedSensorPosition(0, 0, 10);

    // start compressor
    compressor.start();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
