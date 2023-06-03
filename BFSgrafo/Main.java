import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
  public static void main(String args[]) throws FileNotFoundException {
    File arq = new File("adj.txt");
    Scanner lerArq = new Scanner(arq);
    int nVertices = lerArq.nextInt();//ler a primeira linha do arquivo contendo nVertices
    int matrizAdjacencia[][] = new int[nVertices][nVertices];
    //preenche matriz lendo do arquivo
    for (int i = 0; i < nVertices; i++) {
      for (int j = 0; j < nVertices; j++) {
        matrizAdjacencia[i][j] = lerArq.nextInt();
      }
    }
    final ArrayList<Double> distancia = new ArrayList<>();//lista de distancia
    //chama a busca em largura pra preencher vetor de cores e vetor distancia
    ArrayList<Character> coresBFS = BuscaGrafos.BFS(nVertices, matrizAdjacencia, 0, distancia);
    System.out.print("Busca em largura:");
    System.out.println(coresBFS.toString());//imprime BFS
    //chama a busca em profundidade pra preencher vetor de cores
    ArrayList<Character> coresDFS = BuscaGrafos.DFS(nVertices, matrizAdjacencia);
    System.out.print("Busca em profundidade:");
    System.out.println(coresDFS.toString());//imprime DFS
    System.out.print("Vetor distancia:");
    /*percorre o a lista Double de distancia para converter os valores diferentes
    de infinito para Integer*/ 
    System.out.print("[");
    for (int i = 0; i < distancia.size(); i++) {
      Integer d;
      if (distancia.get(i) == Double.POSITIVE_INFINITY) {
        System.out.print(distancia.get(i) + "," + " ");
      } else if (i < distancia.size() - 1) {
        d = distancia.get(i).intValue();
        System.out.print(d + "," + " ");
      } else if (i == distancia.size() - 1) {
        d = distancia.get(i).intValue();
        System.out.print(d + "]");
      }
    }
    System.out.println();
    //fecha leitor do arquivo
    lerArq.close();

  }
}
