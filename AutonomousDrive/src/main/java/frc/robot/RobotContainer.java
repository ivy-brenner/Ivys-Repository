/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;

/**
 * Add your docs here.
 */
public class RobotContainer {


    private Drivetrain drive = new Drivetrain();

    public Command getAutonomousCommand(){
      TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(2),
        Units.feetToMeters(2));
    config.setKinematics(drive.getKinematics());

    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())),
        config
    );

    RamseteCommand command = new RamseteCommand(
        trajectory,
        drive::getPose,
        new RamseteController(2.0, 0.7),
        drive.getFeedForward(),
        drive.getKinematics(),
        drive::getSpeeds,
        drive.getLeftPIDController(),
        drive.getRightPIDController(),
        drive:: setOutput,
        drive
    );
    return command;
    }



}
