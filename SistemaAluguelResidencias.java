import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe Pessoa (para proprietário e inquilino)
class Pessoa {
    String nome;
    int idade;
    String cpf;
    double salarioMensal;

    public Pessoa(String nome, int idade, String cpf, double salarioMensal) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.salarioMensal = salarioMensal;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | CPF: " + cpf + " | Idade: " + idade + " | Salário: R$ " + salarioMensal;
    }
}

// Classe Proprietario (herda de Pessoa)
class Proprietario extends Pessoa {
    List<Residencia> residencias = new ArrayList<>();

    public Proprietario(String nome, int idade, String cpf, double salarioMensal) {
        super(nome, idade, cpf, salarioMensal);
    }

    public void adicionarResidencia(Residencia residencia) {
        if (residencias.size() < 4) {
            residencias.add(residencia);
            System.out.println("Residência adicionada com sucesso!");
        } else {
            System.out.println("O proprietário já possui o máximo de 4 residências.");
        }
    }

    public List<Residencia> getResidencias() {
        return residencias;
    }
}

// Classe Residencia
class Residencia {
    double metragem;
    String posicaoFrente; // Norte, Sul, Leste, Oeste
    boolean esquina;
    Proprietario proprietario;
    Pessoa inquilino;

    public Residencia(double metragem, String posicaoFrente, boolean esquina, Proprietario proprietario) {
        this.metragem = metragem;
        this.posicaoFrente = posicaoFrente;
        this.esquina = esquina;
        this.proprietario = proprietario;
    }

    public void alugarPara(Pessoa inquilino) {
        if (this.inquilino == null) {
            this.inquilino = inquilino;
            System.out.println("Residência alugada para " + inquilino.nome);
        } else {
            System.out.println("A residência já está alugada para " + this.inquilino.nome);
        }
    }

    @Override
    public String toString() {
        return "Residência: " + metragem + " m² | Frente: " + posicaoFrente + " | Esquina: " + (esquina ? "Sim" : "Não");
    }
}

// Classe principal
public class SistemaAluguelResidencias {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Proprietario> proprietarios = new ArrayList<>();
        List<Pessoa> inquilinos = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n---- Menu Principal ----");
            System.out.println("1. Cadastrar Proprietário");
            System.out.println("2. Cadastrar Residência");
            System.out.println("3. Cadastrar Inquilino");
            System.out.println("4. Alugar Residência");
            System.out.println("5. Listar Residências de um Proprietário");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("---- Cadastro de Proprietário ----");
                    System.out.print("Nome: ");
                    String nomeProprietario = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeProprietario = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    System.out.print("CPF: ");
                    String cpfProprietario = scanner.nextLine();
                    System.out.print("Salário Mensal: ");
                    double salarioProprietario = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a nova linha
                    Proprietario proprietario = new Proprietario(nomeProprietario, idadeProprietario, cpfProprietario, salarioProprietario);
                    proprietarios.add(proprietario);
                    System.out.println("Proprietário cadastrado com sucesso!");
                    break;

                case 2:
                    if (proprietarios.isEmpty()) {
                        System.out.println("Nenhum proprietário cadastrado. Cadastre um proprietário primeiro.");
                        break;
                    }
                    System.out.println("---- Cadastro de Residência ----");
                    System.out.print("Metragem: ");
                    double metragem = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a nova linha
                    System.out.print("Posição da Frente (Norte, Sul, Leste, Oeste): ");
                    String posicaoFrente = scanner.nextLine();
                    System.out.print("É de Esquina? (true/false): ");
                    boolean esquina = scanner.nextBoolean();
                    scanner.nextLine();  // Consumir a nova linha
                    System.out.print("CPF do Proprietário: ");
                    String cpfProprietarioResidencia = scanner.nextLine();

                    Proprietario proprietarioEncontrado = null;
                    for (Proprietario p : proprietarios) {
                        if (p.cpf.equals(cpfProprietarioResidencia)) {
                            proprietarioEncontrado = p;
                            break;
                        }
                    }

                    if (proprietarioEncontrado != null) {
                        Residencia residencia = new Residencia(metragem, posicaoFrente, esquina, proprietarioEncontrado);
                        proprietarioEncontrado.adicionarResidencia(residencia);
                    } else {
                        System.out.println("Proprietário não encontrado.");
                    }
                    break;

                case 3:
                    System.out.println("---- Cadastro de Inquilino ----");
                    System.out.print("Nome: ");
                    String nomeInquilino = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeInquilino = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    System.out.print("CPF: ");
                    String cpfInquilino = scanner.nextLine();
                    System.out.print("Salário Mensal: ");
                    double salarioInquilino = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a nova linha
                    Pessoa inquilino = new Pessoa(nomeInquilino, idadeInquilino, cpfInquilino, salarioInquilino);
                    inquilinos.add(inquilino);
                    System.out.println("Inquilino cadastrado com sucesso!");
                    break;

                case 4:
                    if (proprietarios.isEmpty() || inquilinos.isEmpty()) {
                        System.out.println("Nenhum proprietário ou inquilino cadastrado.");
                        break;
                    }
                    System.out.println("---- Alugar Residência ----");
                    System.out.print("CPF do Proprietário: ");
                    String cpfProprietarioAluguel = scanner.nextLine();
                    System.out.print("Número da Residência do Proprietário (0 a 3): ");
                    int numResidencia = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    System.out.print("CPF do Inquilino: ");
                    String cpfInquilinoAluguel = scanner.nextLine();

                    Proprietario proprietarioParaAlugar = null;
                    for (Proprietario p : proprietarios) {
                        if (p.cpf.equals(cpfProprietarioAluguel)) {
                            proprietarioParaAlugar = p;
                            break;
                        }
                    }

                    Pessoa inquilinoParaAlugar = null;
                    for (Pessoa i : inquilinos) {
                        if (i.cpf.equals(cpfInquilinoAluguel)) {
                            inquilinoParaAlugar = i;
                            break;
                        }
                    }

                    if (proprietarioParaAlugar != null && inquilinoParaAlugar != null) {
                        if (numResidencia >= 0 && numResidencia < proprietarioParaAlugar.getResidencias().size()) {
                            Residencia residenciaParaAlugar = proprietarioParaAlugar.getResidencias().get(numResidencia);
                            residenciaParaAlugar.alugarPara(inquilinoParaAlugar);
                        } else {
                            System.out.println("Residência inválida.");
                        }
                    } else {
                        System.out.println("Proprietário ou Inquilino não encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("---- Listar Residências de um Proprietário ----");
                    System.out.print("CPF do Proprietário: ");
                    String cpfProprietarioListar = scanner.nextLine();

                    Proprietario proprietarioListar = null;
                    for (Proprietario p : proprietarios) {
                        if (p.cpf.equals(cpfProprietarioListar)) {
                            proprietarioListar = p;
                            break;
                        }
                    }
                    if (proprietarioListar != null) {
                        System.out.println("Residências do proprietário " + proprietarioListar.nome + ":");
                        for (Residencia residencia : proprietarioListar.getResidencias()) {
                            System.out.println(residencia);
                        }
                        for (Residencia residencia : proprietarioListar.getResidencias()) {
                            System.out.println(residencia);
                        }
                    } else {
                        System.out.println("Proprietário não encontrado.");
                    }
                    break;

                case 6:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 6);

        scanner.close();
    }
}

