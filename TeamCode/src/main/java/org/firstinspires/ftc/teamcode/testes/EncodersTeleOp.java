package org.firstinspires.ftc.teamcode.testes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="EncodersTeleOp", group="BahTech")
public class EncodersTeleOp extends LinearOpMode {

    private final double kp = 0.001;
    private DcMotor FL = null;
    private DcMotor FR = null;
    private DcMotor BL = null;
    private DcMotor BR = null;
    private int teste = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        int aPosition = - 1000;

        int bPosition = 1000;

        DcMotor FL = hardwareMap.dcMotor.get("FL");
        DcMotor BL = hardwareMap.dcMotor.get("BL");
        DcMotor FR = hardwareMap.dcMotor.get("FR");
        DcMotor BR = hardwareMap.dcMotor.get("BR");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.FORWARD);
        FR.setDirection(DcMotor.Direction.REVERSE);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setTargetPosition(bPosition);
        BL.setTargetPosition(bPosition);
        FR.setTargetPosition(bPosition);
        BR.setTargetPosition(bPosition);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.triangle) {
                teste = 10;
                FL.setTargetPosition(aPosition);
                BL.setTargetPosition(aPosition);
                FR.setTargetPosition(aPosition);
                BR.setTargetPosition(aPosition);
                FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                smoother(FL,0.15);
                smoother(BL,0.15);
                smoother(FR,0.15);
                smoother(BR,0.15);

            }

            if (gamepad1.cross) {
                FL.setTargetPosition(bPosition);
                BL.setTargetPosition(bPosition);
                FR.setTargetPosition(bPosition);
                BR.setTargetPosition(bPosition);
                FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                smoother(FL,0.15);
                smoother(BL,0.15);
                smoother(FR,0.15);
                smoother(BR,0.15);
            }

            if (gamepad1.circle) {
                FL.setPower(1);
                BL.setPower(1);
                FR.setPower(1);
                BR.setPower(1);
                sleep(2000);
            }

            telemetry.addData("posição", FL.getCurrentPosition());
            telemetry.addData("teste", teste);
            telemetry.update();
        }

    }

    private void smoother(DcMotor motor, Double targetVelocity){
        int targetPos = motor.getTargetPosition();
        int currPos = motor.getCurrentPosition();

        double power = Math.abs(targetPos - currPos) * kp;
        if (power <= targetVelocity)
            motor.setPower(power);
        else
            motor.setPower(targetVelocity);

        if (power <= 0.003)
            motor.setPower(0);

    }

}