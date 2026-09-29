import java.util.*;

public class ClinicaPetMain {
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Pessoa> pessoas = new ArrayList<>();
    private static final List<Animal> animais = new ArrayList<>();
    private static final List<Consulta> consultas = new ArrayList<>();

    public static void main(String[] args) {
        seedData();
        boolean running = true;
        while (running) {
            showMenu();
            int opc = readInt("Escolha uma opção");
            switch (opc) {
                case 1: cadastrarPessoa(Pessoa.Role.PROPRIETARIO); break;
                case 2: ListarPessoas(Pessoa.Role.PROPRIETARIO); break;
                case 3: atualizarPessoa(Pessoa.Role.PROPRIETARIO); break;
                case 4: removerPessoa(Pessoa.Role.PROPRIETARIO); break;
                case 5: cadastrarPessoa(Pessoa.Role.VETERINARIO); break;
                case 6: ListarPessoas(Pessoa.Role.VETERINARIO); break;
                case 7: atualizarPessoa(Pessoa.Role.VETERINARIO); break;
                case 8: removerPessoa(Pessoa.Role.VETERINARIO); break;
                case 9: cadastrarAnimal(); break;
                case 10: listarAnimais(); break;
                case 11: atualizarAnimal(); break;
                case 12: removerAnimal(); break;
                case 13: agendarConsulta(); break;
                case 14: listarConsultas(); break;
                case 15: atualizarConsulta(); break;
                case 16: removerConsulta(); break;
                case 0: {System.out.println("Encerrando..."); running = false;} break;
                default: System.out.println("Opção inválida.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("=== Clínica Pet - Menu ===");
        System.out.println("1. Cadastrar Proprietários");
        System.out.println("2. Listar Proprietários");
        System.out.println("3. Atualizar Proprietários");
        System.out.println("4. Remover Proprietário");
        System.out.println("5. Cadastrar Veterinário");
        System.out.println("6. Listar Veterinário");
        System.out.println("7. Atualizar Veterinário");
        System.out.println("8. Remover Veterinário");
        System.out.println("9. Cadastrar Animal");
        System.out.println("10. Listar Animais");
        System.out.println("11. Atualizar Animal");
        System.out.println("12. Remover Animal");
        System.out.println("13. Agendar Consulta");
        System.out.println("14. Listar Consultas");
        System.out.println("15. Atualizar Consulta");
        System.out.println("16. Remover Consulta");
        System.out.println("0. Sair");
    }

    private static void cadastrarPessoa(Pessoa.Role role) {
        System.out.println("=== Cadastrar" + role + " ===");
        String nome = readLine("Nome: ");
        String tel = readLine("Telefone: ");
        Pessoa p = new Pessoa(nome, tel, role);
        pessoas.add(p);
        System.out.println("Cadastro:" + p);
    }


    private static void ListarPessoas(Pessoa.Role role) {
        System.out.println("=== Listando" + role + "s ===");
        pessoas.stream().filter(p -> p.getRole() == role).forEach(System.out::println);
    }

    private static void atualizarPessoa(Pessoa.Role role) {
        System.out.println("=== Atualizar" + role + " ===");
        ListarPessoas(role);
        int id = readInt("ID para atualizar: ");
        Pessoa p = findPessoaByIdAndRole(id, role);
        if (p == null) { System.out.println("Não encontrado"); return; }
        String novoNome = readLine("Novo nome (enter para manter " + p.getNome() + "): ");
        if (!novoNome.isBlank()) p.setNome(novoNome);
        String novoTel = readLine("Novo telefone (Enter para manter" + p.getTelefone() + "): ");
        if (!novoTel.isBlank()) p.setTelefone(novoTel);
        System.out.println("Atualizado:" + p);
    }

    private static void removerPessoa(Pessoa.Role role) {
        System.out.println("=== Remover" + role + " ===");
        ListarPessoas(role);
        int id = readInt("ID para Remover: ");
        Pessoa p = findPessoaByIdAndRole(id, role);
        if (p == null) { System.out.println("Não encontrado"); return; }
        boolean temAnimais = animais.stream().anyMatch(a -> a.getProprietario() != null && a.getProprietario().getId() == p.getId());
        boolean temConsultas = consultas.stream().anyMatch(c -> (c.getVeterinario()!=null && c.getVeterinario().getId() == p.getId()));
        if (temAnimais || temConsultas) {
            System.out.println("Aviso: essa pessoa possui associações (animais ou consultas). Remoção cancelada.");
            return;
        }
        pessoas.remove(p);
        System.out.println("Removido:" + p);
    }

    private static Pessoa findPessoaByIdAndRole(int id, Pessoa.Role role) {
        return pessoas.stream().filter(p -> p.getId() == id && p.getRole() == role).findFirst().orElse(null);
    }

    private static void cadastrarAnimal() {
        System.out.println("== Cadastrar Animal ==");
        String nome = readLine("Nome do animal: ");
        int idade = readInt("Idade: ");
        String especie = readLine("Espécie (Cachorro / Gato): ");

        System.out.println("Escolha o proprietario: ");
        ListarPessoas(Pessoa.Role.PROPRIETARIO);
        int ownerId = readInt("ID do proprietário: ");
        Pessoa dono = findPessoaByIdAndRole(ownerId, Pessoa.Role.PROPRIETARIO);
        if (dono == null) { System.out.println("Proprietário invalido. Cancele e cadastre o proprietário primeiro."); return; }

        Animal ani;
        if (especie.equalsIgnoreCase("Cachorro") || especie.equalsIgnoreCase("cão") || especie.equalsIgnoreCase("cao")) {
            String raca = readLine("Raça do Cachorro: ");
            ani = new Cachorro(nome, idade, "Cachorro", dono, raca);
        } else  if (especie.equalsIgnoreCase("Gato") || especie.equalsIgnoreCase("gato")) {
            boolean gosta = readLine("Gosta de brincar? (S/N): ").trim().equalsIgnoreCase("S");
            ani = new Gato(nome, idade, "Gato", dono, gosta);
        } else {
            ani = new Cachorro(nome, idade, especie, dono, "Indefinido");
        }
        animais.add(ani);
        System.out.println("Animal cadastrado:" + ani);
    }

    private static void listarAnimais() {
        System.out.println("== Listando Animais ==");
        animais.forEach(a -> {
            System.out.println(a);
            System.out.println("Som: " + a.emitirSom());
        });
    }

    private static void atualizarAnimal() {
        System.out.println("== Atualizar Animal==");
        listarAnimais();
        int id = readInt("ID para Atualizar: ");
        Animal a = findAnimalById(id);
        if (a != null) {System.out.println("Animal não encontrado."); return; }
        String novoNome = readLine("Novo nome (enter para manter " + a.getNome() + ")");
        if (!novoNome.isBlank()) a.setNome(novoNome);
        String novaIdade = readLine("Nova idade (enter para manter" + a.getIdade() + ")");
        if (!novaIdade.isBlank()) a.setIdade(Integer.parseInt(novaIdade));
        System.out.println("Atualizado:" + a);
    }

    private static void removerAnimal() {
        System.out.println("== Remover Animal==");
        listarAnimais();
        int id = readInt("ID para remover: ");
        Animal a = findAnimalById(id);
        if (a == null) {System.out.println("Animal não encontrado."); return; }
        boolean hasConsulta = consultas.stream().anyMatch(c -> c.getAnimal().getId() == a.getId());
        if (hasConsulta) {System.out.println("Animal possui consultas agendadas. Remova as consultas antes."); return; }
        animais.remove(a);
        System.out.println("Animal removido:" + a);
    }

    private static Animal findAnimalById(int id) {
        return animais.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    private static void agendarConsulta() {
        System.out.println("== Agendar Consulta==");
        System.out.println("Lista de Veterinários: ");
        ListarPessoas(Pessoa.Role.VETERINARIO);
        int vetId = readInt("ID do veterinário: ");
        Pessoa vet = findPessoaByIdAndRole(vetId, Pessoa.Role.VETERINARIO);
        if (vet == null) {System.out.println("Veterinário inválido"); return;}

        System.out.println("Lista de Animais: ");
        listarAnimais();
        int animalId = readInt("ID do animal: ");
        Animal ani = findAnimalById(animalId);
        if (ani == null) {System.out.println("Animal inexistente"); return;}

        String dataHora = readLine("Data e hora (ex: 2025-11-07 14:00 ");
        String desc = readLine("Descrição: ");
        Consulta c = new Consulta(dataHora, vet, ani, desc);
        consultas.add(c);
        System.out.println("Consulta agendada:" + c);
    }

    private static void listarConsultas() {
        System.out.println("== Listar Consultas==");
        consultas.forEach(System.out::println);
    }

    private static void atualizarConsulta() {
        System.out.println("== Atualizar Consulta==");
        listarConsultas();
        int id = readInt("ID da consulta para atualizar: ");
        Consulta c = consultas.stream().filter(cc -> cc.getId() == id).findFirst().orElse(null);
        if (c == null) {System.out.println("Consulta não encontrada"); return;}
        String novaData = readLine("Nova data/hora (enter para manter " + c.getDataHora() + "):" );
        if (!novaData.isBlank()) c.setDataHora(novaData);
        String novaDesc = readLine("Nova descrição (enter para manter): ");
        if (!novaDesc.isBlank()) c.setDescricao(novaDesc);
        System.out.println("Atualizada:" + c);
    }

    private static void removerConsulta() {
        System.out.println("== Remover Consulta==");
        listarConsultas();
        int id = readInt("ID para remover: ");
        Consulta c = consultas.stream().filter(cc -> cc.getId() == id).findFirst().orElse(null);
        if (c == null) {System.out.println("Consulta inexistente"); return;}
        consultas.remove(c);
        System.out.println("Consulta removida:" + c);
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static void seedData() {
        Pessoa dono1 = new Pessoa("João", "99-9999-0001", Pessoa.Role.PROPRIETARIO);
        Pessoa dono2 = new Pessoa("Maria", "99-9999-0002", Pessoa.Role.PROPRIETARIO);
        Pessoa vet1 = new Pessoa("Dra. Ana", "99-9999-0003", Pessoa.Role.VETERINARIO);
        pessoas.addAll(Arrays.asList(dono1, dono2, vet1));

        Animal a1 = new Cachorro("Rex", 4, "Cachorro", dono1, "Vira-lata");
        Animal a2 = new Gato("Mimi", 2, "Gato", dono2, true);
        animais.addAll(Arrays.asList(a1, a2));

        Consulta c1 = new Consulta("2025-11-10 09:00", vet1, a1, "Vacinação");
        consultas.add(c1);
    }
}