/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;

public class Drivetrain extends SubsystemBase {

   // create motors and stuff
    WPI_TalonSRX leftMaster = new WPI_TalonSRX(1, WPI_TalonSRXLowLevel.MotorType.kBrushless);
    WPI_TalonSRX rightMaster = new WPI_TalonSRX(3, WPI_TalonSRXLowLevel.MotorType,kBrushless);

    WPI_TalonSRX leftSlave = new WPI_TalonSRX(2, WPI_TalonSRXLowLevel.MotorType.kBrushless);
    WPI_TalonSRX rightSlave = new WPI_TalonSRX(4, WPI_TalonSRXLowLevel.MotorType.kBrushless);

// create gyro
    AHRS gyro = new AHRS(SPI.Port.kMXP);


    // this takes in the 'track width' which is the distance between the wheels
    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(28));
    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(kinematics, getHeading());

    SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.268, 1.89, 0.243);

    Pose2d pose;


    // make the slaves follow the masters
    public Drivetrain(){
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
        
        //invert right motors 

        rightMaster.setInverted(true);
        leftMaster.setInverted(false);
    }

    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(-gyro.getAngle());
    }

    public DifferentialDriveWheelSpeeds getSpeeds(){
        return new DifferentialDriveWheelSpeeds(
            leftMaster.getEncoder().getVelocity() / 7.29 * 2 * Math.PI * Units.inchesToMeters(3.0) / 60,
            rightMaster.getEncoder().getVelocity() / 7.29 * 2 * Math.PI * Units.inchesToMeters(3.0) / 60
        );
    }

    public SimpleMotorFeedforward getFeedforward(){
        return feedforward;
    }

    @Override
    public void periodic(){
        pose  = odometry.update(getHeading(),_getSpeeds())_
    }



}
