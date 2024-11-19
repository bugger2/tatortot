# Basic Autonomous Routine

https://github.com/user-attachments/assets/492e1122-2b06-46ea-bc26-fd0804864368

The code in this project serves as the most basic example on how to do something
in auto. It ain&rsquo;t fancy or intuitive, but it&rsquo;s the simplest logic to create for
somebody who might not be a programming expert quite yet.

## Reading the code

The code to run auto is split into two separate functions. There&rsquo;s the
autonomous initialization code that gets run at the start of a match, and theres
the autonomous periodic code, which gets run once every 20ms throughout the
entirety of the autonomous period. These are located in
[`src/main/java/frc/robot/Robot.java`](https://github.com/sentientspud/tatortot/blob/main/3-basic-autonomous/src/main/java/frc/robot/Robot.java)
and are written as so:

    @Override
    public void autonomousInit() {
        // run code at the start of the auto
    }
    
    @Override
    public void autonomousPeriodic() {
        // run code once every 20ms
    }

## Understanding the code

This example is the most basic possible auto logic to write. There are ways that
are easier to read and/or more intuitive at a high level, but that&rsquo;s because
they offer something called &ldquo;abstractions,&rdquo; which often confuse new programmers.
This auto is the simplest to understand from a logic and implementation
perspective.

In the `autonomousInit()` function, you will see the line `autoTimer.restart()`.
If you scroll up in the file, you will notice there is a variable called
`autoTimer` of type `Timer`, which allows you to keep track of time independent
of the robot code. By calling this line, we are resetting the `autoTimer` to 0
seconds, and starting it.

The `autonomousPeriodic()` function is a bit more complex. Every single tick
(20ms) throughout auto, we&rsquo;re checking if the timer has passed a certain value.
Based on how far the timer says we are (`autoTimer.get()`), the robot will
perform different actions. For example, the first line, `if (autoTimer.get() <
0.75)`, evaluates if we are less than 0.75 seconds past when `autoTimer` was
started, which is at the beginning of auto. If we are, the code in the curly
braces executes. That code is `drivetrain.drive(speed, -0.5)`. This tells the
drivetrain to go forward at the speed specified (which was declared at the top
of the function), while trying to rotate at half speed clockwise. The rest of
the periodic function follows a similar pattern. I would encourage you to try
and figure out what&rsquo;s running as you look at the video of the robot!

