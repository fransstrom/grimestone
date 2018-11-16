import java.util.ArrayList;

public class GUI {

    private GameEngine gameEngine;

    public GUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void render() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        printPlayerHud(gameEngine.getInactivePlayer());
        System.out.printf("\n");
        printPlayerCardsInHand(gameEngine.getInactivePlayer());
        System.out.printf("\n\n");
        printCardsOnTable();
        System.out.printf("\n\n");
        printPlayerCardsInHand(gameEngine.getActivePlayer());
        System.out.printf("\n\n");
        printPlayerHud(gameEngine.getActivePlayer());
        System.out.printf("\n------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public void printPlayerHP(Player player) {
        String num = "00";
        if (player.getHp() < 9) {
            num = "0" + player.getHp();
        } else {
            num = Integer.toString(player.getHp());
        }
        System.out.printf("| Player HP: " + num + " |");
    }

    public void printManaBar(Player player){
        String numMax = "00";
        String num = "00";
        if (player.getMaxMana()<9){
            numMax = "0" + player.getMaxMana();
        }
        if (player.getMana()<9){
            num = "0" + player.getMana();
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
        //printSpaceBetweenCardsAndBoard(inActivePlayerTable);
        printCards(inActivePlayerTable, false);
        System.out.printf("\n\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        //printSpaceBetweenCardsAndBoard(activePlayerTable);
        System.out.println();
        printCards(activePlayerTable, false);
    }

    public void printCards(ArrayList<Card> cardList, boolean hidden) {
        if (cardList.size() == 0) {
            //System.out.println();
        }
        if(!hidden){
            String FormatCard = " %-24s ";
            String BlankCardLine = "| %-21s | ";
            String BlankSpecialCardLine = "\u001B[33m| %-21s | \u001B[0m";

            String NameFormatCreatureCard = "| \033[1;30m%-15s \u001B[0mHP %-2d | ";
            String NameFormatSpecialCreatureCard = "\u001B[33m|\033[1;33m %-15s \u001B[33mHP %-2d | \u001B[0m";
            String NameFormatMagicCard = "| \033[1;30m%-21s \u001B[0m| ";

            String ManaCostFormat = "| \033[0;34mMana Cost: %-2d %-7s \u001B[0m| ";
            String ManaCostSpecialCreatureFormat = "\u001B[33m| \033[0;34mMana Cost: %-2d %-7s \u001B[33m| \u001B[0m";

            String TypeFormat = "| TYPE: %-15s | ";
            String TypeSpecialCreatuerCardFormat = "\u001B[33m| TYPE: %-15s | \u001B[0m";

            String CreatureCardStatFormat = "| ATT %-2d  DEF %-2d \u001B[31m S  %-2d\u001B[0m | " ;
            String SpecialCreatureCardStatFormat = "\u001B[33m| ATT %-2d  DEF %-2d  \u001B[31m S %-2d\u001B[33m | \u001B[0m" ;

            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(FormatCard, "\u001B[33m----------------------- \u001B[0m");
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(FormatCard, "-----------------------");
                if (card instanceof MagicCard)
                    System.out.printf(FormatCard, "-----------------------");
            }
            //PRINT OUT NAME LINE
            System.out.println();
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
            System.out.println();
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(ManaCostFormat,  card.getManaCost(), "");
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(ManaCostSpecialCreatureFormat,  card.getManaCost(), "");
                if (card instanceof MagicCard)
                    System.out.printf(ManaCostFormat,  card.getManaCost(), "");
            }
            //PRINT OUT TYPE LINE
            System.out.println();
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(TypeFormat, ((CreatureCard) card).getType());
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(TypeSpecialCreatuerCardFormat, ((CreatureCard) card).getType());
                if (card instanceof MagicCard)
                    System.out.printf(BlankCardLine, "");
            }
            //PRINT OUT BLANK LINE
            System.out.println();
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(BlankSpecialCardLine, "");
                else
                    System.out.printf(BlankCardLine, "");
            }
            System.out.println();
            //PRINT OUT EFFECT TEXT
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(BlankSpecialCardLine, ((SpecialCreatureCard) card).getText());
                if (card instanceof MagicCard)
                    System.out.printf(BlankCardLine, ((MagicCard) card).getText());
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(BlankCardLine, "");
            }
            System.out.println();
            //PRINT OUT BLANK LINE
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(BlankSpecialCardLine, "");
                else
                    System.out.printf(BlankCardLine, "");
            }
            System.out.println();
            //PRINT OUT STATS
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(SpecialCreatureCardStatFormat, 10,10,3);
                if (card instanceof MagicCard)
                    System.out.printf(BlankCardLine, "");
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(CreatureCardStatFormat, 3,3,1);
            }
            System.out.println();
            for (int i=0; i<cardList.size(); i++){
                Card card = cardList.get(i);
                if (card instanceof SpecialCreatureCard)
                    System.out.printf(FormatCard, "\u001B[33m----------------------- \u001B[0m");
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                    System.out.printf(FormatCard, "-----------------------");
                if (card instanceof MagicCard)
                    System.out.printf(FormatCard, "-----------------------");
            }

        }else{
            System.out.println();
            String FormatCard = " %-24s ";
            String BlankCardLine = "| %-21s | ";
            for (int i=0; i<cardList.size(); i++){
                    System.out.printf(FormatCard, "-----------------------");
            }
            for (int i=0; i<7;i++) {
                System.out.println();
                //PRINT OUT BLANK LINE
                for (int j = 0; j < cardList.size(); j++) {
                    if (i==3) {
                        System.out.printf(BlankCardLine, "       Card  " + (j+1));
                    }else
                    System.out.printf(BlankCardLine, "");
                }
            }
            System.out.println();
            for (int i=0; i<cardList.size(); i++){
                System.out.printf(FormatCard, "-----------------------");
            }
        }
        /*if (!hidden) {
            int cardPlacement = 1;
            for (Card card : cardList) {
                if (card instanceof CreatureCard && !(card instanceof SpecialCreatureCard)) {
                    if (!((CreatureCard) card).isActive()) {
                        System.out.printf(" | #" + cardPlacement + " HP: " + ((CreatureCard) card).getHp() + "\u001B[31m CD: 1\u001B[0m | ");
                    } else {
                        System.out.printf(" | #" + cardPlacement + " HP: " + ((CreatureCard) card).getHp() + " | ");
                    }
                }
                else if(card instanceof MagicCard){
                    if (((MagicCard) card).trigger().contains("HEAL")) {
                        System.out.printf("\033[0;32m | #" + cardPlacement + " " + ((MagicCard) card).getText() + " | \u001B[0m");
                    }else if(((MagicCard) card).trigger().contains("LIGHTNING")){
                        System.out.printf("\033[0;35m | #" + cardPlacement + " " + ((MagicCard) card).getText() + " | \u001B[0m");
                    }else if(((MagicCard) card).trigger().contains("FIRE")){
                        System.out.printf("\033[1;31m | #" + cardPlacement + " " + ((MagicCard) card).getText() + " | \u001B[0m");
                    }
                }
                else if(card instanceof SpecialCreatureCard){
                    if (!((CreatureCard) card).isActive()){
                        System.out.printf("\u001B[33m | #" + cardPlacement + " " + card.getName() + " HP: " + ((SpecialCreatureCard) card).getHp() + " \033[4;30m" + ((SpecialCreatureCard) card).getText()  + "\u001B[31m CD: " + ((SpecialCreatureCard) card).getActivationCountdown() + "\u001B[33m | \u001B[0m");
                    }
                }
                if(card instanceof MagicCard){
                    System.out.printf("\033[0;32m | Card " + cardPlacement + ((MagicCard) card).getText() + " | \u001B[0m");
                }
                cardPlacement++;
            }
        } else {
            int cardPlacement = 1;
            for (Card card : cardList) {
                if (card instanceof CreatureCard) {
                    System.out.printf(" |    Card " + cardPlacement + "    | ");
                }
                if(card instanceof MagicCard){
                    System.out.printf(" |    Card " + cardPlacement + "    | ");
                }
                cardPlacement++;

            }
        }*/
    }

    public void printSpaceBetweenCardsAndBoard(ArrayList<Card> cardList) {
        switch (cardList.size()) {
            case 5:
                System.out.printf("\n                  ");
                break;
            case 4:
                System.out.printf("\n                           ");
                break;
            case 3:
                System.out.printf("\n                                    ");
                break;
            case 2:
                System.out.printf("\n                                             ");
                break;
            case 1:
                System.out.printf("\n                                                      ");
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
        System.out.println("*************** NEXT TURN *********************");
    }

}