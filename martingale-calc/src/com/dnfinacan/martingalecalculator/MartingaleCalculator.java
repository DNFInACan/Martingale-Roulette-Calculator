package com.dnfinacan.martingalecalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.math.BigDecimal;

//---------------------------------------------------------------------//
//             This calculator uses the Martingale roulette            //
//          strategy to calculate the best odds of you making          //
//                  more money while playing roulette.                 //
//---------------------------------------------------------------------//

public class MartingaleCalculator {

    final static int TOTAL_CALCULATIONS = 1000;
    final static String FILE_NAME = "CalculatedBet.txt";
    static double ODDS_OF_SUCCESS = 0.52631578947D; // This is the American version of roulette, adjust accordingly to how your odds are. (European is 0.51351351351D).

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("What is your bet? : ");
        double bet = scanner.nextDouble();

        File output = new File(FILE_NAME);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(output));

            for (int i = 0; i < TOTAL_CALCULATIONS; i++) {
                BigDecimal oddsOfLosing = calculateOddsOfLosing(i);
                double newBet = calculateBet(bet, i);
                writer.println(String.format("Bet %d: %e. Odds of losing money: %e",i,newBet,oddsOfLosing));
                bet = newBet;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BigDecimal calculateOddsOfLosing(int totalBets) {
        System.out.println(ODDS_OF_SUCCESS);
        BigDecimal valueToReturn = BigDecimal.valueOf(Math.pow(ODDS_OF_SUCCESS, totalBets));
        return valueToReturn;
    }

    public static double calculateBet(double bet, int totalBets) {
        boolean isTBetOne = totalBets == 0;
        double newBet = bet;
        if (!isTBetOne) {
            newBet = bet * 2;
        }

        return newBet;
    }

}
