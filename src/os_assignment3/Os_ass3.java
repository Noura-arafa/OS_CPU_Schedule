package os_assignment3;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import os_assignment3.OS_Assignment3.Process;
import os_assignment3.OS_Assignment3.Queue;

public class Os_ass3 {

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
        return  o.priority - this.priority  ;
    }
    
}

public static class Pair implements Comparable{
	int arr;
	int ID;
	public int compareTo(Object o){
		Pair a1 =(Pair) o;
		if(this.arr > a1.arr )
			return 1;
		
		else if(this.arr < a1.arr)
			return -1;
		
		return 0;
		
	}
} 
public static void sortRR(Queue RR){
	List<Pair> x=new ArrayList<>();
	
	for(int i=0;i<RR.queue.size();i++){
		Pair pair = new Pair();
		pair.arr = RR.queue.get(i).arrivalTime;
		pair.ID = RR.queue.get(i).id;
		x.add(pair);
	}
	
	
	
	Queue y=new Queue();
	for(int i=0;i<x.size();i++){
		for(int j=0;j<RR.queue.size();j++){
			if(x.get(i).arr == RR.queue.get(j).arrivalTime && x.get(i).ID == RR.queue.get(j).id){
				Process p = RR.queue.get(j);
				y.queue.add(p);
				break;
			}
		}
	}
	RR.queue.clear();
	for(int i=0;i<y.queue.size();i++){
		RR.queue.add(y.queue.get(i));
	}
	
	
}

public static class SJF_Pair implements Comparable{
	int burst;
	int ID;
	public int compareTo(Object o){
		SJF_Pair a1 =(SJF_Pair) o;
		if(this.burst > a1.burst)
			return 1;
		
		else if(this.burst < a1.burst)
			return -1;
		
		return 0;
		
	}
} 
public static Queue sortshortest(Queue shortest){
	
	List<SJF_Pair> x=new ArrayList<>();
	for(int i=0;i<shortest.queue.size();i++){
		SJF_Pair pair = new SJF_Pair();
		pair.burst = shortest.queue.get(i).burstTime;
		pair.ID =shortest.queue.get(i).id;
		x.add(pair);
	}
	Collections.sort(x);
	
	Queue y=new Queue();
	for(int i=0;i<x.size();i++){
		for(int j=0;j<shortest.queue.size();j++){
			//int z=shortest.queue.get(j).burstTime;
			if(x.get(i).ID== shortest.queue.get(j).id){
				Process p=shortest.queue.get(j);
				y.queue.add(p);
				break;
			}
		}
	}
	shortest.queue.clear();
	for(int i=0;i<y.queue.size();i++){
		shortest.queue.add(y.queue.get(i));
		// System.out.println("arrival"+" "+shortest.queue.get(i).arrivalTime+"burst"+shortest.queue.get(i).burstTime);
	}
	
	  return shortest;                 
}

