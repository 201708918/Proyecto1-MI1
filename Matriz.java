/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomate;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author prisc
 */

public class Matriz {
    
    static Scanner In = new Scanner(System.in);

    public Matriz(){
        
        double [][] Mensaje = LeerMatriz();
                System.out.print("\n");
        double [][] M = LeerMatriz();
                System.out.print("\n");
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.print("\n");
        System.out.println("LA MATRIZ MENSAJE ES");
        System.out.print("\n");
        mostrarMatriz(Mensaje);
        System.out.print("\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.print("\n");
        System.out.println("LA METRIZ M ES");
        System.out.print("\n");
        mostrarMatriz(M);
        System.out.print("\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.print("\n");
        double Det = Determinante(1,M);
        
        double [][]Adj = AdjuntaMatriz(M);
        
            System.out.println("LA DETERMINANTE ES: \t "+ Det);
            System.out.print("\n");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.print("\n");
            if (Det==0) {
                System.out.println("NO EXIXTE MATRIZ INVERSA");
                   return;
            }

            System.out.println("LA MATRIZ INVERSA ES");
            System.out.print("\n");

            double [][] Inv = multiplicacion (Adj,Det);
            
            mostrarMatriz(Inv);
            
            System.out.print("\n");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
        
            double [][] Resultado = Codigo(Mensaje,Inv);

            System.out.println("LA MATRIZ RESULTANTE ES");
            
            System.out.print("\n");
            
            mostrarMatriz(Resultado);   
            
            System.out.print("\n");
            
        System.out.println("----------------------------------------------- EL MENSAJE DESCIFRADO ES --------------------------------------------------");
        System.out.print("\n");
        LectorASCII(Resultado);
        System.out.print("\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.print("\n");
        
    }
    public static double [][] LeerMatriz (){
        
    System.out.println("--------------------------------------------------------------------------------------------------------------------");
    System.out.println("                                               ");        
    System.out.println("         INGRESE LA RUTA DEL ARCHIVO           ");
    System.out.println("                                               ");     
    System.out.println("--------------------------------------------------------------------------------------------------------------------");
    System.out.print("\n");
    System.out.println("USUARIO:");
    System.out.print("\n");
    
    String ruta = In.nextLine();
    String Datos= ""; 
    
    try{
        File Archivo = new File(ruta);
        Scanner MatrizEnteros = new Scanner (Archivo);
               
        while(MatrizEnteros.hasNextLine()){            
        Datos += MatrizEnteros.nextLine() + ("\n");
        }
        MatrizEnteros.close();
    }catch(Exception e){
        System.out.println("Ocurrio un error");
        System.out.println(e);
    }
    
    System.out.println(Datos);
    
    String[] TextoFila = Datos.split("\n");
    
    int n = TextoFila.length;
    String[] TextColumna = TextoFila[0].split(",");
    int m = TextColumna.length;
    double[][] MatrizDatos = new double[n][m];
        for (int i= 0 ; i<n ; i++){
            String[] TextoColumna = TextoFila[i].split(",");
            for(int j=0 ; j<m ; j++){
                MatrizDatos[i][j] = Integer.parseInt(TextoColumna[j]);
            }
        }
        
    return (MatrizDatos);
}

    public static void mostrarMatriz(double x[][]){
	for(int i=0;i<x.length;i++){
            for(int j=0;j<x[0].length;j++){
                   System.out.print((int)Math.round(x[i][j])+"\t");
			}
			System.out.print("\n");
		}
    }
    
    public static double[][] trasponerMatriz(int x[][]){
		double[][] y = new double [x.length][x.length];
		for(int i=0;i<y.length;i++){
			for(int j=0;j<y.length;j++){
				y[i][j]=x[j][i];
			}
		}
		return y;
    }
 
    public static double[][] multiplicacion (double [][] ADJ,double D){
   int n= ADJ[0].length;
   int m= ADJ.length;
   double [][] C= new double [m][n];
   if (ADJ[0].length == ADJ.length) {
        for (int i = 0; i < ADJ.length; i++) {
            for (int j = 0; j < ADJ[0].length; j++) {
                    C[i][j] += ADJ[i][j] * (1/D);       
            }
        }
    }
   return C;
} 
     
    private double Determinante(int i, double [][]matriz){      
      if (matriz.length== 2) {
        double deter=matriz[0][0]*matriz[1][1]-matriz[0][1]*matriz[1][0];        
        return  deter;
      }else{
        double deter=0;
        for (int j = 0; j < matriz.length; j++) 
        {
            double[][]temp = this.SubMatriz(i,j,matriz);
      
            deter=deter+Math.pow(-1, i+j)*matriz[i][j]*this.Determinante(0, temp);    
        }         
        return deter;
    }
}    
      
    private double[][]SubMatriz(int i,int j,double [][]matriz){
        double[][]temp=new double[matriz.length-1][matriz.length-1];
     
     int count1=0;
     int count2=0;
     
     for (int k = 0; k < matriz.length; k++) 
     {
         if (k!=i) 
         {
             count2=0;
            for (int l = 0; l < matriz.length; l++) 
            {
                if (l!=j) 
                {
                    temp[count1][count2]=matriz[k][l];
                    
                    count2++;
                }  
            }
            count1++;
         }                
     }
     return temp;
 }
 
    private double [][]AdjuntaMatriz(double [][]matriz){
        double[][]tempAdjunta=new double[matriz.length][matriz.length];
            for (int i = 0; i < tempAdjunta.length; i++) 
        {
            for (int j = 0; j < tempAdjunta.length; j++) 
            {
            double[][]temp  = this.SubMatriz(i, j, matriz) ;
            double elementoAdjunto=Math.pow(-1, i+j)*this.Determinante(0, temp);
            tempAdjunta[j][i]=elementoAdjunto;   
            }   
        }
    return tempAdjunta;
}

    public double [][]TransouestaMatriz(double [][]matriz){
        double[][]tempTranspuesta=new double[matriz.length][matriz.length];   
            for (int i = 0; i < tempTranspuesta.length; i++) 
        {
        for (int j = 0; j < tempTranspuesta.length; j++) 
            {
            tempTranspuesta[i][j]=matriz[j][i];   
            }      
        }
        return tempTranspuesta;
    }     
  
    public static double[][] Codigo (double [][] B, double [][] A){
    int m= A.length;
    int o= B[0].length;
    double [][] C= new double [m][o];
    if (A[0].length == B.length) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
   System.out.print("\n");
   return C;
} 
   
    public static void LectorASCII(double[][]matrizDatos){
    char [] Codigo = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','X','Y','Z','.',',','Á','É','í','ó','"',':','¡','!'};
       
    int [][] Matriz = new int [matrizDatos.length][matrizDatos[0].length];
    
    for(int j=0 ; j<matrizDatos[0].length ; j++){
        for (int i= 0 ; i<matrizDatos.length ; i++){
            
                Matriz[i][j] = (int)Math.round(matrizDatos[i][j]);

                for(int k=0 ; k < Codigo.length ; k++){
                    if(k == Matriz[i][j]){
                    System.out.print(Codigo[k]);
                    }
                }
            }
        } 
    }  
   
}
