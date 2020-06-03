package Exercice7;

public class Matrice {
    private int[][] matrice;

    public Matrice(int[][] m) {
        matrice = m;
    }
    
    public static void main(String[] args) {
    	Matrice m1 = new Matrice(
            new int[][]{
                new int[]{7,3,9,12,5},
                new int[]{4,6,9,10,8},
                new int[]{9,3,5,12,19}
            }
        );
        Matrice m2 = new Matrice(
            new int[][]{
                new int[]{13,22,17},
                new int[]{9,5,0},
                new int[]{15,3,0},
                new int[]{13,11,12},
                new int[]{9,12,13}
            }
        );
        Matrice resultat = m1.fois(m2);
        System.out.println(resultat.toString());
    }

    public Matrice fois(Matrice m) {
        int resultLigneCount, resultColCount;
        resultLigneCount = this.matrice.length;
        resultColCount = m.getValeurActuelle()[0].length;
        int[][] result = new int[resultLigneCount][resultColCount];
        ThreadDeMultipliaction[] mts = new ThreadDeMultipliaction[resultLigneCount * resultColCount];
        int row = 0, col = 0;
        for (int i = 0; i < mts.length; i++) {
            mts[i] = new ThreadDeMultipliaction(result, this.matrice, m.getValeurActuelle(), row, col++);
            if (col == resultColCount) {
                row++;
                col = 0;
            }
            mts[i].start();/**/
        }
        try {
            for (ThreadDeMultipliaction mt : mts) {
                mt.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }/**/
        return new Matrice(result);
    }

    public int[][] getValeurActuelle(){
        return matrice;
    }

    public String toString(){
        String s="";
        for(int i=0;i<this.matrice.length;i++){
            s+="[";
            for(int j=0;j<this.matrice[0].length;j++){
                s+=this.matrice[i][j];
                if(j<this.matrice[0].length-1)
                    s+=", ";
            }
            s+="]\n";
        }
        return s;
    }

}