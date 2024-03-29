package com.example.demo;


import com.example.demo.cmd.*;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class Main {

    String commName = "COM3";
    SerialPort serialPort = null; //new SerialPort(commName);

    public static Map<String, List<String>> macro = new HashMap<>();

    public static Map<String, Supplier<byte[]>> commands = new HashMap<String, Supplier<byte[]>>() {{
        put("ClearAll", Main::sendClearAll);
//        put("PanTiltHome", Main::sendPanTiltHome);
//        put("PanTiltUpLeft", Main::sendPanTiltUpLeft);
//        put("PanTiltUp", Main::sendPanTiltUp);
//        put("PanTiltUpRight", Main::sendPanTiltUpRight);
//        put("PanTiltLeft", Main::sendPanTiltLeft);
//        put("PanTiltRight", Main::sendPanTiltRight);
//        put("PanTiltLeft2", Main::sendPanTiltLeft2);
//        put("PanTiltRight2", Main::sendPanTiltRight2);
//        put("PanTiltDownLeft", Main::sendPanTiltDownLeft);
//        put("PanTiltDown", Main::sendPanTiltDown);
//        put("PanTiltDownRight", Main::sendPanTiltDownRight);
//        put("PanTiltAbsolutePosition", Main::sendPanTiltAbsolutePos);
//        put("PanTiltMaxSpeed", Main::sendGetPanTiltMaxSpeed);
//        put("ZoomTeleStd", Main::sendZoomTeleStd);
//        put("ZoomWideStd", Main::sendZoomWideStd);
//        put("Address", Main::sendAddress);
    }};

    Main() {
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (RuntimeException e1) {
            e1.printStackTrace();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public String command(String command) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            List<String> actualMacro = new ArrayList<>();
            String cmd = reader.readLine();
            if (cmd.contains(":") && !macro.containsKey(cmd)) {
                List<String> l = Arrays.asList(cmd.split(" "));
                macro.put(l.get(0), l.subList(1, l.size()));
                macro.get(l.get(0)).forEach((c) -> runCommand(c, serialPort));
            } else if (commands.containsKey(cmd)) {
                return runCommand(cmd, serialPort);
            } else if (macro.containsKey(cmd)) {
                return macro.get(cmd).stream().map((c) -> runCommand(c, serialPort)).collect(Collectors.joining(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }


    private static String runCommand(String cmd, SerialPort serial) {
        try {
            serial.writeBytes(commands.get(cmd).get());
            byte[] response = ViscaResponseReader.readResponse(serial);
            System.out.println("> " + byteArrayToString(response));
            return byteArrayToString(response);
        } catch (ViscaResponseReader.TimeoutException var11) {
            System.out.println("TIMEOUT");
            return "TIMEOUT";
        } catch (SerialPortException e) {
            e.printStackTrace();
            System.out.println("SERIAL PORT EXCEPTION");
            return "SERIAL PORT EXCEPTION";
        }
    }

    public static void sleep(int timeSec) {
        try {
            Thread.sleep((long) (timeSec * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }
    }


    public void runMacro(String macro) {
        System.out.println(macro);

        ////// tutaj dostajemy string macro wysłane z frontu

//        List<String> l = Arrays.asList(macro.split(" "));
//        Map<String, List<String>> macroHashMap = new HashMap<>();
//        macroHashMap.put(l.get(0), l.subList(1, l.size()));
//        macroHashMap.get(l.get(0)).forEach((c) -> runCommand(c, serialPort));
    }

    public static byte[] sendClearAll() {
        byte[] cmdData = (new ClearAllCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 8;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltHome(byte destinationAdr) {
        byte[] cmdData = (new PanTiltHomeCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltUpLeft(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltUpLeftCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltUp(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltUpCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltUpRight(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltUpRightCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltLeft(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltLeftCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltLeft2(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltLeftCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltRight(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltRightCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltRight2(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltRightCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltDownLeft(byte panSpeed, byte tiltSpeed,  byte destinationAdr) {
        byte[] cmdData = (new PanTiltDownLeftCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltDown(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltDownCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltDownRight(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltDownRightCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendPanTiltAbsolutePos(byte panSpeed, byte tiltSpeed, byte destinationAdr) {
        byte[] cmdData = (new PanTiltAbsolutePosCmd()).createCommandData();
        cmdData[3] = panSpeed;
        cmdData[4] = tiltSpeed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendZoomTeleStd(byte destinationAdr) {
        byte[] cmdData = (new ZoomTeleStdCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendZoomWideStd(byte destinationAdr) {
        byte[] cmdData = (new ZoomWideStdCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = destinationAdr;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendAddress() {
        byte[] cmdData = (new AddressCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 8;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }

    public static byte[] sendGetPanTiltMaxSpeed() {
        byte[] cmdData = (new GetPanTiltMaxSpeedCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        return cmdData;
    }


    public static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] var5 = bytes;
        int var4 = bytes.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            byte b = var5[var3];
            sb.append(String.format("%02X ", new Object[]{Byte.valueOf(b)}));
        }
        return sb.toString();
    }
}