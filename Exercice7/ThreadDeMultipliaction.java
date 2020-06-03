package Exercice7;

public class ThreadDeMultipliaction extends Thread{
	
    int[][] result, firstMatrix, secondMatrix;
    int rowIndex, colIndex;
    
    public ThreadDeMultipliaction(int[][] result,int[][] firstM, int[][] secondM,int rowIndex, int colIndex){
            this.result=result;
            this.firstMatrix=firstM;
            this.secondMatrix=secondM;
            this.rowIndex=rowIndex;
            this.colIndex=colIndex;
    }
    
    public void run(){
        int total=0,counter=0;
        while(counter<this.firstMatrix[0].length){
            total+=firstMatrix[rowIndex][counter]*secondMatrix[counter][colIndex];
            counter++;
        }
        result[rowIndex][colIndex]=total;
    }
}