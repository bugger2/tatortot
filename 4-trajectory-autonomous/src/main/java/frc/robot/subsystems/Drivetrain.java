package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
    // CANSparkMax is a type of motor controller
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;

    // The DifferentialDrive class does math for us to translate joystick inputs
    // -> motor outputs
    private DifferentialDrive diffDrive;

    // gyro keeps track of your robot angle
    private Pigeon2 gyro;

    // odometry keeps track of what your wheel encoders are reporting, and using
    // that to infer where the robot must have traveled
    private DifferentialDriveOdometry odometry;

    // the kinematics take care of calculating what speed the wheels should go
    // at if we want the robot to go at a specific speed, and requires the track
    // width (how wide the tank drive is from wheel to wheel) to calculate that
    public static final DifferentialDriveKinematics kinematics =
        new DifferentialDriveKinematics(.22);

    private static final double MOTOR_ROTATIONS_PER_METER = 1.0;
    public static final double KS_VOLTS = 0;
    public static final double KV_VOLT_SECONDS_PER_METER = 0;
    public static final double KA_VOLT_SECONDS_SQUARED_PER_METER = 0;
    public static final double MAX_ACCELERATION = 1;
    public static final double MAX_VELOCITY = 2;

    public Drivetrain() {
        leftMotor = new CANSparkMax(33, MotorType.kBrushless);
        rightMotor = new CANSparkMax(21, MotorType.kBrushless);

        // convert the encoder units to meters instead of motor rotations
        leftMotor.getEncoder().setPositionConversionFactor(1/MOTOR_ROTATIONS_PER_METER);
        rightMotor.getEncoder().setPositionConversionFactor(1/MOTOR_ROTATIONS_PER_METER);

        diffDrive = new DifferentialDrive(leftMotor, rightMotor);

        gyro = new Pigeon2(6);

        odometry = new DifferentialDriveOdometry(
            Rotation2d.fromDegrees(0), // starting angle of the robot
            0, // how far the left side has travelled
            0 // how far the right side has travelled
        );
    }

    @Override
    public void periodic() { // everything in this function gets run every 20ms
        // update the odometry so we know how far the robot has moved
        odometry.update(
            gyro.getRotation2d(),
            leftMotor.getEncoder().getPosition(),
            rightMotor.getEncoder().getPosition()
        );
    }

    // take in the forward vector and rotational vector to drive with
    public void joystickDrive(double forward, double rotational) {
        diffDrive.arcadeDrive(forward, rotational);
    }

    // take in left and right side individually to follow a trajectory
    public void voltageDrive(double leftVolts, double rightVolts) {
        leftMotor.setVoltage(leftVolts);
        rightMotor.setVoltage(rightVolts);
        diffDrive.feed(); // prevents motor safety mechanism going off
    }

    // calling this function will tell you where the robot thinks it is
    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }
}
