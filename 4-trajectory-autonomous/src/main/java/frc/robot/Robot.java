// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drivetrain;

public class Robot extends TimedRobot {
    private XboxController controller;

    private Drivetrain drivetrain;

    private Timer autoTimer;

    public Robot() {
        controller = new XboxController(0);
        drivetrain = new Drivetrain();
        autoTimer = new Timer();
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void disabledExit() {
    }

    /* AUTO CODE HERE */

    // code run when autonomous mode begins. any prep work should be done here
    @Override
    public void autonomousInit() {
        // this voltage constraint ensures the robot doesn't try accelerate
        // faster than it can
        DifferentialDriveVoltageConstraint autoVoltageConstraint =
            new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(
                    Drivetrain.KS_VOLTS,
                    Drivetrain.KV_VOLT_SECONDS_PER_METER,
                    Drivetrain.KA_VOLT_SECONDS_SQUARED_PER_METER
                ),
                Drivetrain.kinematics,
                10 // max voltage. leave room below 12 volts to account for
                   // voltage sag on the battery
            );
    }

    // code run during autonomous period, once every 20ms. logic that runs the
    // auto should be in this function. This logic drives forward, drives
    // forward while turning, drives forward, drives forward while turning the
    // other way, drive forward, then stops
    @Override
    public void autonomousPeriodic() {
        if (autoTimer.get() < 2) {
            drivetrain.drive(0.3, 0);
        } else if (autoTimer.get() < 4) {
            drivetrain.drive(0.3, 0.3);
        } else if (autoTimer.get() < 6) {
            drivetrain.drive(0.3, 0);
        } else if (autoTimer.get() < 8) {
            drivetrain.drive(0.3, -0.3);
        } else if (autoTimer.get() < 10) {
            drivetrain.drive(0.3, 0.3);
        } else {
            drivetrain.drive(0, 0);
        }
    }

    @Override
    public void autonomousExit() {
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        drivetrain.drive(-controller.getLeftY(), controller.getRightX());
    }

    @Override
    public void teleopExit() {
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void testExit() {
    }
}
