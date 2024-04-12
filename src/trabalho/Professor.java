package trabalho;

import java.io.Serializable;
import java.util.ArrayList;

public class Professor implements Serializable {

    private String nome;
    private int numero;
    private String genero;
    private String cidade;
    private ArrayList<Curso> listaC;

    public Professor() {
        nome = "";
        numero = 0;
        genero = "";
        cidade = "";
        listaC = new ArrayList<>();
    }

    public Professor(Professor p) {
        this.nome = p.nome;
        this.numero = p.numero;
        this.genero = p.genero;
        this.cidade = p.cidade;
        this.listaC = (ArrayList<Curso>) p.listaC.clone();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public ArrayList<Curso> getListaC() {
        return listaC;
    }

    public void setListaC(ArrayList<Curso> listaC) {
        this.listaC = (ArrayList<Curso>) listaC.clone();
    }

    @Override
    public String toString() {
        return "Professor{" + "nome=" + nome + ", genero=" + genero + ", cidade=" + cidade + ", listaC=" + listaC + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && this.getClass() == obj.getClass()) {
            Professor p = (Professor) obj;
            boolean eq;
            eq = this.nome.equals(p.nome) && this.genero.equals(p.genero) && this.cidade.equals(p.cidade) && this.listaC.equals(p.listaC);
            return eq;
        }
        return false;
    }

    @Override
    public Object clone() {
        Professor copia = new Professor(this);
        return copia;
    }

    public void addCurso(Curso c) {
        listaC.add(c);
    }
    
    public void removeCurso(Curso c) {
        listaC.remove(c);
    }
    
    public boolean jaLeciona(Curso c) {
        for (Curso singular : listaC) {
            if (singular.getNome().equals(c.getNome())) {
                return true;
            }
        }
        return false;
    }
}