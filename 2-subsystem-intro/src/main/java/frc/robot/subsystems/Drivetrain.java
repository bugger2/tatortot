package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
    // CANSparkMax is a type of motor controller
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;

    // The DifferentialDrive class does math for us to translate joystick inputs
    // -> motor outputs
    private DifferentialDrive diffDrive;

    public Drivetrain() {
        leftMotor = new CANSparkMax(33, MotorType.kBrushless);
        rightMotor = new CANSparkMax(21, MotorType.kBrushless);

        diffDrive = new DifferentialDrive(leftMotor, rightMotor);
    }

    public void drive(double forward, double rotational) {
        diffDrive.arcadeDrive(forward, rotational);
    }
}
