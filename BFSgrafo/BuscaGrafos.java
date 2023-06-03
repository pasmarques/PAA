import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BuscaGrafos {
  public static ArrayList<Character> BFS(int nVertices, int adj[][], int verticeRaiz, ArrayList<Double>d) {

    ArrayList<Character> cores = new ArrayList<Character>();//cria lista de cores
    Queue<Integer> fila = new LinkedList<>();//cria a fila pra pegar vertice e olhar os adjacentes

    for (int i = 0; i < nVertices; i++){
      cores.add('B'); //preenche como 'B' para indicar nenhuma visita efetuada
      d.add(Double.POSITIVE_INFINITY);//seta vetor de distancia com infinito
    }
    cores.set(verticeRaiz, 'C');//indica visitação no vertice de partida
    d.set(verticeRaiz,0.0);//distancia ate o vertice raiz=0, ele é a partida
    fila.add(verticeRaiz);//adiciona vertice na fila pra olhar todos os adjacentes a ele

    while (!fila.isEmpty()) {
      int u = fila.remove();
      for (int j = 0; j < nVertices; j++) {
        if (adj[u][j] == 1) {//verifica se a algum vertice é adjacente ao atual
          if (cores.get(j) == 'B') {// e algum vertice adjacente for branco pinta visita
            cores.set(j, 'C');//visitou o adjacente
            fila.add(j);//empilha os adjacentes ao vertice visitado anterior(u) na fila
            d.set(j,d.get(u)+1.0);//altera o vetor distancia
          }
        }
      }
      cores.set(u, 'P');//indica que olhou todos os adjacentes ao vertice
    }
    return cores;//retorna lista de cores
  }
  public static ArrayList<Character> DFS(int nVertices, int adj[][]) {
    ArrayList<Character> cores = new ArrayList<>();
    ArrayList<Double> predecessores = new ArrayList<>();
    for (int i = 0; i < nVertices; i++) {
      cores.add('B');//nenhum vertice visitado
      predecessores.add(Double.POSITIVE_INFINITY);//nenhum vertice com predecessor
    }
    //inicia a visita aos vertices em branco
    for (int i = 0; i < nVertices; i++) {
      if (cores.get(i) == 'B') {
        visite(cores, predecessores, adj, i);//visita todos os vertices
      }
    }
    return cores;//retorna o resultado da alteração do vetor feita ao longo das visitas
  }

  private static void visite(ArrayList<Character> cores, ArrayList<Double> predecessores, int adj[][],
      int verticeRaiz) {
    cores.set(verticeRaiz, 'C');//seta o vertice raiz como visitacao atual
    for (int j = 0; j < cores.size(); j++) {//recebe o vetor de cores e pred inicais
      if (adj[verticeRaiz][j] == 1) {//verifica os adjacentes ao vertice  raiz
        if (cores.get(j) == 'B') {//se algum dos adjacentes nao foi visitado
          predecessores.set(j, verticeRaiz/1.0);//torna o vertice raiz o "pai" dos adjacentes a ele
          visite(cores, predecessores, adj, j);//visita os vertices adjacentes ao "filho" de cada chamada
        }
      }
    }
    cores.set(verticeRaiz, 'P');//finaliza preenchendo a cor preta
  }

}