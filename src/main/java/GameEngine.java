import cards.Card;
import cards.CreatureCard;

import java.sql.SQLException;
import java.util.Scanner;

public class GameEngine {

    private Player player1;
    private Player player2;
    private GUI gui;
    private BattleLogic battleLogic;
    private InputProcessor inputProcessor;
    private HighScoreDB highScoreDB;

    public GameEngine(Player p1, Player p2, BattleLogic battleLogic, InputProcessor inputProcessor) {
        player1 = p1;
        player2 = p2;
        this.inputProcessor = inputProcessor;
        this.battleLogic = battleLogic;
        this.gui = new GUI(this);
        this.highScoreDB = new HighScoreDB();
    }

    public void run() throws SQLException, ClassNotFoundException {

        int choice;
        gui.printStartMenu();
        do {
            choice = inputProcessor.nextInt();
            switch (choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    System.out.println("Thanks for playing!");
                    sleep(2000);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (true);
    }

    public void gameIsOver() {

        gui.printGameOverMenu();
        inputProcessor.nextLine();

    }

    public boolean isGameOver() {
        return !player1.isAlive() || !player2.isAlive();
    }


    public void attack() {
        battleLogic.setDefendingPlayer(getInactivePlayer());
        if (battleLogic.getDefendingPlayer().getTable().isEmpty()) {
            battleLogic.cardVsPlayer();
        } else {
            int choice;
            Card defendingCard;
            do {
                System.out.println("Choose card to attack!");
                choice = inputProcessor.nextInt();
                defendingCard = getInactivePlayer().pickCardFromTable(choice);
            } while (!(getInactivePlayer().getTable().size() >= choice));
            battleLogic.setDefendingCard(defendingCard);
            battleLogic.cardVsCard();
        }
    }

    public void startGame() throws SQLException, ClassNotFoundException {
        namePlayers();
        player1.drawInitialHand();
        player2.drawInitialHand();
        randomGenerateFirstActivePlayer();

        while (!isGameOver()) {
            getActivePlayer().incrementMaxMana();
            getActivePlayer().refillMana();
            getActivePlayer().drawCard();
            getActivePlayer().setCardsOnTableToActive();
            playerChoicePhase();
            switchActivePlayer();
            printActivePlayerTurn();
        }
        //GAME OVER
       gameIsOver();
    }

    public void playerChoicePhase() {
        int choice;
        int cardIndex;

        while (!getActivePlayer().hasPassedTurn() && !isGameOver()) {
            String resolvePlay = "";
            gui.render();
            System.out.println("\033[1;31mYour turn!\n\033[0;93m1. Play a card\n2. Attack\n3. Pass\033[0m");
            choice = inputProcessor.nextInt();
            switch (choice) {
                case 1:
                    gui.printPickACardToPlay();
                    cardIndex = inputProcessor.nextInt();
                    resolvePlay = getActivePlayer().playCard(cardIndex);
                    break;
                case 2:
                    actionPhase();
                    sleep(2000);
                    break;
                case 3:
                    getActivePlayer().passTurn(true);
                    gui.nextTurn();
                    break;
                default:
                    System.out.println("\033[0;101m\033[1;97mInvalid choice!\033[0m");
                    sleep(2000);
            }
            moveAllDeadCardsToGraveYard();
            resolveEffect(resolvePlay);
        }

    }


    public void resolveEffect(String effect) {
        String[] effectComponents = effect.split("_");
        switch (effectComponents[0]) {
            case "HEAL":
                if (effectComponents[1].equals("PLAYER")) {
                    int healAmount = Math.abs(Integer.parseInt(effectComponents[2]));
                    getActivePlayer().heal(healAmount);
                    System.out.println("\033[0;93mYou healed \033[0m" + effectComponents[2] + "\033[0;93m HP!\033[0m");
                    sleep(2000);
                }
                break;
            case "ATTACK":
                int damage = Math.abs(Integer.parseInt(effectComponents[2]));
                getInactivePlayer().setHp(getInactivePlayer().getHp() - damage);
                System.out.println("\033[0;93mCard attacked directly. Defending player loses \033[0m" + damage + "\033[0;93m HP!\033[0m");
                sleep(2000);
            case "RUSH":
                Card card = getActivePlayer().getTable().get(getActivePlayer().getTable().size() - 1);
                ((CreatureCard) card).setActive(true);
                break;
        }
    }

    public void actionPhase() {
        if (getActivePlayer().hasActiveCardsOnTable() && !getActivePlayer().hasPassedTurn()) {
            gui.render();
            gui.printChooseCardToAttackWith();
            int choice;
            Card pickedCard;
            do {
                choice = inputProcessor.nextInt();
                if (choice == 0) {
                    getActivePlayer().passTurn(true);
                    return;
                }
                pickedCard = getActivePlayer().pickCardFromTable(choice);
            } while (pickedCard == null || !((CreatureCard) pickedCard).isActive());
            battleLogic.setAttackingCard(pickedCard);
            attack();
        } else {
            System.out.println("\033[1;93mYou have no cards you can attack with!\033[0m");
            sleep(2000);
        }
    }

    public Player getActivePlayer() {
        if (player1.isActive()) {
            return player1;
        } else {
            return player2;
        }
    }


    public Player getInactivePlayer() {
        if (!player1.isActive()) {
            return player1;
        } else {
            return player2;
        }
    }

    public void switchActivePlayer() {
        getActivePlayer().passTurn(false);
        player1.setActive(!player1.isActive());
        player2.setActive(!player2.isActive());
    }

    private void randomGenerateFirstActivePlayer() {
        if (!player1.isActive() && !player2.isActive()) {
            int lottery = RandomNumberGenerator.roll();
            if ((lottery % 2) == 0) {
                player1.setActive(true);
            } else if ((lottery % 2) != 0) {
                player2.setActive(true);
            }
        }

    }

    private void moveAllDeadCardsToGraveYard() {
        getActivePlayer().moveDeadCardToGraveyard();
        getInactivePlayer().moveDeadCardToGraveyard();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void namePlayers() throws SQLException, ClassNotFoundException {
        inputProcessor.nextLine();
        System.out.println("Enter name for player one:");
        player1.setName(inputProcessor.nextLine());
        /*     highScoreDB.addUserIfNew(player1.getName());*/
        System.out.println("Enter name for player two:");
        player2.setName(inputProcessor.nextLine());


    }

    private void printActivePlayerTurn() {
        System.out.printf("\033[1;93mSwitching from %s's turn to %s's turn\n \033[0m", getInactivePlayer().getName(), getActivePlayer().getName());
        sleep(3000);
    }
}

