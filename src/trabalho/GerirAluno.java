package trabalho;

import java.util.ArrayList;
import myinputs.Ler;
import static trabalho.Main.guardaFich;

public class GerirAluno {

    public static ArrayList<Aluno> procuraAluno(ArrayList<Aluno> listaA) {
        String nome_aluno = Ler.umaString().toLowerCase();
        ArrayList<Aluno> devolve = new ArrayList<>();
        for (Aluno aluno : listaA) {
            if (aluno.getNome().toLowerCase().equals(nome_aluno)) {
                devolve.add(aluno);
            }
        }
        return devolve;
    }

    public static void menuModificarAluno(Aluno a) {
        int op;
        do {
            System.out.println(a);
            System.out.println("1 - Modificar nome;");
            System.out.println("2 - Modificar número;");
            System.out.println("3 - Modificar idade;");
            System.out.println("4 - Modificar localidade;");
            System.out.println("5 - Modificar género");
            System.out.println("0 - Voltar atrás;");
            op = Ler.umInt();
            switch (op) {
                case 0:
                    break;
                case 1:
                    System.out.print("Novo nome: ");
                    a.setNome(Ler.umaString());
                    break;
                case 2:
                    System.out.print("Novo número: ");
                    a.setNumero(Ler.umInt());
                    break;
                case 3:
                    int idade = 19;
                    do {
                        if (idade < 18) {
                            System.out.println("Insira idade acima de 18 anos");
                        }
                        System.out.print("Insira a idade do aluno: ");
                        idade = Ler.umInt();
                    } while (idade < 18);
                    a.setIdade(idade);
                    break;
                case 4:
                    System.out.print("Novo local de residência: ");
                    a.setCidade(Ler.umaString());
                    break;
                case 5:
                    while (true) {
                        System.out.println("Novo género (M - masculino F - feminino): ");
                        String s = Ler.umaString().toUpperCase();
                        if (s.equals("M") || s.equals("F")) {
                            a.setGenero(s);
                            break;
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (op != 0);
    }

    public static Aluno adicionarAluno(ArrayList<Aluno> listaA) {
        Aluno a = new Aluno();
        System.out.print("Nome do aluno: ");
        a.setNome(Ler.umaString());

        boolean continuacao = true;
        while (continuacao) {
            continuacao = false;
            System.out.print("Número do aluno: ");
            int numero_aluno = Ler.umInt();
            for (int i = 0; i < listaA.size(); i++) {
                if (listaA.get(i).getNumero() == numero_aluno) {
                    continuacao = true;
                }
            }
            if (continuacao) {
                System.out.println("Número já registado!");
            } else {
                a.setNumero(numero_aluno);
            }
        }

        int idade = 19;
        do {
            if (idade < 18) {
                System.out.println("Insira idade acima de 18 anos");
            }
            System.out.print("Idade do aluno: ");
            idade = Ler.umInt();
        } while (idade < 18);
        a.setIdade(idade);

        System.out.print("Local de residência: ");
        a.setCidade(Ler.umaString());

        while (true) {
            System.out.println("Género do aluno (M - masculino F - feminino): ");
            String s = Ler.umaString().toUpperCase();
            if (s.equals("M") || s.equals("F")) {
                a.setGenero(s);
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }

        return a;
    }

    public static void listarAluno(ArrayList<Aluno> listaA) {
        if (!listaA.isEmpty()) {
            for (Aluno aluno : listaA) {
                System.out.println(aluno);
            }
        } else {
            System.out.println("Não existem alunos registados");
        }
    }

    public static void procurarAluno(ArrayList<Aluno> listaA) {
        System.out.print("Introduza o nome do aluno que procura: ");
        ArrayList<Aluno> procura = procuraAluno(listaA);
        if (!procura.isEmpty()) {
            for (Aluno aluno : procura) {
                System.out.println(aluno);
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    public static void modificarAluno(ArrayList<Aluno> listaA) {
        System.out.print("Introduza o nome do aluno que deseja modificar: ");
        ArrayList<Aluno> modificar = procuraAluno(listaA);
        boolean continuacao = true;
        if (!modificar.isEmpty()) {
            if (modificar.size() > 1) {
                System.out.println("Conflito! Qual deles modificar? (Ex: 1)");
                for (Aluno aluno : modificar) {
                    System.out.println(aluno);
                }
                while (continuacao) {
                    try {
                        menuModificarAluno(modificar.get(Ler.umInt() - 1));
                        continuacao = false;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insira a posição do aluno (1 - " + modificar.size() + ")");
                    }
                }
            } else {
                menuModificarAluno(modificar.get(0));
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    public static void removerAluno(ArrayList<Aluno> listaA, ArrayList<Curso> listaC, ArrayList<Notas> listaN) {
        System.out.print("Introduza o nome do aluno que deseja remover: ");
        ArrayList<Aluno> remover = procuraAluno(listaA);
        if (!remover.isEmpty()) {
            int op = 0;
            if (remover.size() > 1) {
                System.out.println("Conflito, alunos com o mesmo nome!");
                do {
                    for (Aluno singular : remover) {
                        System.out.println(singular);
                    }
                    if (!(op < 0 || op >= remover.size())) {
                        System.out.print("Qual deles inscrever? (Ex: 1): ");
                    } else {
                        System.out.println("Insira a posição do aluno (1 - " + remover.size() + ")");
                    }
                    op = Ler.umInt() - 1;
                } while (op < 0 || op >= remover.size());
                
                listaA.remove(remover.get(op));
                for (Curso curso : listaC) {
                    curso.getListaA().remove(remover.get(op));
                }
            } else {
                listaA.remove(remover.get(0));
                for (Curso curso : listaC) {
                    curso.getListaA().remove(remover.get(0));
                }
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    public static void inscreverAluno(ArrayList<Aluno> listaA, ArrayList<Curso> listaC) {
        System.out.print("Introduza o nome do aluno que deseja increver: ");
        ArrayList<Aluno> inscrever = procuraAluno(listaA);
        if (!(inscrever.isEmpty() || listaC.isEmpty())) {
            Aluno aluno = null;
            int op = 0;
            if (inscrever.size() > 1) {
                System.out.println("Conflito, alunos com o mesmo nome!");
                do {
                    for (Aluno singular : inscrever) {
                        System.out.println(singular);
                    }
                    if (!(op < 0 || op >= inscrever.size())) {
                        System.out.print("Qual deles inscrever? (Ex: 1): ");
                    } else {
                        System.out.println("Insira a posição do aluno (1 - " + inscrever.size() + ")");
                    }
                    op = Ler.umInt() - 1;
                } while (op < 0 || op >= inscrever.size());
                aluno = inscrever.get(op);
                op = 1;
            }

            System.out.println("Em que curso vai ser inscrito?");
            do {
                for (Curso singular : listaC) {
                    System.out.println(singular.toString2());
                }
                if (!(op < 0 || op >= listaC.size())) {
                    System.out.print("Qual deles inscrever? (Ex: 1): ");
                } else {
                    System.out.println("Insira a posição do curso (1 - " + listaC.size() + ")");
                }
                op = Ler.umInt() - 1;
            } while (op < 0 || op >= listaC.size());

            if (inscrever.size() > 1) {
                if (!listaC.get(op).jaInscrito(aluno)) {
                    listaC.get(op).addAluno(aluno);
                } else {
                    System.out.println("Aluno já inscrito");
                }
            } else {
                if (!listaC.get(op).jaInscrito(inscrever.get(0))) {
                    listaC.get(op).addAluno(inscrever.get(0));
                } else {
                    System.out.println("Aluno já inscrito");
                }
            }
        } else {
            if (listaC.isEmpty()) {
                System.out.println("Não existem cursos registados!");
            } else {
                System.out.println("Aluno não encontrado!");
            }
        }
    }

    public static void menuGerirAlunos(Main main) {
        int op;
        do {
            System.out.println("1 - Adicionar aluno;");
            System.out.println("2 - Listar alunos;");
            System.out.println("3 - Procurar aluno;");
            System.out.println("4 - Modificar aluno;");
            System.out.println("5 - Remover aluno;");
            System.out.println("6 - Inscrever aluno");
            System.out.println("0 - Voltar atrás.");
            op = Ler.umInt();
            switch (op) {
                case 0:
                    break;
                case 1:
                    Aluno a = adicionarAluno(main.listaA);
                    main.listaA.add(a);
                    break;
                case 2:
                    listarAluno(main.listaA);
                    break;
                case 3:
                    procurarAluno(main.listaA);
                    break;
                case 4:
                    modificarAluno(main.listaA);
                    break;
                case 5:
                    removerAluno(main.listaA, main.listaC, main.listaN);
                    break;
                case 6:
                    inscreverAluno(main.listaA, main.listaC);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            guardaFich(main, "Main.txt");
        } while (op != 0);
    }
}
