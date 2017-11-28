import java.util.Scanner;

public class ArrayCalculator{
  public static void main(String[] args){

    // System.out.println("Enter array");
    // Scanner arrayTaker = new Scanner(System.in);
    System.out.println("Enter operator");
    Scanner textTaker = new Scanner(System.in);
    String get = textTaker.next();
    int arr[] = new int[]{1,2,3,4,5};
    makeArr(arr, get);
  }

    public static void makeArr(int[] arr, String operator){

      int total = 0;

      for(int i = 0 ; i< arr.length; i++){
        if (operator.equals("add")) total += arr[i];
        if (operator.equals("subtract")) total -= arr[i];
        if (operator.equals("multiply")) total *= arr[i];
        if (operator.equals("divide")) total /= arr[i];
      }

      System.out.println(total);

    }

}
