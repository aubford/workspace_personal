package com.example.aubreyford.vario;

public class test {
  public static void main(String[] args){
    System.out.println("Tupac isn't dead!");


    VarioData mVarioData = new VarioData();

    mVarioData.addEntry(new AltitudeEntry(10,1460258695206L));
    mVarioData.addEntry(new AltitudeEntry(12,1460258751470L));
    mVarioData.addEntry(new AltitudeEntry(14,1460258759030L));
    mVarioData.addEntry(new AltitudeEntry(19,1460258767115L));

  }
}
