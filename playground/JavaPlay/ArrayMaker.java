public class ArrayMaker{
  public static void main(String[] args){
    int[] anArray = new int[]{1,2,3,4,5,6};
    int num = 94;
    String name = "whatever";
    int total = 0;
    for(int i = 0; i < anArray.length; i++){
      total += anArray[i];
    }

    System.out.println(total);

  }
}
