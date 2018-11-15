package com.example.demo.cmd;

public final class ClearAllCmd extends Cmd {

   private static final byte[] clearAllCommandData = new byte[]{(byte)1, (byte)0, (byte)1};


   public byte[] createCommandData() {
      byte[] cmdData = duplicatArray(clearAllCommandData);
      return cmdData;
   }

   private static byte[] duplicatArray(byte[] src) {
      byte[] dest = new byte[src.length];
      System.arraycopy(src, 0, dest, 0, src.length);
      return dest;
   }
}
