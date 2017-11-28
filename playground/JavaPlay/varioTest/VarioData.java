import java.util.ArrayList;



public class VarioData {

    ArrayList<AltitudeEntry> varioData;
    double[] lastAltPoint = {0.0, 0.0};


    public VarioData(){
        this.varioData = new ArrayList<>();
    }

    public ArrayList<AltitudeEntry> getAltitudeEntries() {
        return varioData;
    }

    public void addEntry(AltitudeEntry entry) {
        varioData.add(entry);
    }


     public double getCurrentMpS(){

        if(varioData.size() < 4) {
            return 0;
        }else{

            if(lastAltPoint[1] == 0.0){
              lastAltPoint[0] = varioData.get(varioData.size()-2).altitude;
              lastAltPoint[1] = varioData.get(varioData.size()-2).timestamp / 1000.0;
            }

            double[] currentPoint = {0.0, varioData.get(varioData.size()-1).timestamp / 1000.0};

            double sum = 0.0;
            double netTime = (varioData.get(varioData.size()-1).timestamp - varioData.get(varioData.size()-4).timestamp) / 1000.0;

            for(int i = varioData.size() - 3 ; i < varioData.size() ; i++ ){

                AltitudeEntry current = varioData.get(i);
                AltitudeEntry prev = varioData.get(i-1);

                double timeDifference = (current.timestamp - prev.timestamp) / 1000.0;
                
                double altitudeReading = current.altitude;


                sum += (timeDifference * altitudeReading);
            }

            currentPoint[0] = sum / netTime;
            System.out.println(currentPoint[0]);
            System.out.println(lastAltPoint[0]);
            System.out.println(currentPoint[1]);
            System.out.println(lastAltPoint[1]);
            double MpS = (currentPoint[0] - lastAltPoint[0]) / (currentPoint[1] - (lastAltPoint[1]) );
            lastAltPoint = currentPoint;

            return MpS;
        }
    }

    public double getAvgMpS(){
            double sum = 0.0;

            for(int i = varioData.size() - 3 ; i < varioData.size() ; i++ ){

                AltitudeEntry current = varioData.get(i);
                AltitudeEntry prev = varioData.get(i-1);

                double timeDifference = (current.timestamp - prev.timestamp) / 1000.0;
                double altitudeDifference = current.altitude - prev.altitude;

                sum += (altitudeDifference/timeDifference);
            }
            return sum/3;

    }

    public double getNet(){

                AltitudeEntry current = varioData.get(varioData.size()-1);
                AltitudeEntry prev = varioData.get(varioData.size()-4);
                double timeDifference = (current.timestamp - prev.timestamp) / 1000.0;
                System.out.println("NET Time Diff:  "+ timeDifference);

                double altitudeDifference = current.altitude - prev.altitude;
                return (altitudeDifference/timeDifference);
    }




}
