import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal {

    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int id;
    private String nome;
    private int idade;
    private String especie;
    private Pessoa proprietario;

    public Animal(String nome, int idade, String especie, Pessoa proprietario) {
        this.id = COUNTER.getAndIncrement();
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.proprietario = proprietario;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public Pessoa getProprietario() { return proprietario; }
    public void setProprietario(Pessoa proprietario) { this.proprietario = proprietario; }

    public abstract String emitirSom();

    @Override
    public String toString() {
        String dono = (proprietario != null)
                ? proprietario.getNome() + " (id=" + proprietario.getId() + ")"
                : "Sem proprietário";

        return String.format("%s[id=%d, nome=%s, idade=%d, especie=%s, dono=%s]",
                this.getClass().getSimpleName(), id, nome, idade, especie, dono);
    }
}
