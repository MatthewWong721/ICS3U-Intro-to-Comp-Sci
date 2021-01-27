package week1;

public class ArraysExamples {
    public static void main(String[] args){
        exampleOne();
        exampleTwo();
    }

    private static void exampleTwo(){
    }

    private static void exampleOne(){
        int[] arr = new int[10];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*10+1);
        }
        int sum=0;

        for(int x:arr){
            sum+=x;
        }
        System.out.println(sum);
    }
}
