package trabalho;

import java.io.Serializable;

public class Notas implements Serializable {
    private Curso c;
    private Aluno a;
    private double nota;
    
    public Notas() {
        this.c = null;
        this.a = null;
        this.nota = 0;
    }
    
    public Notas(Curso c, Aluno a, int nota) {
        this.c = c;
        this.a = a;
        this.nota = nota;
    }
    
    public Notas(Notas n) {
        this.c = n.c;
        this.a = n.a;
        this.nota = n.nota;
    }

    public Curso getC() {
        return c;
    }

    public void setC(Curso c) {
        this.c = c;
    }
    
    public Aluno getA() {
        return a;
    }

    public void setA(Aluno a) {
        this.a = a;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Notas{" + "c=" + c + ", a=" + a + ", nota=" + nota + '}';
    }

    public String toString2() {
        return "Curso: " + c.getNome() + ", Nome: " + a.getNome() + " (Numero: " + a.getNumero() + "), Nota: " + nota;
    }
    
}
