package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class Camera{

  UsbCamera camera;
  String cameraName;
  VideoSink server;

  public Camera() {
    setupCameraStream();  
  }

  public void setupCameraStream() {
    camera = CameraServer.startAutomaticCapture("cameraA", 0);
    cameraName = camera.getName();
    //camera.setPixelFormat(VideoMode.PixelFormat.kGray);

    server = CameraServer.getServer();
    server.setSource(camera);

    Shuffleboard.getTab("Driver")
                .add("Camera", server.getSource())
                .withWidget(BuiltInWidgets.kCameraStream)
                .withSize(5, 5)
                .withPosition(0, 0);
  }
}