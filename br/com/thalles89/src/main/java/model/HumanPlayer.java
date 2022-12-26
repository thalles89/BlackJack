package model;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private static final String HIT = "H";
    private static final String STAND = "S";
    private static final String MSG = "[H]it or [S]tand";
    private static final String DEFAULT = "invalid";


    public HumanPlayer(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    protected Boolean hit() {
        while (true) {
            Console.INSTANCE.printMessage(MSG);
            String response = Console.INSTANCE.readInupt(DEFAULT);
            if (response.equalsIgnoreCase(HIT)) {
                return true;
            } else if (response.equalsIgnoreCase(STAND)) {
                return false;
            }
        }
    }


}
