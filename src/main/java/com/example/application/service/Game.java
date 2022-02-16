package com.example.application.service;



import com.example.application.entity.Attempt;

import java.util.Arrays;


public class Game {
    private char[] resultNumber;
    private int attemptsCounter;

    private Attempt attempt;

    public Game(String resultNumber) {
        this.resultNumber = resultNumber.toCharArray();
    }

    public AttemptResult attempt(String userNumberFromField) {
        int bull = 0;
        int cow = 0;
        char[] userNumber = userNumberFromField.toCharArray();
        for (int i = 0; i < resultNumber.length; i++) {
            if (resultNumber[i] == userNumber[i]) {
                bull++;
            } else {
                if (Arrays.toString(resultNumber).indexOf(userNumber[i]) != -1) {
                    cow++;
                }
            }
        }
        attemptsCounter++;
        return new AttemptResult(bull, cow, resultNumber.length);
    }

    public int getAttempts() {
        return attemptsCounter;
    }

    public static class AttemptResult {
        private int bull;
        private int cow;
        private int size;

        public AttemptResult(int bull, int cow, int size) {
            this.bull = bull;
            this.cow = cow;
            this.size=size;
        }

        public String getAttemptResult(){
            if (bull == size) {
                return String.format("%sБ%sК" + " " + "(число угадано)", bull, cow);
            } else {
                return String.format("%sБ%sК", bull, cow);
            }
        }

        public boolean getWinOrNot(){
            return bull==size;
        }
    }
}
