package marcos.garcia.atividade;

public abstract class PersonagemBase {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;

    public PersonagemBase(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public abstract void atacar(PersonagemBase alvo, int dano);

    public void receberDano(int dano) {
        setVida(getVida() - dano);
    }

    public void curar(int pontos) {
        setVida(getVida() + pontos);
    }
}
