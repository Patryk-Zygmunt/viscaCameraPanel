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


    String commName = "COMM1";
    SerialPort serialPort = null;

    public static Map<String, List<String>>  macro = new HashMap<>();

    public static Map<String, Supplier<byte[]>> commands = new HashMap<String,Supplier<byte[]>>(){{
        put("ClearAll", Main::sendClearAll);
        put("PanTiltHome", Main::sendPanTiltHome);
        put("PanTiltLeft", Main::sendPanTiltLeft);
        put("PanTiltRight", Main::sendPanTiltRight);
        put("PanTiltLeft2", Main::sendPanTiltLeft2);
        put("PanTiltRight2", Main::sendPanTiltRight2);
        put("PanTiltUp", Main::sendPanTiltUp);
        put("PanTiltDown", Main::sendPanTiltDown);
        put("PanTiltAbsolutePosition", Main::sendPanTiltAbsolutePos);
        put("PanTiltMaxSpeed", Main::sendGetPanTiltMaxSpeed);
        put("ZoomTeleStd", Main::sendZoomTeleStd);
        put("ZommTeleWideStd", Main::sendZoomWideStd);
        put("Address", Main::sendAddress);
    }};


    Main() {
        try {
//            serialPort.openPort();
  //          serialPort.setParams(9600, 8, 1, 0);
        } catch (RuntimeException e1) {
            e1.printStackTrace();
        }
    }

    public String command(String command) {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            List<String> ActualaMacro = new ArrayList<>();
                String cmd = reader.readLine();
                if (cmd.contains(":") && !macro.containsKey(cmd)) {
                    List<String> l = Arrays.asList(cmd.split(" "));
                    macro.put(l.get(0), l.subList(1, l.size()));
                    macro.get(l.get(0)).forEach((c) -> runCommand(c, serialPort));
                } else if (commands.containsKey(cmd)) {
                 return  runCommand(cmd, serialPort);
                } else if (macro.containsKey(cmd)) {
                 return   macro.get(cmd).stream().map((c) -> runCommand(c, serialPort)).collect(Collectors.joining(" "));
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }


   private static String  runCommand(String cmd,SerialPort serial) {
       System.out.println(cmd);
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
       }
       return "error";
   }






      private static void sleep(int timeSec) {
      try {
         Thread.sleep((long)(timeSec * 1000));
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

   }

   private static byte[] sendClearAll()  {
      byte[] cmdData = (new ClearAllCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 8;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendPanTiltHome() {
      byte[] cmdData = (new PanTiltHomeCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendPanTiltLeft()  {
      byte[] cmdData = (new PanTiltLeftCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendPanTiltLeft2()  {
      byte[] cmdData = (new PanTiltLeftCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 2;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendPanTiltRight()  {
      byte[] cmdData = (new PanTiltRightCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendPanTiltRight2()  {
      byte[] cmdData = (new PanTiltRightCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 2;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendPanTiltUp() {
      byte[] cmdData = (new PanTiltUpCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendPanTiltDown()  {
      byte[] cmdData = (new PanTiltDownCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendPanTiltAbsolutePos()  {
      byte[] cmdData = (new PanTiltAbsolutePosCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendAddress() {
      byte[] cmdData = (new AddressCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 8;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;
   }

   private static byte[] sendGetPanTiltMaxSpeed() {
      byte[] cmdData = (new GetPanTiltMaxSpeedCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;

   }

   private static String byteArrayToString(byte[] bytes) {
      StringBuilder sb = new StringBuilder();
      byte[] var5 = bytes;
      int var4 = bytes.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         byte b = var5[var3];
         sb.append(String.format("%02X ", new Object[]{Byte.valueOf(b)}));
      }

      return sb.toString();
   }

   private static byte[] sendZoomTeleStd() {
      byte[] cmdData = (new ZoomTeleStdCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;

   }

   private static byte[] sendZoomWideStd()  {
      byte[] cmdData = (new ZoomWideStdCmd()).createCommandData();
      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
      System.out.println("@ " + byteArrayToString(cmdData));
      return cmdData;

   }
}
