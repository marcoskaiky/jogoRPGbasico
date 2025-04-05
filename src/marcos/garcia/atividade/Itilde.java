package marcos.garcia.atividade;

public class Itilde extends PersonagemBase {
    private ClasseHeroi classe;

    public Itilde(String nome, ClasseHeroi classe, Atributos atributos) {
        super(nome, atributos.vida(), atributos.ataque(), atributos.defesa());
        this.classe = classe;
    }

    public ClasseHeroi getClasse() {
        return classe;
    }

    public void setClasse(ClasseHeroi classe) {
        this.classe = classe;
    }

    @Override
    public void atacar(PersonagemBase alvo, int dano) {
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
}