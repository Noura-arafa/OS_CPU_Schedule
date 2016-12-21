package os_assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OS_Assignment3 {

	public static class Process{
	    int id,arrivalTime,burstTime,turnaroundTime,finishedTime;
	    boolean finished;
	    Process(){
	        id =0;
	        arrivalTime = 0;
	        burstTime = 0;
	        finished = false;
	    }
	}

public static class Queue implements Comparable<Queue>{
    Vector<Process> queue;
    String algorithm;
    int priority, numberOfProcesses;
    Queue(){
        queue = new Vector<>();
        algorithm = "";
        priority = 0;
    }
   
    public int compareTo(Queue o) {
        return o.priority - this.priority;
    }
    
}
public static void sortRR(Queue RR){
	ArrayList<Integer> x=new ArrayList<>();
	for(int i=0;i<RR.queue.size();i++){
		x.add(RR.queue.get(i).arrivalTime);
	}
	Collections.sort(x);
	Queue y=new Queue();
	for(int i=0;i<x.size();i++){
		for(int j=0;j<RR.queue.size();j++){
			if(x.get(i)==RR.queue.get(j).arrivalTime){
				Process p=RR.queue.get(j);
				y.queue.add(p);
				break;
			}
		}
	}
	RR.queue.clear();
	for(int i=0;i<y.queue.size();i++){
		RR.queue.add(y.queue.get(i));
	}
	System.out.println(RR.algorithm+" "+RR.numberOfProcesses);
	for(int i=0;i<RR.queue.size();i++){
		
		System.out.println(RR.queue.get(i).id);
		System.out.println(RR.queue.get(i).arrivalTime);
		System.out.println(RR.queue.get(i).burstTime);
		System.out.println("----------------------------");
		
	}
}

public static void sortshortest(Queue shortest){
	ArrayList<Integer> x=new ArrayList<>();
	for(int i=0;i<shortest.queue.size();i++){
		x.add(shortest.queue.get(i).burstTime-shortest.queue.get(i).arrivalTime);
	}
	Collections.sort(x);
	Queue y=new Queue();
	for(int i=0;i<x.size();i++){
		for(int j=0;j<shortest.queue.size();j++){
			int z=shortest.queue.get(j).burstTime-shortest.queue.get(j).arrivalTime;
			if(x.get(i)==z){
				Process p=shortest.queue.get(j);
				y.queue.add(p);
				break;
			}
		}
	}
	shortest.queue.clear();
	for(int i=0;i<y.queue.size();i++){
		shortest.queue.add(y.queue.get(i));
	}
	//System.out.println(shortest.algorithm+" "+shortest.numberOfProcesses);
/*	System.out.println(shortest.queue.size());
	for(int i=0;i<shortest.queue.size();i++){
		System.out.println("hahhahah1");
		System.out.println(shortest.queue.get(i).id);
		System.out.println(shortest.queue.get(i).arrivalTime);
		System.out.println(shortest.queue.get(i).burstTime);
		System.out.println("----------------------------");
		
	}*/
	                        
}
public static void RR(Process P,int i){
    for (int k = 0; k < 2; k++) {
       P.burstTime--;
        systemTime++;
        if(P.burstTime == 0){
            P.finished = true;
            P.turnaroundTime = systemTime - P.arrivalTime;
            P.finishedTime = systemTime;
            terminatedProcesses.add(P);
            arrOfQueues.get(i).queue.remove(0);
            break;
        }
        
    }
    if(P.finished == false){
        arrOfQueues.get(i).queue.remove(0);
        arrOfQueues.get(i).queue.add(P);
    }
    

}
public static void SJF(Process P){
	
}
public static void FCFS(Process P){
	 systemTime += P.burstTime;
	 P.finished = true;
     P.turnaroundTime = systemTime - P.arrivalTime;
     P.finishedTime = systemTime;
     terminatedProcesses.add(P);
  
}

public static Queue check(int index){
	Queue queue = new Queue();
	int i;
	boolean flag = false;
	if(index == 0)
		return null;
	else{
		for (  i = 0 ; i < index ; i++) {
             if(arrOfQueues.get(i).queue.get(0).arrivalTime<=systemTime){
            	 flag=true;
                 break;
             }
         }
		if(flag){
			queue = arrOfQueues.get(i);
			return queue;
		}
		
	}
	return null;
	
}

static private final String input = "inputs.txt";
static private final String output = "output.txt";
static public Vector<Process> terminatedProcesses;
static Vector<Queue> arrOfQueues = new Vector<>();
static public int systemTime = 0;
    public static void main(String[] args) {
    	Scanner sc = new Scanner("file.txt");
    	while(sc.hasNext()){
    		int numberOfQueues = sc.nextInt();
    		Process process;
            Queue queue ;
            for (int i = 0; i < numberOfQueues; i++) {
                queue = new Queue();
                queue.algorithm = sc.next();
                queue.priority = sc.nextInt();
                queue.numberOfProcesses = sc.nextInt();
                for (int j = 0; j < queue.numberOfProcesses; j++) {
                    process = new Process();
                    process.id = i;
                    process.arrivalTime = sc.nextInt();
                    process.burstTime = sc.nextInt();
                    queue.queue.add(process);
                }
                arrOfQueues.add(queue);
            }
    		
    	}
    	
    	
    	
    	
    	/* Scanner in = new Scanner(System.in);
         System.out.println("Enter number of queues");
         int numberOfQueues = in.nextInt();
         
         Process process;
         Queue queue ;
         for (int i = 0; i < numberOfQueues; i++) {
             queue = new Queue();
             System.out.println("Enter priority");
             queue.priority = in.nextInt();
             System.out.println("Enter queue algorithm");
             queue.algorithm = in.next();
             System.out.println("Enter number of processes");
             queue.numberOfProcesses = in.nextInt();
             for (int j = 0; j < queue.numberOfProcesses; j++) {
                 process = new Process();
                 process.id = i;
                 System.out.println("Enter arrival time of process");
                 process.arrivalTime = in.nextInt();
                 System.out.println("Enter burst time of process");
                 process.burstTime = in.nextInt();
                 queue.queue.add(process);
             }
             arrOfQueues.add(queue);
         }*/
         //please sort all queues first right here
         for(int i=0;i<3;i++){
        	 if(arrOfQueues.get(i).algorithm=="RR"){
        		 sortRR(arrOfQueues.get(i));
        	 }
        	 else if(arrOfQueues.get(i).algorithm=="JSF"){
        		 sortshortest(arrOfQueues.get(i));
        	 }
         }
         //Collections.sort( arrOfQueues); 
         int i;
         while(true){
         if(arrOfQueues.isEmpty()){
             break;
         }
         
             for ( i = 0; i < arrOfQueues.size(); i++) {
                 if(arrOfQueues.get(i).queue.get(0).arrivalTime<=systemTime){
                     break;
                 }
             }                  
             if(arrOfQueues.get(i).algorithm.equals("RR")){
            	 RR(arrOfQueues.get(i).queue.get(0),i);
            	 
             }
             else if (arrOfQueues.get(i).algorithm.equals("SJF")){
            	 sortshortest(arrOfQueues.get(i));
             	 Queue q;
             	 q=check(i);
             	 if(q==null){
             		 SJF(arrOfQueues.get(i).queue.get(0));
             	 }
             	 else{
             		 Process P=q.queue.get(0);
             		 if(q.algorithm=="RR"){
             			 arrOfQueues.get(i).queue.get(0).burstTime-=P.arrivalTime;
             			 RR(P,q.priority-1);
             		 }
             		 else {
             			 arrOfQueues.get(i).queue.get(0).burstTime-=P.arrivalTime;
             			 FCFS(P);
             		 }
             	 }
              }
              else if(arrOfQueues.get(i).algorithm.equals("FCFS")){
             	 Queue q;
             	 q=check(i);
             	 if(q==null){
             		 FCFS(arrOfQueues.get(i).queue.get(0));
             		 arrOfQueues.get(i).queue.remove(0);
             	 }
             	 else{
             		 Process P=q.queue.get(0);
             		 arrOfQueues.get(i).queue.get(0).burstTime-= systemTime;
             		 if(q.algorithm=="RR"){
             			 RR(P,q.priority-1);
             		 }
             		 
             		 else {
             			 arrOfQueues.get(i).queue.get(0).burstTime-= systemTime;
             			 SJF(P);
             		 }
             	 }
              }
             
             if(arrOfQueues.get(i).queue.isEmpty()){
                 arrOfQueues.remove(i);
             }
             systemTime++;
         
     }
    }
}
