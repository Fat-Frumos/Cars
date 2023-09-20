package com.ua.tictactoe;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdversarialSearchTicTacToe {

    public int minMaxDecision(State state) {
        ArrayList<State> possibleMoves = successorsOf(state);
        ArrayList<Integer> movesList = possibleMoves.stream().map(this::minValue).collect(Collectors.toCollection(ArrayList::new));

        int max = movesList.get(0);
        int bestIndex = 0;

        for (int i = 1; i < movesList.size(); i++) {
            if (movesList.get(i) > max) {
                max = movesList.get(i);
                bestIndex = i;
            }
        }
        return possibleMoves.get(bestIndex).getPosition();
    }

    private int maxValue(State state) {
        if (isTerminal(state)) {
            return utilityOf(state);
        }
        int v = (int) -Double.POSITIVE_INFINITY;

        for (State possibleMove : successorsOf(state)) {
            v = Math.max(v, minValue(possibleMove));
        }
        return v;
    }

    //Picks best option for the O-player
    private int minValue(State state) {
        if (isTerminal(state)) {
            return utilityOf(state);
        }

        int v = (int) Double.POSITIVE_INFINITY;
        for (State possibleMove : successorsOf(state)) {

            v = Math.min(v, maxValue(possibleMove));
        }
        return v;
    }

    //Returns true if the game is over
    public boolean isTerminal(State state) {
        int takenSpots = 0;
        for (int a = 0; a < 9; a++) {
            if (state.getStateIndex(a).equals("X") || state.getStateIndex(a).equals("O")) {
                takenSpots++;
            }

            String line = checkState(state, a);

            if (line.equals("XXX")) {
                return true;
            } else if (line.equals("OOO")) {
                return true;
            }
            if (takenSpots == 9) {
                return true;
            }
        }
        return false;
    }

    //Returns +1 if X is winner
    //Return -1 if O is winner
    //Returns 0 if no one won
    private int utilityOf(State state) {
        for (int a = 0; a < 8; a++) {
            String line = checkState(state, a);
            //Check for Winners
            if (line.equals("XXX")) {
                return 1;
            } else if (line.equals("OOO")) {
                return -1;
            }
        }
        return 0;
    }

    private String checkState(State state, int a) {
        if (a == 0) {
            return state.getStateIndex(0) + state.getStateIndex(1) + state.getStateIndex(2);
        } else if (a == 1) {
            return state.getStateIndex(3) + state.getStateIndex(4) + state.getStateIndex(5);
        } else if (a == 2) {
            return state.getStateIndex(6) + state.getStateIndex(7) + state.getStateIndex(8);
        } else if (a == 3) {
            return state.getStateIndex(0) + state.getStateIndex(3) + state.getStateIndex(6);
        } else if (a == 4) {
            return state.getStateIndex(1) + state.getStateIndex(4) + state.getStateIndex(7);
        } else if (a == 5) {
            return state.getStateIndex(2) + state.getStateIndex(5) + state.getStateIndex(8);
        } else if (a == 6) {
            return state.getStateIndex(0) + state.getStateIndex(4) + state.getStateIndex(8);
        } else if (a == 7) {
            return state.getStateIndex(2) + state.getStateIndex(4) + state.getStateIndex(6);
        } else {
            return "";
        }
    }

    //Returns all possible states form a given state
    private ArrayList<State> successorsOf(State state) {
        ArrayList<State> possibleMoves = new ArrayList<>();
        int xMoves = 0;
        int yMoves = 0;
        String player;

        //Calculate player turn
        for (String s : state.getState()) {
            if (s.equals("X")) {
                xMoves++;
            } else if (s.equals("O")) {
                yMoves++;
            }
        }

        if (xMoves <= yMoves) {
            player = "X";
        } else {
            player = "O";
        }

        //Create all possible states
        for (int i = 0; i < 9; i++) {
            String[] newState = state.getState().clone();

            if (!Objects.equals(newState[i], "X") && !Objects.equals(newState[i], "O")) {
                newState[i] = player;
                possibleMoves.add(new State(i, newState));
            }
        }
        return possibleMoves;
    }
}
