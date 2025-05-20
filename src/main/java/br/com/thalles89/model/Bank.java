package br.com.thalles89.model;

public class Bank {
    private int bet;
    private int total;

    public Bank(Integer amount){
        total=amount;
    }

    public void place10Bet(){
        placeBet(10);
    }
    public void place50Bet(){
        placeBet(50);
    }
    public void place100Bet(){
        placeBet(100);
    }
    public void doubleDown(){
        placeBet(bet);
        bet = bet*2;
    }
    public void placeBet(Integer amount){
        bet = amount;
        total -= amount;
    }

    public void win(){
        total=total+(2*bet);
        bet=0;
    }

    public void lose(){
        bet=0;
    }

    public void blackjack(){
        total += (((3*bet)/2)+bet);
    }

    public void standoff(){
        total+=bet;
        bet=0;
    }

    @Override
    public String toString() {
        return String.format("R$%s,00", total);
    }

}
