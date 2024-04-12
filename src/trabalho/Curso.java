package trabalho;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Curso implements Serializable {

    private int num;
    private int horas;
    private String nome;
    private LocalDate data_inicio;
    private LocalDate data_fim;
    private ArrayList<Aluno> listaA;

    public Curso() {
        num = 0;
        horas = 0;
        nome = "";
        listaA = new ArrayList<>();
        data_inicio = LocalDate.of(1970, 01, 01);
        data_fim = LocalDate.of(1970, 01, 01);
    }

    public Curso(Curso c) {
        this.num = c.num;
        this.horas = c.horas;
        this.nome = c.nome;
        this.listaA = (ArrayList<Aluno>) c.listaA.clone();
        this.data_inicio = c.data_inicio;
        this.data_fim = c.data_fim;
    }

    public int getHoras() {
        return horas;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Aluno> getListaA() {
        return listaA;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data) {
        this.data_inicio = LocalDate.parse(data);
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data) {
        this.data_fim = LocalDate.parse(data);
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setListaA(ArrayList<Aluno> listaA) {
        this.listaA = (ArrayList<Aluno>) listaA.clone();
    }

    @Override
    public String toString() {
        return "Curso{" + "num=" + num + ", horas=" + horas + ", nome=" + nome + ", data_inicio=" + data_inicio + ", data_fim=" + data_fim + ", listaA=" + listaA + '}';
    }

    public String toString2() {
        String s = "Curso{" + "num=" + num + ", horas=" + horas + ", nome=" + nome + ", data_inicio=" + data_inicio + ", data_fim=" + data_fim + ", listaA=[";
        if (listaA.isEmpty()) {
            s += "]}";
        }
        else if (listaA.size() == 1) {
            s += listaA.get(0).getNome() + "]}";
        }
        else {
            for (int i = 0; i < listaA.size() - 1; i++) {
                s += listaA.get(i).getNome() + ", ";
            }
            s += listaA.get(listaA.size() - 1).getNome() + "]}";
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (this.getClass() == obj.getClass())) {
            Curso c = (Curso) obj;
            boolean eq;
            eq = this.nome.equals(c.nome) && this.horas == c.horas && listaA.equals(c.listaA)
                    && this.num == c.num && this.data_inicio.equals(c.data_inicio) && this.data_fim.equals(c.data_fim);
            return eq;
        }
        return false;
    }

    @Override
    public Object clone() {
        Curso copia = new Curso(this);
        return copia;
    }

    public void addAluno(Aluno a) {
        listaA.add(a);
    }
    
    public void removeAluno(Aluno a) {
        listaA.remove(a);
    }
    
    public boolean jaInscrito(Aluno a) {
        for (Aluno singular : listaA) {
            if (singular.getNumero() == a.getNumero()) {
                return true;
            }
        }
        return false;
    }
}
