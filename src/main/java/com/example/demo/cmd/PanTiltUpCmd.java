package com.example.demo.cmd;

public final class PanTiltUpCmd extends Cmd {

   private static final byte[] ptUpCommandData = new byte[]{(byte)1, (byte)6, (byte)1, (byte)0, (byte)0, (byte)3, (byte)1};


   public byte[] createCommandData() {
      byte[] cmdData = duplicateArray(ptUpCommandData);
//      cmdData[3] = 1;
//      cmdData[4] = 2;
      return cmdData;
   }

   private static byte[] duplicateArray(byte[] src) {
      byte[] dest = new byte[src.length];
      System.arraycopy(src, 0, dest, 0, src.length);
      return dest;
   }
}
