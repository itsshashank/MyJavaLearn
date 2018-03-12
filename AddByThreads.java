import java.util.*;
import java.util.concurrent.*;

class Adding implements Callable<Integer>{
    int a,b; 
    int[] arr;
    int time;
    public Adding(int a, int b,int[] arr,int t){
        this.a = a;
        this.b = b;
        this.arr =arr;
        this.time = t;
    }
    public Integer call() {
        int sum = 0;
 
        for (int i = a; i < b; i++) {
            sum = sum + arr[i];
        }
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return sum;
    }
}
public class AddByThreads{
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a =new int[n];
        for(int i=0;i<n;i++)
        {
            a[i]=s.nextInt();
        }
        //int a[]={1,2,3,4,0,0};
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> ans1 = pool.submit(new Adding(0,2,a,2000));
        Future<Integer> ans2 = pool.submit(new Adding(2,4,a,5000));
        try {
 
            Integer fhalf = ans1.get();
            Integer shalf = ans2.get();
            System.out.println("Sum ="+(fhalf+shalf));
 
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
 
        pool.shutdown();
        s.close();
    }
}
