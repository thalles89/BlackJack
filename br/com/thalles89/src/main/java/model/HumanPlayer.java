package model;

import interfaces.Dealer;

public class HumanPlayer extends BettingPlayer {

    private static final String HIT = "H";
    private static final String STAND = "S";
    private static final String MSG = "[H]it or [S]tand";
    private static final String DD = "DO YOU WANT TO DOUBLE THE BET? \n[Y]ES or [N]o";
    private static final String BET_MSG = "Place Bet [10], [50] OR [100]";
    private static final String BET_10 = "10";
    private static final String BET_50 = "50";
    private static final String BET_100 = "100";
    private static final String YES = "Y";
    private static final String NO = "N";
    private static final String DEFAULT = "invalid";


    public HumanPlayer(String name, Hand hand, Bank bank) {
        super(name, hand, bank);
    }

    @Override
    protected Boolean hit(Dealer dealer) {
        while (true) {
            Console.INSTANCE.printMessage(this.getName()+": "+MSG);
            String response = Console.INSTANCE.readInput(DEFAULT);
            if (response.equalsIgnoreCase(HIT)) {
                return true;
            } else if (response.equalsIgnoreCase(STAND)) {
                return false;
            }
        }
    }

    @Override
    protected Boolean doubleDown(Dealer dealer) {
        while (true){
            Console.INSTANCE.printMessage(this.getName()+" "+DD);
            String response = Console.INSTANCE.readInput(DEFAULT);
            if (response.equalsIgnoreCase(YES)) {
                Console.INSTANCE.playerDoubling(this);
                return true;
            } else if (response.equalsIgnoreCase(NO)) {
                setCurrentState(getWaitingState());
                return false;
            }
        }
    }

    @Override
    protected void bet() {
        while (true){
            Console.INSTANCE.printMessage(this.getName()+" "+BET_MSG);
            String response = Console.INSTANCE.readInput(DEFAULT);
            if (response.equalsIgnoreCase(BET_10)) {
                getBank().place10Bet();
                return;
            } else if (response.equalsIgnoreCase(BET_50)) {
                getBank().place50Bet();
                return;
            }if (response.equalsIgnoreCase(BET_100)) {
                getBank().place100Bet();
                return;
            }
        }
    }

}
