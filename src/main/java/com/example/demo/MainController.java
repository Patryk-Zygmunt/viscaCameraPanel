package com.example.demo;

import com.example.demo.models.OtherCommands;
import com.example.demo.models.PositionDirection;
import com.example.demo.models.Zoom;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

import static com.example.demo.Main.byteArrayToString;

//@RestController
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    Main main;
    String commName = "COM3";
    SerialPort serialPort = new SerialPort(commName);

//    @GetMapping("/command")
//    public String command(@RequestParam String cmd) {
//        return main.command(cmd);

    public MainController() {
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (RuntimeException e1) {
            e1.printStackTrace();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private  String readResponse() {
        try {
            byte[] response = ViscaResponseReader.readResponse(serialPort);
            System.out.println("> " + byteArrayToString(response));
            return byteArrayToString(response);
        } catch (ViscaResponseReader.TimeoutException var11) {
            System.out.println("TIMEOUT");
            return "TIMEOUT";
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        return "error";
    }


//    }

    @RequestMapping(method = RequestMethod.GET)
    private String loadMainPage(Model model) {
//        Integer maxSpeed = viscaCommandSender.sendGetPanTiltMaxSpeed();
        Integer maxSpeed = 10;
        model.addAttribute("maxSpeed", maxSpeed);
        return "mainPage";
    }


    @RestController
    @RequestMapping("/api/position")
    public class PositionController {

        @RequestMapping(method = RequestMethod.GET)
        private ResponseEntity changeZoom() {
            return ResponseEntity.ok("hello from /api/position");
        }

        @RequestMapping(method = RequestMethod.POST)
        private ResponseEntity changePosition(@RequestParam String direction, @RequestParam byte speed) {

            PositionDirection positionDirection = PositionDirection.valueOf(direction);
            byte[] response = null;
            switch (positionDirection) {
                case UP:
                    response = main.sendPanTiltUp();
                    break;
                case DOWN:
                    response = main.sendPanTiltDown();
                    break;
                case LEFT:
                    response = main.sendPanTiltLeft();
                    break;
                case RIGHT:
                    response = main.sendPanTiltRight();
                    break;
            }
            try {
                serialPort.writeBytes(response);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(readResponse());
        }
    }


    @RestController
    @RequestMapping("/api/zoom")
    public class ZoomController {

        @RequestMapping(method = RequestMethod.GET)
        private ResponseEntity changeZoom() {
            return ResponseEntity.ok("hello from /api/zoom");
        }

        @RequestMapping(method = RequestMethod.POST)
        private ResponseEntity changeZoom(@RequestParam String zoom, @RequestParam byte speed) {

            Zoom zoom1 = Zoom.valueOf(zoom);
            byte[] response = null;
            switch (zoom1) {
                case WIDE:
                    response = main.sendZoomWideStd();
                    break;
                case TELE:
                    response = main.sendZoomTeleStd();
                    break;
            }
            try {
                serialPort.writeBytes(response);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(readResponse());
        }
    }


    @RestController
    @RequestMapping("/api/other")
    public class OtherController {

        @RequestMapping(method = RequestMethod.GET)
        private ResponseEntity test() {
            String response = "hello from /api/other";
            return ResponseEntity.ok(response);
        }


        @RequestMapping(method = RequestMethod.POST)
        private ResponseEntity changePosition(@RequestParam String command) {

            byte[] response = null;
            switch (OtherCommands.valueOf(command)) {
                case HOME:
                    response = main.sendPanTiltHome();
                    break;
            }
            try {
                serialPort.writeBytes(response);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }

            return ResponseEntity.ok(readResponse());
        }
    }
}
