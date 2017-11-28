public class Run{
  public static void main(String[] args){

    VarioData data = new VarioData();

    data.addEntry(new AltitudeEntry(10,1460270259278L));
    data.addEntry(new AltitudeEntry(12,1460270271793L));
    data.addEntry(new AltitudeEntry(13,1460270280290L));
    System.out.println("MpS: " + data.getCurrentMpS());
    data.addEntry(new AltitudeEntry(19,1460270288270L));
    System.out.println("MpS: " + data.getCurrentMpS());
    data.addEntry(new AltitudeEntry(14,1460270296551L));

    double Net = data.getNet();
    double Avg = data.getAvgMpS();
    double MpS = data.getCurrentMpS();


    System.out.println("NET: " + Net);
    System.out.println("AVG: " + Avg);
    System.out.println("MpS: " + MpS);

  }
}
