import java.util.concurrent.atomic.AtomicInteger;

public class Consulta {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private int id;
    private String dataHora;
    private Pessoa veterinario;
    private Animal animal;
    private String descricao;

    public Consulta(String dataHora, Pessoa veterinario,  Animal animal, String descricao) {
        this.id = COUNTER.getAndIncrement();
        this.dataHora = dataHora;
        this.veterinario = veterinario;
        this.animal = animal;
        this.descricao = descricao;

    }

    public int getId() { return id; }
    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
    public Pessoa getVeterinario() { return veterinario; }
    public void setVeterinario(Pessoa veterinario) {this.veterinario = veterinario; }
    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return String.format("Consulta[id=%d, dataHora=%s, vet=%s(id=%d), animal=%s(id=%d), desc=%s]",
                id, dataHora, veterinario.getNome(),veterinario.getId(), animal.getNome(), animal.getId(), descricao);
    }
}