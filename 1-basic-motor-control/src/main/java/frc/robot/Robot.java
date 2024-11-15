// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// the package keyword just declares where in the file path this class
// exists. It is what tells the java compiler the full qualified name of this
// class (see note on import statements for clarification of what that means)
package frc.robot;

// import statements declare these class names to be available to us in this
// file, so we don't have to type out the full qualified name of a class
// e.g. we can type `CANSparkMax` instead of `com.revrobotics.CANSparkMax`
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

// A class header defines the blueprint for which a Robot can be created with It
// is public, so it can be created anywhere. It also extends TimedRobot, which
// gives us some simple controls, such as init and periodic functions for
// different modes that will be run at the start of or every 20ms during a mode,
// respectively.
public class Robot extends TimedRobot {
    // Declare the file-local variables. This only declares, and does not
    // instantiate these objects. Sort of like saying that you will have these,
    // so other parts of the system know, but they are not created yet

    // Private means to be only available in this file (other files can't
    // reference these variables)
    //
    // The type of the variable follows that, in this case being an
    // XboxController, which allows us to use functions specific to an xbox
    //
    // controller, such as getting the joystick values
    // This line is concluded by the name of the variable
    private XboxController controller;

    // CANSparkMax is a type of motor controller
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;

    // constructor for the Robot class
    public Robot() {
        // give value to the variables so they can be used
        controller = new XboxController(0);
        leftMotor = new CANSparkMax(33, MotorType.kBrushless);
        rightMotor = new CANSparkMax(21, MotorType.kBrushless);
    }

    // All these empty functions are used in more advanced code
    // The only important one here is teleopPeriodic
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

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void autonomousExit() {
    }

    @Override
    public void teleopInit() {
    }

    /**
     * This is a javadoc comment. This specific format of comment (/** at the
     * start, * on lines following, with an ending *_/ (_ added to prevent
     * ending the comment) marks the text within the comment as special. It will
     * show this helpful commentary when hovering over this function in and IDE
     * such as VSCode), and is helpful for providing a summary of what a
     * function does, what its parameters are, and what it returns
     *
     * The code below is called a function.
     * @Override overrides a function defined in the parent class, TimedRobot.
     * public means this function can be called from anywhere in the project.
     * void means the function does not return a value
     * teleopPeriodic is the name of the function
     * The () following teleopPeriodic specify the inputs of the function, which
     * must be passed into the function whenever it is called. Think the x in f(x).
     * This function does not have any parameters
     */
    @Override
    public void teleopPeriodic() {
        // tell both motors to go at the speed specific by the joysticks
        leftMotor.set(-controller.getLeftY());
        rightMotor.set(-controller.getRightY());
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
