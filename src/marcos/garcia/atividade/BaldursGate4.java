package marcos.garcia.atividade;

import java.util.Scanner;
import java.util.Random;

public class BaldursGate4 {

    public static int rolarDado() {
        Random rand = new Random();
        return rand.nextInt(20) + 1;
    }

    public static boolean combate(Itilde jogador, Monstro inimigo, Scanner scanner) {
        System.out.println("\n===========================");
        System.out.println("BATALHA CONTRA " + inimigo.getNome().toUpperCase());
        System.out.println("===========================\n");

        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {
            System.out.println("Escolha uma ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Curar");
            System.out.println("3 - Sair do jogo");
            int acao = scanner.nextInt();

            if (acao == 1) {
                int dado = rolarDado();
                System.out.println("Você rolou: " + dado);
                if (dado > 10) {
                    jogador.atacar(inimigo, jogador.getAtaque());
                } else {
                    System.out.println("Você errou o ataque!");
                }
            } else if (acao == 2) {
                int cura = (jogador.getClasse() == ClasseHeroi.BARDO) ? 30 : 20;
                jogador.curar(cura);
                System.out.println("Você se curou em " + cura + " pontos. Vida atual: " + jogador.getVida());
            } else if (acao == 3) {
                System.out.println("Saindo do jogo...");
                System.exit(0);
            } else {
                System.out.println("Ação inválida!");
            }

            if (inimigo.getVida() > 0) {
                System.out.println("\nTurno do " + inimigo.getNome() + "!");
                int dadoInimigo = rolarDado();
                System.out.println(inimigo.getNome() + " rolou: " + dadoInimigo);
                if (dadoInimigo > 10) {
                    inimigo.atacar(jogador, inimigo.getAtaque());
                } else {
                    System.out.println(inimigo.getNome() + " errou o ataque!");
                }
            }

            System.out.println("\nStatus Atual:");
            System.out.println(jogador.getNome() + " - Vida: " + jogador.getVida());
            System.out.println(inimigo.getNome() + " - Vida: " + inimigo.getVida());
            System.out.println("---------------------------");
        }

        if (jogador.getVida() <= 0) {
            System.out.println("\nVocê foi derrotado. Fim de jogo!");
            return false;
        } else {
            System.out.println("\nVocê derrotou " + inimigo.getNome() + "!");
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================");
        System.out.println("   BEM-VINDO AO BALDUR'S GATE 4");
        System.out.println("===========================\n");

        System.out.println("Escolha sua classe:");
        System.out.println("1 - Monge");
        System.out.println("2 - Paladino");
        System.out.println("3 - Bardo");
        int escolhaClasse = scanner.nextInt();
        scanner.nextLine();

        ClasseHeroi classeEscolhida;
        switch (escolhaClasse) {
            case 1 -> classeEscolhida = ClasseHeroi.MONGE;
            case 2 -> classeEscolhida = ClasseHeroi.PALADINO;
            case 3 -> classeEscolhida = ClasseHeroi.BARDO;
            default -> {
                System.out.println("Opção inválida. Monge selecionado.");
                classeEscolhida = ClasseHeroi.MONGE;
            }
        }

        System.out.print("Digite o nome do seu personagem: ");
        String nomeJogador = scanner.nextLine();

        Atributos atributosJogador = switch (classeEscolhida) {
            case MONGE -> new Atributos(110, 18, 8);
            case PALADINO -> new Atributos(140, 14, 18);
            case BARDO -> new Atributos(100, 15, 15);
        };

        Itilde jogador = new Itilde(nomeJogador, classeEscolhida, atributosJogador);

        System.out.println("\nPersonagem criado com sucesso!");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Classe: " + jogador.getClasse());
        System.out.println("Vida: " + jogador.getVida());
        System.out.println("Ataque: " + jogador.getAtaque());
        System.out.println("Defesa: " + jogador.getDefesa());
        System.out.println("===========================\n");

        System.out.println("Missão 1: Você é emboscado por Goblins!\n");
        Monstro goblins = new Monstro(TipoInimigo.GOBLINS, new Atributos(50, 10, 5));
        if (!combate(jogador, goblins, scanner)) return;

        System.out.println("\nApós derrotar os Goblins, você encontra um AMULETO MÍSTICO!");
        System.out.println("Você sente um poder crescendo dentro de você... +10 de Ataque!\n");
        jogador.setAtaque(jogador.getAtaque() + 10);

        System.out.println("Você chega a uma encruzilhada. O que deseja fazer?");
        System.out.println("1 - Enfrentar o Vampiro");
        System.out.println("2 - Enfrentar o Demônio");
        System.out.println("3 - Ir ao acampamento para descansar");
        int escolhaCaminho = scanner.nextInt();

        if (escolhaCaminho == 3) {
            System.out.println("\nVocê decide descansar no acampamento.");
            jogador.curar(30);
            System.out.println("Vida restaurada: " + jogador.getVida());
            System.out.println("Agora, escolha seu próximo desafio:");
            System.out.println("1 - Enfrentar o Vampiro");
            System.out.println("2 - Enfrentar o Demônio");
            escolhaCaminho = scanner.nextInt();
        }

        Monstro inimigoFinal;
        if (escolhaCaminho == 1) {
            inimigoFinal = new Monstro(TipoInimigo.VAMPIRO, new Atributos(80, 15, 10));
            System.out.println("\nVocê escolheu enfrentar o Vampiro!");
        } else {
            inimigoFinal = new Monstro(TipoInimigo.DEMONIO, new Atributos(120, 20, 15));
            System.out.println("\nVocê escolheu enfrentar o Demônio!");
        }

        if (!combate(jogador, inimigoFinal, scanner)) return;

        System.out.println("\nVocê alcança o antigo castelo onde o ARTEFATO SAGRADO está guardado.");
        System.out.println("Você o pega e sente o equilíbrio do mundo restaurado.\n");

        System.out.println("===========================");
        System.out.println(" PARABÉNS! Você salvou o reino!");
        System.out.println(" FIM DO JOGO ");
        System.out.println("===========================");

        scanner.close();
    }
}

