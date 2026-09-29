public class Gato extends Animal {
    private boolean gostaDeBrincar;

    public Gato(String nome, int idade, String especie, Pessoa proprietario, boolean gostaDeBrincar) {
        super(nome, idade, especie, proprietario);
        this.gostaDeBrincar = gostaDeBrincar;
    }

    public boolean isGostaDeBrincar() { return gostaDeBrincar; }
    public void setGostaDeBrincar(boolean gostaDeBrincar) { this.gostaDeBrincar = gostaDeBrincar; }

    @Override
    public String emitirSom() {
        return "Miau!";
    }

    @Override
    public String toString() {
        return super.toString() + " | gostaDeBrincar=" + gostaDeBrincar;
    }
}
