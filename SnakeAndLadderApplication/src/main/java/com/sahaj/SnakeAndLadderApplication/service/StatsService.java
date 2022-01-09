package com.sahaj.SnakeAndLadderApplication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahaj.SnakeAndLadderApplication.model.Cell;

import lombok.Getter;

@Service
@Getter
public class StatsService {

    @Autowired
    private Game executor;

    private int minClimb=0;

    private  int minSlide=0;

    public int averageRollOfDice(int noOfSimulation){
        int games=0;
        int total=0;
        while(games < noOfSimulation){
            games = games+1;
            int n=executor.playGame();
            total = total+n;

        }
        return total / noOfSimulation;
    }

    public int minClimb(final int[] ladderMoves) {
        int minClimb=ladderMoves[1]-ladderMoves[0];
        for (int i=2; i< ladderMoves.length; i++){
            int diff=Math.abs(ladderMoves[i+1]-ladderMoves[i++]);
            if(diff < minClimb ){
                minClimb=diff;
            }
        }
        return minClimb;
    }

    public int maxClimb() {
       return  executor.getMaxClimb();
    }

    public int averageClimb(int noOfSimulation) {
        return executor.getMaxClimb()/noOfSimulation;
    }

    public int maxSlides() {
       return  executor.getMaxSlides();
    }
    public int averageSlides(int noOfSimulation) {
        return  executor.getMaxSlides()/noOfSimulation;
    }

    public int minRollsOfDice(int N) {
        boolean[] isVisited = new boolean[N];
        Arrays.fill(isVisited, false);
        Queue<Cell> queue = new LinkedList<Cell>();
        Cell cell = new Cell();
        cell.setIndex(0);
        cell.setHopCount(0);
        queue.offer(cell);
        Cell currentCell = new Cell();
        while (!queue.isEmpty()) {
            currentCell = queue.poll();
            int index = currentCell.getIndex();

            if (index == N)
                break;
            for (int i = 0; i < (index + 6) && i < N; i++) {
                if (!isVisited[i]) {
                    Cell newCell = new Cell();
                    newCell.setHopCount(currentCell.getHopCount() + 1);
                    isVisited[i] = true;
                    if (executor.getMoves()[i] != -1) {
                        int move=executor.getMoves()[i];
                        newCell.setIndex(move);
                        if(executor.isLuckyRoll(move))
                            minClimb += executor.climbTaken(move);
                        else if(executor.isUnluckyRoll(move))
                            minSlide += executor.slidesTaken(move);
                    }
                    else
                        newCell.setIndex(i);

                    queue.offer(newCell);
                }
            }
        }

        return currentCell.getHopCount();
    }

    public int getBiggestClimbInSingleRun(int [] ladderMoves){
        int maxClimb=ladderMoves[1]-ladderMoves[0];
        for (int i=2; i< ladderMoves.length; i++){
            int diff=Math.abs(ladderMoves[i+1]-ladderMoves[i++]);
            if(diff > maxClimb ){
                maxClimb=diff;
            }
        }
        return maxClimb;
    }

    public int getBiggestSlideInSingleRun(int[] snakeMoves){
        int maxSlide=snakeMoves[1]-snakeMoves[0];
        for (int i=2; i< snakeMoves.length; i++){
            int diff=Math.abs(snakeMoves[i+1]-snakeMoves[i++]);
            if(diff > maxSlide ){
                maxSlide=diff;
            }
        }
        return maxSlide;
    }
}
