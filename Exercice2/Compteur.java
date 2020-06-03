package Exercice2;

public class Compteur extends Thread{
    
	private static int pos;
	
    public Compteur(String name){
        super(name);
    }

    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(currentThread().getName()+" = "+i);
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
            	e.printStackTrace();
            }
            if(i==4){
                synchronized(System.out){
                    System.out.printf("the thread %s has ended with %d rank\n",currentThread().getName(), ++pos);
                }
            }
        }
        
    }
    public static void main(String[] args) {
    	pos = 0;
        Compteur[] cpts = {
            new Compteur("TH1"),
            new Compteur("TH2"),
            new Compteur("TH3"),
            new Compteur("TH4")
        };
        for(Compteur c:cpts){
            c.start();
        }
        
    }
}