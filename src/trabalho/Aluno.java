package trabalho;

import java.io.Serializable;

public class Aluno implements Serializable {

    private String nome;
    private int numero;
    private int idade;
    private String cidade;
    public String genero;

    public Aluno() {
        nome = "";
        numero = 0;
        idade = 0;
        cidade = "";
        genero = "";
    }

    public Aluno(Aluno a) {
        this.nome = a.nome;
        this.numero = a.numero;
        this.idade = a.idade;
        this.cidade = a.cidade;
        this.genero = a.genero;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }
    
    public int getIdade() {
        return idade;
    }

    public String getCidade() {
        return cidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Aluno{" + "nome=" + nome + ", numero=" + numero + ", idade=" + idade + ", cidade=" + cidade + ", genero=" + genero + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (this.getClass() == obj.getClass())) {
            Aluno a1 = (Aluno) obj;
            boolean eq;
            eq = this.nome.equals(a1.nome) && this.numero == a1.numero && this.idade == a1.idade
                    && this.cidade.equals(a1.cidade) && this.genero.equals(a1.genero);
            return eq;
        }
        return false;
    }

    @Override
    public Object clone() {
        Aluno copia = new Aluno(this);
        return copia;
    }
}
