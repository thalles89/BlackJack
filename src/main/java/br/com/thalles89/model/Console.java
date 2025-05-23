package br.com.thalles89.model;

import br.com.thalles89.interfaces.PlayerListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Aqui optamos pelo singleton para
 * facilitar o controle das jogadas via console (terminal)
 *
 * @author Thalles
 */
public class Console implements PlayerListener {
    private Console() {
    }

    public static final Console INSTANCE = new Console();

    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String readInput(String input) {
        try {
            return in.readLine();
        } catch (IOException exception) {
            return input;
        }
    }

    @Override
    public void playerChanged(Player player) {
        printMessage(player.toString());
    }

    @Override
    public void playerBusted(Player player) {
        printMessage(player.toString() + " BUSTED!!!");
    }

    @Override
    public void playerStanding(Player player) {
        printMessage(player.toString() + " STANDS");
    }
    public void playerDoubling(Player player) {
        printMessage(player.toString() + " DOUBLED AND STANDS");
    }
    @Override
    public void playerBlackjack(Player player) {
        printMessage(player.toString() + " BLACKJACK!!!");
    }

    @Override
    public void playerWon(Player player) {
        printMessage(player.toString() + " WINNER!!!!" );
    }

    @Override
    public void playerLost(Player player) {
        printMessage(player.toString() + " LOSER!!!!");
    }

    @Override
    public void playerStandOff(Player player) {
        printMessage(player.toString() + " Dropped off!!!!");
    }
}
