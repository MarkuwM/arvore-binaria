import java.util.*;

public class ArvoreBinaria {
    private No raiz;

    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No atual, int valor) {
        if (atual == null) return new No(valor);
        if (valor < atual.valor) atual.esquerdo = inserirRec(atual.esquerdo, valor);
        else if (valor > atual.valor) atual.direito = inserirRec(atual.direito, valor);
        return atual;
    }

    public No buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private No buscarRec(No atual, int valor) {
        if (atual == null || atual.valor == valor) return atual;
        return valor < atual.valor ? buscarRec(atual.esquerdo, valor) : buscarRec(atual.direito, valor);
    }

    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    private No removerRec(No atual, int valor) {
        if (atual == null) return null;

        if (valor < atual.valor) {
            atual.esquerdo = removerRec(atual.esquerdo, valor);
        } else if (valor > atual.valor) {
            atual.direito = removerRec(atual.direito, valor);
        } else {
            if (atual.esquerdo == null) return atual.direito;
            if (atual.direito == null) return atual.esquerdo;
            No sucessor = encontrarMenor(atual.direito);
            atual.valor = sucessor.valor;
            atual.direito = removerRec(atual.direito, sucessor.valor);
        }
        return atual;
    }

    private No encontrarMenor(No atual) {
        while (atual.esquerdo != null) atual = atual.esquerdo;
        return atual;
    }

    public List<No> emOrdem() {
        List<No> resultado = new ArrayList<>();
        emOrdemRec(raiz, resultado);
        return resultado;
    }

    private void emOrdemRec(No atual, List<No> resultado) {
        if (atual != null) {
            emOrdemRec(atual.esquerdo, resultado);
            resultado.add(atual);
            emOrdemRec(atual.direito, resultado);
        }
    }

    public List<No> preOrdem() {
        List<No> resultado = new ArrayList<>();
        preOrdemRec(raiz, resultado);
        return resultado;
    }

    private void preOrdemRec(No atual, List<No> resultado) {
        if (atual != null) {
            resultado.add(atual);
            preOrdemRec(atual.esquerdo, resultado);
            preOrdemRec(atual.direito, resultado);
        }
    }

    public List<No> posOrdem() {
        List<No> resultado = new ArrayList<>();
        posOrdemRec(raiz, resultado);
        return resultado;
    }

    private void posOrdemRec(No atual, List<No> resultado) {
        if (atual != null) {
            posOrdemRec(atual.esquerdo, resultado);
            posOrdemRec(atual.direito, resultado);
            resultado.add(atual);
        }
    }

    public List<No> caminhoDFS(int valor) {
        List<No> caminho = new ArrayList<>();
        dfs(raiz, valor, caminho);
        return caminho;
    }

    private boolean dfs(No atual, int valor, List<No> caminho) {
        if (atual == null) return false;
        caminho.add(atual);
        if (atual.valor == valor) return true;
        if (dfs(atual.esquerdo, valor, caminho) || dfs(atual.direito, valor, caminho)) return true;
        caminho.remove(caminho.size() - 1);
        return false;
    }

    public List<No> caminhoBFS(int valor) {
        List<No> caminho = new ArrayList<>();
        if (raiz == null) return caminho;

        Queue<No> fila = new LinkedList<>();
        Map<No, No> pais = new HashMap<>();
        fila.add(raiz);
        pais.put(raiz, null);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            caminho.add(atual);
            if (atual.valor == valor) break;
            if (atual.esquerdo != null) {
                fila.add(atual.esquerdo);
                pais.put(atual.esquerdo, atual);
            }
            if (atual.direito != null) {
                fila.add(atual.direito);
                pais.put(atual.direito, atual);
            }
        }
        return caminho;
    }

    public No getRaiz() {
        return raiz;
    }
}