public static int check(int index,int pindex){
	 
	Queue queue = new Queue();
	int i;
	boolean flag = false;
	int min = 999999;
	int old_min= 999999;
	int j= 0;
	int minn=99999999;
	int oldminn=999999999;
	int c=0;
	if(index == 0)
		return -1;
	else{
		for (  i = index-1 ; i >=0 ; i--) {
			if(arrOfQueues.get(i).queue.isEmpty())
				break;
			//System.out.println(arrOfQueues.get(i).algorithm);
			if(arrOfQueues.get(i).algorithm.equals("SJF")){
				
				for(int k=0;k<arrOfQueues.get(i).queue.size();k++){
					
					if(arrOfQueues.get(i).queue.get(k).arrivalTime<=systemTime){
					
					minn=Math.min(arrOfQueues.get(i).queue.get(k).burstTime, minn);
					if(oldminn!=minn){
						//System.out.println("kk"+k);
						c=k;
						oldminn=minn;
					}
					}
				}		
				
			}
			else{
				c=0;
			}
			//System.out.println("c"+c);
             if(arrOfQueues.get(i).queue.get(c).arrivalTime<=systemTime+arrOfQueues.get(index).queue.get(pindex).burstTime){ 
            	
            	 min = Math.min(arrOfQueues.get(i).queue.get(c).arrivalTime, min);
            	 
            	 if(old_min != min){
            		 j = i;
            		 old_min = min;
            	 }
            	 flag=true;
                 
             }
         }
		if(flag){
			queue = arrOfQueues.get(j);
			processarrivaltime=arrOfQueues.get(j).queue.get(c).arrivalTime;
			
			return j;
			
		}
		
	}
	return -1;
	
}
static private final String input = "inputs.txt";
static private final String output = "output.txt";
static public Vector<Process> terminatedProcesses;
static Vector<Queue> arrOfQueues = new Vector<>();
static public int systemTime = 0;
static public int processarrivaltime=0;
static boolean lessthen=true;
static Process process;
static Queue queue ;
    public static void main(String[] args) throws FileNotFoundException {
    	terminatedProcesses = new Vector<>();
    	int terminated1=0,terminated2=0,terminated3=0;
    	int x1=0,x2=0,x3=0;
    	Scanner sc = new Scanner(new File("file.txt"));
    	while(sc.hasNext()){
    		int numberOfQueues = sc.nextInt();
            for (int i = 0; i < numberOfQueues; i++) {
                queue = new Queue();
                queue.algorithm = sc.next();
                queue.priority = sc.nextInt();
                queue.numberOfProcesses = sc.nextInt();
                if(queue.algorithm.equals("RR"))
                	x1 += queue.numberOfProcesses;
                else if(queue.algorithm.equals("FCFS"))
                	x3 += queue.numberOfProcesses;
                else
                	x2 += queue.numberOfProcesses;
              
                for (int j = 0; j < queue.numberOfProcesses; j++) {
                    process = new Process();
                    process.id = j;
                    process.arrivalTime = sc.nextInt();
                    process.burstTime = sc.nextInt();
                    queue.queue.add(process);
                }
                arrOfQueues.add(queue);
            }
    		
    	}
    	 
    	Collections.sort(arrOfQueues);
         for(int l=0;l<3;l++){
        	 if(arrOfQueues.get(l).algorithm.equals("RR")){
        		 sortRR(arrOfQueues.get(l));
        	 }
        	 else if(arrOfQueues.get(l).algorithm.equals("SJF")){
        		 arrOfQueues.set(l,sortshortest(arrOfQueues.get(l)));
        		 for(int r=0;r<arrOfQueues.get(l).queue.size();r++){
        	//		 System.out.println("arrival"+" "+arrOfQueues.get(l).queue.get(r).arrivalTime+"burst"+arrOfQueues.get(l).queue.get(r).burstTime);
        		 }
        	 }
        	 
        	// System.out.println("index"+" "+l+" "+arrOfQueues.get(l).algorithm);
         }
          
         int i;
         int x=-1;
         int minn=99999999;
         int oldminn=999999999;
         int c=0;
         PrintStream  outstream = null;
         try{
        	 
        	 outstream = new PrintStream("output.txt");
        	 System.setOut(outstream);
         }
         catch(Exception e){
        	 
         }
         while(true){
        	
        	 int min=999999;
         if(arrOfQueues.isEmpty()){
             break;
         }
         lessthen=false;
         
             for ( i = 0; i < arrOfQueues.size(); i++) {
            	 if(arrOfQueues.get(i).queue.isEmpty()){	 
            		 continue;
            	 }
            	 c=0;
            	 minn=99999999;
            	 oldminn=99999999;
            	 if(arrOfQueues.get(i).algorithm.equals("SJF")){
     				for(int k=0;k<arrOfQueues.get(i).queue.size();k++){
     					if(arrOfQueues.get(i).queue.get(k).arrivalTime<=systemTime){
     					minn=Math.min(arrOfQueues.get(i).queue.get(k).burstTime, minn);
     					if(oldminn!=minn){
     						
     						c=k;
     						oldminn=minn;
     					}
     					}
     				}		
     				
     			}
     			else{
     				c=0;
     			}
	 
            	 if(x!=-1){
            		 i=x;
            		 lessthen=true;
            		 x=-1;
            		 break;
            	 }
                 if(arrOfQueues.get(i).queue.get(c).arrivalTime<=systemTime){
                	 lessthen=true;
                     break;
                 }
             }
             if(i ==  arrOfQueues.size() )
            	 break;
             
             if(!lessthen){
            	 for(int j=0;j<arrOfQueues.size();j++){
            		 for(int r=0;r<arrOfQueues.get(j).queue.size();r++){
            			 if(!(arrOfQueues.get(j).queue.isEmpty()))
            				min = Math.min(arrOfQueues.get(j).queue.get(0).arrivalTime, min);
            		 }
            	 }
            	 systemTime =min;
      
             }
             if(arrOfQueues.elementAt(i).algorithm.equals("RR") ){
            	 
            	    
            	    System.out.println("Process"+" "+arrOfQueues.elementAt(i).queue.elementAt(0).id+" queue "+ arrOfQueues.elementAt(i).algorithm +" started at"+" "+systemTime);
                	
            	    int index=check(i,0);
            	    
            	    if(index==-1){
            	    	
            	    
            	    	arrOfQueues.elementAt(i).queue.elementAt(0).finished = false;
                   	 
                   	 for (int k = 0; k < 2; k++) {
            
                   		 arrOfQueues.elementAt(i).queue.elementAt(0).burstTime--;
                   	        systemTime++;
                   	        if(arrOfQueues.elementAt(i).queue.elementAt(0).burstTime <= 0){
                   	        	
                   	        	arrOfQueues.elementAt(i).queue.elementAt(0).finished = true;
                   	        	arrOfQueues.elementAt(i).queue.elementAt(0).turnaroundTime = systemTime - arrOfQueues.elementAt(i).queue.elementAt(0).arrivalTime;
                   	        	arrOfQueues.elementAt(i).queue.elementAt(0).finishedTime = systemTime;
                   	            System.out.println("process"+" "+arrOfQueues.elementAt(i).queue.elementAt(0).id+" queue "+ arrOfQueues.elementAt(i).algorithm +"finished at"+" "+systemTime);
                   	            terminated1 += arrOfQueues.elementAt(i).queue.elementAt(0).turnaroundTime;
                   	            terminatedProcesses.add(arrOfQueues.elementAt(i).queue.remove(0));                   	            
                   	         System.out.println("RR " + process.id + " " + "TurnArroundTime: " + terminatedProcesses.lastElement().turnaroundTime);
                   	            
                   	            break;
                   	        }
                   	        
                   	    }
                   	 systemTime++;
                   	 if(arrOfQueues.elementAt(i).queue.isEmpty())continue;
                   	    if(arrOfQueues.elementAt(i).queue.elementAt(0).finished == false ){
       
                   	        
                   	        arrOfQueues.elementAt(i).queue.add(arrOfQueues.elementAt(i).queue.remove(0));
                   	        
                   	    }
                   	    	
            	    	x=-1;
            	    }
            	    
            	    else{
            	    	arrOfQueues.elementAt(i).queue.elementAt(0).burstTime--;
            	    	
            	    	systemTime+=2;
            	    	
            	System.out.println("proces"+" "+arrOfQueues.elementAt(i).queue.elementAt(0).id+ " queue "+ arrOfQueues.get(i).algorithm +" interrupted at"+" "+systemTime);
            	x=index;


            	    } 
//            	 arrOfQueues.elementAt(i).queue.elementAt(0).finished = false;
//            	 System.out.println("Process"+" "+arrOfQueues.elementAt(i).queue.get(0).id+" queue "+ arrOfQueues.elementAt(i).algorithm +" started at"+" "+systemTime);
//            	 for (int k = 0; k < 2; k++) {
//     
//            		 arrOfQueues.elementAt(i).queue.elementAt(0).burstTime--;
//            	        systemTime++;
//            	        if(arrOfQueues.elementAt(i).queue.elementAt(0).burstTime <= 0){
//            	        	System.out.println("ok");
//            	        	arrOfQueues.elementAt(i).queue.elementAt(0).finished = true;
//            	        	arrOfQueues.elementAt(i).queue.elementAt(0).turnaroundTime = systemTime - arrOfQueues.elementAt(i).queue.elementAt(0).arrivalTime;
//            	        	arrOfQueues.elementAt(i).queue.elementAt(0).finishedTime = systemTime;
//            	            System.out.println("process"+" "+arrOfQueues.elementAt(i).queue.elementAt(0).id+" queue "+ arrOfQueues.elementAt(i).algorithm +"finished at"+" "+systemTime);
//            	            terminated3 += arrOfQueues.elementAt(i).queue.elementAt(0).turnaroundTime;
//            	            terminatedProcesses.add(arrOfQueues.elementAt(i).queue.remove(0));
//            	            
//            	            break;
//            	        }
//            	        
//            	    }
//            	 
//            	    if(arrOfQueues.elementAt(i).queue.elementAt(0).finished == false && !arrOfQueues.elementAt(i).queue.isEmpty()){
//
//            	        
//            	        arrOfQueues.elementAt(i).queue.add(arrOfQueues.elementAt(i).queue.remove(0));
//            	        
//            	    }
            	  
             }
             else if (arrOfQueues.get(i).algorithm.equals("SJF")){
            	 c=0;
            	 oldminn=999999;
            	 minn=9999999;
     				for(int k=0;k<arrOfQueues.get(i).queue.size();k++){
     					if(arrOfQueues.get(i).queue.get(k).arrivalTime<=systemTime){
     					minn=Math.min(arrOfQueues.get(i).queue.get(k).burstTime, minn);
     					if(oldminn!=minn){
     						c=k;
     						//System.out.println("K"+arrOfQueues.get(i).queue.get(k).burstTime );
     						oldminn=minn;
     					}
     					}
     				}		
     				
     			
     			
    System.out.println("Process"+" "+arrOfQueues.get(i).queue.get(c).id+" queue "+ arrOfQueues.get(i).algorithm +" started at"+" "+systemTime);
            	
    int index=check(i,c);
    
    if(index==-1){
    	
    process=arrOfQueues.get(i).queue.get(c);
    process.turnaroundTime=(systemTime+process.burstTime)-process.arrivalTime;
    System.out.println("SJF " + process.id + " " + "TurnArroundTime: " + process.turnaroundTime);
    terminatedProcesses.add(process);
    systemTime +=process.burstTime;
    
    System.out.println("process"+" "+arrOfQueues.get(i).queue.get(c).id+" queue "+ arrOfQueues.get(i).algorithm +" finished at"+" "+systemTime);
    process.finishedTime=systemTime;
    	systemTime++;
    	terminated2+=process.turnaroundTime;
    	arrOfQueues.get(i).queue.remove(c);
    	
    	x=-1;
    }
    else{
    	
    	systemTime=processarrivaltime+1;
    	
    	systemTime=processarrivaltime;
System.out.println("proces"+" "+arrOfQueues.get(i).queue.get(c).id+ " queue "+ arrOfQueues.get(i).algorithm +" interrupted at"+" "+systemTime);

arrOfQueues.get(i).queue.get(c).burstTime=((arrOfQueues.get(i).queue.get(c).burstTime +arrOfQueues.get(i).queue.get(c).arrivalTime)- systemTime);
//System.out.println("ooh"+arrOfQueues.get(i).queue.get(c).burstTime);
systemTime=processarrivaltime+1;
x=index;


    } 
             }
             else if(arrOfQueues.get(i).algorithm.equals("FCFS")){
            	  System.out.println("Process"+" "+arrOfQueues.get(i).queue.get(0).id+" queue "+ arrOfQueues.get(i).algorithm +" started at"+" "+systemTime);
              	
      int index=check(i,0);
      if(index==-1){
      	process = new Process();
      	process=arrOfQueues.get(i).queue.get(0);
      	process.turnaroundTime=(systemTime+process.burstTime)-process.arrivalTime;
     System.out.println("FCFS " + process.id + " " + "TurnArroundTime: " + process.turnaroundTime);
      terminatedProcesses.add(process);
      systemTime +=process.burstTime;
      
      System.out.println("process"+" "+arrOfQueues.get(i).queue.get(0).id+" queue "+ arrOfQueues.get(i).algorithm +" finished at"+" "+systemTime);
      	
      	process.finishedTime=systemTime;
      	terminated3+=process.turnaroundTime;
      	arrOfQueues.get(i).queue.remove(0);
      	systemTime++;
      	x=-1;
      }
      else{
      	
    	  systemTime=processarrivaltime;	
  System.out.println("proces"+" "+arrOfQueues.get(i).queue.get(0).id+" queue "+ arrOfQueues.get(i).algorithm +" interrupted at"+" "+systemTime);
  
  arrOfQueues.get(i).queue.get(0).burstTime=((arrOfQueues.get(i).queue.get(0).burstTime +arrOfQueues.get(i).queue.get(0).arrivalTime)- systemTime);
  systemTime=processarrivaltime+1;
  x=index;
 

      }
             }
             
             /*if(arrOfQueues.get(i).queue.isEmpty()){
                 arrOfQueues.remove(i);
             }  */
     }
         /*for(int k=0; k<terminatedProcesses.size(); k++){
        	 System.out.println("P" + terminatedProcesses.get(k).id + " turn arround time = " + terminatedProcesses.get(k).turnaroundTime);
         }*/
        
         System.out.println("average RR"+" "+terminated1/x1);
         System.out.println("average SJF"+" "+terminated2/x2);
         System.out.println("average FCFS"+" "+terminated3/x3);
         
    }
}
