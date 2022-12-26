package model;

import interfaces.PalyerListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Aqui optamos pelo singleton para
 * facilitar o controle das jogadas via console (terminal)
 * @author Thalles
 * */
public class Console implements PalyerListener {

    public static final Console INSTANCE = new Console();
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private Console(){}

    public void printMessage(String message){
        System.out.println(message);
    }

    public String readInupt(String input){
        String response;
        try{
            return in.readLine();
        }catch(IOException exception){
            return input;
        }
    }


    @Override
    public void handChanged(Player player) {
        printMessage(player.toString());
    }
}
