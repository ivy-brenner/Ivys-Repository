/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


public class Drivetrain extends SubsystemBase {

   // create motors and stuff
    CANSparkMax leftMaster = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax rightMaster = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType,kBrushless);

    CANSparkMax leftSlave = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax rightSlave = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);

// make the slaves follow the masters
    public Drivetrain(){
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
        
        //invert right motors 

        rightMaster.setInverted(true);
        leftMaster.setInverted(false);
    }



}
