package com.sahaj.SnakeAndLadderApplication;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sahaj.SnakeAndLadderApplication.service.Game;
import com.sahaj.SnakeAndLadderApplication.service.StatsService;

@SpringBootApplication
public class SnakeAndLadderApplication implements CommandLineRunner {

    @Autowired
    private Game game;
    @Autowired
    private StatsService stats;

    public  static  void main(String[] args){
        SpringApplication.run(SnakeAndLadderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter board Size");
        int boardSize = scanner.nextInt();
        System.out.println("Enter simulator Number");
        int noOfSimulator = scanner.nextInt();

        int[] ladderMoves = new int[0];
        int[] snakeMoves = new int[0];
        for (int i = 1; i <= noOfSimulator; i++) {
            System.out.println("Enter Ladder moves");
            String ladderMovesStr=scanner.next();
            ladderMoves = Arrays.stream(ladderMovesStr.split(",")).mapToInt(Integer::parseInt).toArray();

            System.out.println("Enter Snakes moves");
            String snakeMovesStr= scanner.next();
            snakeMoves = Arrays.stream(snakeMovesStr.split(",")).mapToInt(Integer::parseInt).toArray();

            game.initBoard(boardSize);
            game.initializeLadderMoves(ladderMoves);
            game.initializeSnakeMoves(snakeMoves);

        }
        int averageRollDice = stats.averageRollOfDice(noOfSimulator);
        System.out.println("Average Rolls Dice to win : "+ " "+averageRollDice);

        int minRoll = stats.minRollsOfDice(boardSize);
        System.out.println("minimum Rolls Dice to win : "+ " "+minRoll);

        System.out.println("Maximum Rolls Dice to win : "+ " "+game.getMaxRoll());

        System.out.println("Max climb : "+ stats.maxClimb());
        System.out.println("Min climb :" +stats.getMinClimb());
        System.out.println("Average Climb : "+stats.averageClimb(noOfSimulator));

        //Snakes slide stats
        System.out.println("Max slides : "+ stats.maxSlides());
        System.out.println("Min slides :" +stats.getMinSlide());
        System.out.println("Average slides : "+stats.averageSlides(noOfSimulator));

        System.out.println("Unlucky Roll :"+  game.getUnLuckyRoll());
        System.out.println("Lucky Roll :"+ game.getLuckyRoll());

        System.out.println("Biggest Climb in a Single Run  :"+ stats.getBiggestClimbInSingleRun(ladderMoves));
        System.out.println("Biggest Slide in a Single Run  :"+ stats.getBiggestSlideInSingleRun(snakeMoves));
    }
}
