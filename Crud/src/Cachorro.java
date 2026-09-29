public class Cachorro extends Animal {
    private String raca;

    public Cachorro(String nome, int idade, String especie, Pessoa proprietario, String raca) {
        super(nome, idade, especie, proprietario);
        this.raca = raca;
    }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    @Override
    public String emitirSom() {
        return "Au Au!";
    }

    @Override
    public String toString() {
        return super.toString() + String.format("raca=%s", raca);
    }
}
