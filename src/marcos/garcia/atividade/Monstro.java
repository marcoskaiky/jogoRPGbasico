package marcos.garcia.atividade;

public class Monstro extends PersonagemBase {
    private TipoInimigo tipo;

    public Monstro(TipoInimigo tipo, Atributos atributos) {
        super(tipo.toString(), atributos.vida(), atributos.ataque(), atributos.defesa());
        this.tipo = tipo;
    }

    public TipoInimigo getTipo() {
        return tipo;
    }

    public void setTipo(TipoInimigo tipo) {
        this.tipo = tipo;
    }

    @Override
    public void atacar(PersonagemBase alvo, int dano) {
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
}

