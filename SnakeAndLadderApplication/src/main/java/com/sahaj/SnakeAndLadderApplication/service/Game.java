package com.sahaj.SnakeAndLadderApplication.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.sahaj.SnakeAndLadderApplication.model.Board;
import com.sahaj.SnakeAndLadderApplication.model.Cell;
import com.sahaj.SnakeAndLadderApplication.model.Dice;
import com.sahaj.SnakeAndLadderApplication.model.Ladder;
import com.sahaj.SnakeAndLadderApplication.model.Snake;

import lombok.Getter;

@Getter
@Service
public class Game implements IGameInterface{

    private Dice dice;

    private Cell cell;

    private Board board;
    private int[] moves;

    private HashSet<Ladder> setOfLadder;
    private HashSet<Snake> setOfSnake;

    private int luckyRoll;
    private int unLuckyRoll;

    private int maxRoll=0;
    private int maxClimb=0;
    private int maxSlides=0;



    @Override
    public void initBoard(int N) {
        this.moves = new int[N+1];
        Arrays.fill(this.moves, -1);
        board = new Board(N);
        dice = new Dice(1, 6, 2);
        cell = new Cell();
        setOfLadder = new HashSet<>();
        setOfSnake = new HashSet<>();
    }

    public void initializeLadderMoves(final int[] ladderMoves) {
        for (int i = 0; i < ladderMoves.length; i++) {
            Ladder ladder = new Ladder(ladderMoves[i],ladderMoves[i + 1]);
            setOfLadder.add(ladder);
            moves[ladderMoves[i]-1] = (ladderMoves[++i]);
        }
    }

    public void initializeSnakeMoves(final int[] snakeMoves) {

        for (int i = 0; i < snakeMoves.length; i++) {
            Snake snake = new Snake(snakeMoves[i],snakeMoves[i + 1]);
            setOfSnake.add(snake);
            moves[snakeMoves[i] - 1] = snakeMoves[++i];
        }
        System.out.println(Arrays.toString(this.moves));

    }

    public boolean isLuckyRoll(int newPosition){
        for(Ladder ladder: setOfLadder){
            if(moves[newPosition] == ladder.getEnd() || checkMissSnakeByOneOrTwoSteps(newPosition)
            || isLuckyRolledAfter94(newPosition))
                return true;
        }
        return  false;
    }
    public  int climbTaken(int newPosition){
        for(Ladder ladder : setOfLadder) {
            if (moves[newPosition] == ladder.getEnd()) {
                System.out.println("Ladder climb");
                return  Math.abs(ladder.getEnd() - ladder.getStart());
            }
        }
        return 0;
    }

    private boolean checkMissSnakeByOneOrTwoSteps(int newPosition){
        for(Snake snake : setOfSnake){
            if(moves[newPosition+1] == snake.getTail() || moves[newPosition+2]==snake.getTail()){
                return  true;
            }
        }
        return  false;
    }

    private boolean isLuckyRolledAfter94(int newPosition){
        return cell.getIndex() + newPosition == board.getEnd();
    }

    public  boolean isUnluckyRoll(int newPosition){
        for(Snake snake : setOfSnake){
            if(moves[newPosition] == snake.getTail()){
                System.out.println("Snake Bite");
                return  true;
            }
        }
        return  false;
    }

    public int slidesTaken(int newPosition){
        for(Snake snake : setOfSnake) {
            if (moves[newPosition] == snake.getTail())
                return  Math.abs(snake.getHead()-snake.getTail());

        }
        return 0;
    }
    @Override
    public int playGame() {
        int S=0;
        while (cell.getIndex() < 100) {
            S= S+1;
            int val = dice.roll();
            int newPosition = cell.getIndex() + val;
            if (newPosition > board.getEnd()) {
                cell.setIndex(cell.getIndex());
            } else {
                if(moves[newPosition]!=-1) {
                    cell.setIndex(moves[(newPosition)]);
                    if(isLuckyRoll(newPosition)) {
                        luckyRoll++;
                        maxClimb += climbTaken(newPosition);
                    }
                    else if(isUnluckyRoll(newPosition)) {
                        unLuckyRoll++;
                        maxSlides += slidesTaken(newPosition);
                    }
                }
                else
                    cell.setIndex(newPosition);
                if (cell.getIndex() == board.getEnd()) {
                    System.out.println("Player has won");
                    break;
                }
            }

        }
        maxRoll =S;
        return S;

    }

}
