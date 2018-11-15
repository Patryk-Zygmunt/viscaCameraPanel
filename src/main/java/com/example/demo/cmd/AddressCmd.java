package com.example.demo.cmd;

public final class AddressCmd extends Cmd {

   private static final byte[] adrCommmandData = new byte[]{(byte)48, (byte)1};


   public byte[] createCommandData() {
      byte[] cmdData = duplicatArray(adrCommmandData);
      return cmdData;
   }

   private static byte[] duplicatArray(byte[] src) {
      byte[] dest = new byte[src.length];
      System.arraycopy(src, 0, dest, 0, src.length);
      return dest;
   }
}
