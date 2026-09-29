import java.util.concurrent.atomic.AtomicInteger;

public class Pessoa {
    public enum Role { PROPRIETARIO, VETERINARIO }

    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int id;
    private String nome;
    private String telefone;
    private Role role;

    public Pessoa(String nome, String telefone, Role role) {
        this.id = COUNTER.getAndIncrement();
        this.nome = nome;
        this.telefone = telefone;
        this.role = role;
    }

    public int getId() {return id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}

    @Override
    public String toString() {
        return String.format("pessoa[id=%d, nome=%s, telefone=%s, role=%s]", id, nome, telefone, role);
    }
}