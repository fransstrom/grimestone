import cards.Card;
import cards.CreatureCard;
import cards.MagicCard;
import cards.SpecialCreatureCard;
import jdk.nashorn.internal.runtime.NumberToString;

import java.util.ArrayList;

public class GUI {

    private GameEngine gameEngine;

    public GUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void render() {
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------");
        printPlayerHud(gameEngine.getInactivePlayer());
        System.out.println();
        printPlayerCardsInHand(gameEngine.getInactivePlayer());
        System.out.println();
        printCardsOnTable();
        System.out.println();
        printPlayerCardsInHand(gameEngine.getActivePlayer());
        System.out.println("\n");
        printPlayerHud(gameEngine.getActivePlayer());
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printPlayerHP(Player player) {
        String num = "00";
        if (player.getHp() < 9) {
            num = "0" + player.getHp();
        } else {
            num = Integer.toString(player.getHp());
        }
        System.out.printf("| \033[4;33m"+player.getName()+ "\033[0m HP: " + num + " |");
    }

    public void printManaBar(Player player){
        String numMax = "00";
        String num = "00";
        if (player.getMaxMana()<=9){
            numMax = "0" + player.getMaxMana();
        }else{
            numMax= Integer.toString(player.getMaxMana());
        }
        if (player.getMana()<=9){
            num = "0" + player.getMana();
        }else{
            num=Integer.toString(player.getMana());
        }
        System.out.printf("MANA: " +num+"/"+numMax);
    }

    public void printPlayerCardsInHand(Player player) {
        if (player.isActive()) {
            ArrayList<Card> cardsInHandForActivePlayer = player.getHand();
            //printSpaceBetweenCardsAndBoard(cardsInHandForActivePlayer);
            printCards(cardsInHandForActivePlayer, false);
        } else {
            ArrayList<Card> cardsInHandForInactivePlayer = player.getHand();
            //printSpaceBetweenCardsAndBoard(cardsInHandForInactivePlayer);
            printCards(cardsInHandForInactivePlayer, true);
        }
    }

    public void printPlayerHud(Player player) {
        printNumberOfGraveyardCards(player);
        System.out.printf("                              ");
        printPlayerHP(player);
        System.out.printf("           ");
        printManaBar(player);
        System.out.printf("             ");
        printRemainingCards(player);
    }

    public void printRemainingCards(Player player) {
        String num = "00";
        if (player.getDeck().size() < 9) {
            num = "0" + player.getDeck().size();
        } else {
            num = Integer.toString(player.getDeck().size());
        }
        System.out.printf("| Cards in Deck: " + num + " |");
    }

    public void printNumberOfGraveyardCards(Player player) {
        String num = "00";
        if (player.getGraveyard().size() < 9) {
            num = "0" + player.getGraveyard().size();
        } else {
            num = Integer.toString(player.getGraveyard().size());
        }
        System.out.printf("| Cards in Graveyard: " + num + " |");
    }

    public void printCardsOnTable() {
        ArrayList<Card> activePlayerTable = gameEngine.getActivePlayer().getTable();
        ArrayList<Card> inActivePlayerTable = gameEngine.getInactivePlayer().getTable();
        printCards(inActivePlayerTable, false);
        System.out.printf("\n\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        printCards(activePlayerTable, false);
    }

    public void printCards(ArrayList<Card> cardList, boolean hidden) {
        if(cardList.size()==0) {
                System.out.printf("\n\n\n\n\n\n\n\n\n");
        }
        if(!hidden && cardList.size()>0){
            String FormatCard = " %-24s ";

            String BlankCardLine = "| %-21s | ";
            String BlankSpecialCardLine = "\u001B[33m| %-21s | \u001B[0m";
            String BlankMagicCardLine = "\033[0;35m| %-21s | \u001B[0m";

            String NameFormatCreatureCard = "| \033[1;30m%-15s \u001B[0mHP %-2d | ";
            String NameFormatSpecialCreatureCard = "\u001B[33m|\033[1;33m %-15s \u001B[33mHP %-2d | \u001B[0m";
            String NameFormatMagicCard = "\033[0;35m| \033[1;30m%-21s \033[0;35m| \u001B[0m";

            String ManaCostFormat = "| \033[0;34mMana Cost: %-2d %-7s \u001B[0m| ";
            String ManaCostSpecialCreatureFormat = "\u001B[33m| \033[0;34mMana Cost: %-2d %-7s \u001B[33m| \u001B[0m";
            String ManaCostMagicFormat = "\033[0;35m| \033[0;34mMana Cost: %-2d %-7s \033[0;35m| \u001B[0m";


            String TypeFormat = "| TYPE: %-15s | ";
            String TypeSpecialCreatuerCardFormat = "\u001B[33m| TYPE: %-15s | \u001B[0m";

            String CreatureCardStatFormat = "| ATT %-2d  DEF %-2d \u001B[31m S  %-2d\u001B[0m | " ;
            String SpecialCreatureCardStatFormat = "\u001B[33m| ATT %-2d  DEF %-2d  \u001B[31m S %-2d\u001B[33m | \u001B[0m" ;

            String CreatureCardStatFormatRested = "| ATT %-2d  DEF %-2d       \u001B[0m | " ;
            String SpecialCreatureCardStatFormatRested = "\u001B[33m| ATT %-2d  DEF %-2d       \u001B[33m | \u001B[0m" ;
            printSpaceBetweenCardsAndBoard(cardList);
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(FormatCard, "\u001B[33m----------------------- \u001B[0m");
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(FormatCard, "-----------------------");
                if (card instanceof MagicCard)
                    System.out.printf(FormatCard, "\033[0;35m----------------------- \u001B[0m");
            }
            //PRINT OUT NAME LINE
            printSpaceBetweenCardsAndBoard(cardList);
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(NameFormatCreatureCard, card.getName(), ((CreatureCard) card).getHp());
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(NameFormatSpecialCreatureCard, card.getName(), ((SpecialCreatureCard) card).getHp());
                if (card instanceof MagicCard)
                    System.out.printf(NameFormatMagicCard, card.getName());
            }
            //PRINT OUT MANACOST LINE
            printSpaceBetweenCardsAndBoard(cardList);
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(ManaCostFormat,  card.getManaCost(), "");
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(ManaCostSpecialCreatureFormat,  card.getManaCost(), "");
                if (card instanceof MagicCard)
                    System.out.printf(ManaCostMagicFormat,  card.getManaCost(), "");
            }
            //PRINT OUT TYPE LINE
            printSpaceBetweenCardsAndBoard(cardList);
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(TypeFormat, ((CreatureCard) card).getType());
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(TypeSpecialCreatuerCardFormat, ((CreatureCard) card).getType());
                if (card instanceof MagicCard)
                    System.out.printf(BlankMagicCardLine, "");
            }
            //PRINT OUT BLANK LINE
            printSpaceBetweenCardsAndBoard(cardList);
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(BlankSpecialCardLine, "");
                else if (card instanceof MagicCard)
                    System.out.printf(BlankMagicCardLine, "");
                else
                    System.out.printf(BlankCardLine, "");
            }
            printSpaceBetweenCardsAndBoard(cardList);
            //PRINT OUT EFFECT TEXT
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(BlankSpecialCardLine, ((SpecialCreatureCard) card).getText());
                if (card instanceof MagicCard)
                    System.out.printf(BlankMagicCardLine, ((MagicCard) card).getText());
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(BlankCardLine, "");
            }
            printSpaceBetweenCardsAndBoard(cardList);
            //PRINT OUT ACTIVE/RESTING
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                // || cardList.equals(gameEngine.getInactivePlayer().getTable())
                if (cardList.equals(gameEngine.getActivePlayer().getTable()) && ((CreatureCard) card).getActivationCountdown()==0){
                if (card instanceof CreatureCard && ((CreatureCard) card).isActive()) {
                    if (card instanceof SpecialCreatureCard)
                        System.out.printf(BlankSpecialCardLine, "       \033[0;32mACTIVE\u001B[33m        ");
                    if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                        System.out.printf(BlankCardLine, "       \033[0;32mACTIVE\u001B[0m        ");
                }else if (card instanceof CreatureCard) {
                    if (card instanceof SpecialCreatureCard)
                        System.out.printf(BlankSpecialCardLine, "       \033[0;31mRESTING\u001B[33m       ");
                    if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                        System.out.printf(BlankCardLine, "       \033[0;31mRESTING\u001B[0m       ");
                         }
                }else if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard)) {
                    System.out.printf(BlankCardLine, "");
                }else if (card instanceof SpecialCreatureCard) {
                    System.out.printf(BlankSpecialCardLine, "");
                }else {
                    if (card instanceof MagicCard)
                        System.out.printf(BlankMagicCardLine, "");
                }
            }
            printSpaceBetweenCardsAndBoard(cardList);
            //PRINT OUT STATS
            for (int i=0; i<cardList.size(); i++) {
                Card card = cardList.get(i);
                if (card instanceof MagicCard) {
                    System.out.printf(BlankMagicCardLine, "");
                }
                    if (card instanceof CreatureCard && ((CreatureCard) card).getActivationCountdown() > 0) {
                        if (card instanceof SpecialCreatureCard)
                            System.out.printf(SpecialCreatureCardStatFormat, ((SpecialCreatureCard) card).getAttack(), ((SpecialCreatureCard) card).getDefense(), ((SpecialCreatureCard) card).getActivationCountdown());
                        if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                            System.out.printf(CreatureCardStatFormat, ((CreatureCard) card).getAttack(), ((CreatureCard) card).getDefense(), ((CreatureCard) card).getActivationCountdown());
                    } else {
                        if (card instanceof SpecialCreatureCard)
                            System.out.printf(SpecialCreatureCardStatFormatRested, ((SpecialCreatureCard) card).getAttack(), ((SpecialCreatureCard) card).getDefense());
                        if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                            System.out.printf(CreatureCardStatFormatRested, ((CreatureCard) card).getAttack(), ((CreatureCard) card).getDefense());
                    }
                }

            printSpaceBetweenCardsAndBoard(cardList);
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(FormatCard, "\u001B[33m--------Card #" + (i+1) + "-------- \u001B[0m");
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(FormatCard, "--------Card #" + (i+1) + "--------");
                if (card instanceof MagicCard)
                    System.out.printf(FormatCard, "\033[0;35m--------Card #" + (i+1) + "-------- \u001B[0m");
            }


        }else if (hidden && cardList.size()>0){
            String FormatCard = " %-24s ";
            String BlankCardLine = "| %-21s | ";
            printSpaceBetweenCardsAndBoard(cardList);
            for (int i=0; i<cardList.size(); i++){
                    System.out.printf(FormatCard, "-----------------------");
            }
            for (int i=0; i<7;i++) {
                System.out.println();
                printSpaceBetweenHiddenCardsAndBoard(cardList);
                //PRINT OUT BLANK LINE
                for (int j = 0; j < cardList.size(); j++) {
                    if (i==3) {
                        System.out.printf(BlankCardLine, "       Card  " + (j+1));
                    }else {
                        System.out.printf(BlankCardLine, "");
                    }
                }
            }
            printSpaceBetweenCardsAndBoard(cardList);
            for (int i=0; i<cardList.size(); i++){
                System.out.printf(FormatCard, "-----------------------");
            }
        }
    }

    public void printSpaceBetweenCardsAndBoard(ArrayList<Card> cardList) {
        switch (cardList.size()) {
            case 5:
                System.out.printf("\n");
                break;
            case 4:
                System.out.printf("\n             ");
                break;
            case 3:
                System.out.printf("\n                          ");
                break;
            case 2:
                System.out.printf("\n                                       ");
                break;
            case 1:
                System.out.printf("\n                                                    ");
                break;
        }
    }

    public void printSpaceBetweenHiddenCardsAndBoard(ArrayList<Card> cardList) {
        switch (cardList.size()) {
            case 5:
                System.out.printf("");
                break;
            case 4:
                System.out.printf("             ");
                break;
            case 3:
                System.out.printf("                          ");
                break;
            case 2:
                System.out.printf("                                       ");
                break;
            case 1:
                System.out.printf("                                                    ");
                break;
        }
    }

    public void printGameOverMenu() {
        String gameOverMenuDesign = "------------------------------------------------------------------------------------------------------------------------------\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                         Game over!                                                         |\n"
                + "|                                                                                                                            |\n"
                + "|                                                       1. Play Again                                                        |\n"
                + "|                                                       2. Quit                                                              |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "------------------------------------------------------------------------------------------------------------------------------";
        System.out.println(gameOverMenuDesign);
    }

    public void printStartMenu() {
        String startMenuDesign = "------------------------------------------------------------------------------------------------------------------------------\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                      * GRIME STONE! *                                                      |\n"
                + "|                                                                                                                            |\n"
                + "|                                                         1. Play                                                            |\n"
                + "|                                                         2. Quit                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "------------------------------------------------------------------------------------------------------------------------------";
        System.out.println(startMenuDesign);
    }

    public void printPickACardToPlay(){
        System.out.println("Choose a card from your hand to play. Press 0 to pass turn.");
    }
    public void printChooseCardToAttackWith(){
        System.out.println("Choose a card to attack with. Press 0 to pass turn.");
    }

    public void nextTurn(){
        System.out.println("********************* NEXT TURN *********************");
    }

}