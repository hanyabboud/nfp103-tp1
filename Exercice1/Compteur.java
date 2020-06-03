package Exercice1;

public class Compteur extends Thread{

    public Compteur(String name){
        super(name);
    }

    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(currentThread().getName()+" = "+i);
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){

            }
        }
        System.out.printf("the thread %s has ended\n",currentThread().getName());
    }
    public static void main(String[] args) {
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