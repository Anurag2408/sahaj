package com.sahaj.SnakeAndLadderApplication.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.sahaj.SnakeAndLadderApplication.config.SnakeAndLadderApplicationConfiguration;

@SpringBootTest(classes = SnakeAndLadderApplicationConfiguration.class)
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {

    @Mock
    private Game executor;

    @InjectMocks
    private StatsService stats;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
        Mockito.mock(Game.class);
    }
    @Test
    public void testAverageRollDice(){
        when(executor.playGame()).thenReturn(16);
        int avgRollDice=stats.averageRollOfDice(2);
        Assertions.assertEquals(16, avgRollDice);
    }

    @Test
    public void testMinRollDice(){
        int minRoll= stats.minRollsOfDice(100);
        Assertions.assertEquals(6,minRoll);
    }


    @Test
    public void testMaxRollDice(){
        when(executor.playGame()).thenReturn(16);
        int maxRollDice=executor.playGame();
        Assertions.assertEquals(16, maxRollDice);

    }

    @Test
    public void testMaxClimb(){
        final int[] ladderMoves= new int[]{4,25,13,46,33,49,42,63,50,69,62,81,74,92};
        int maxClimb=stats.getBiggestClimbInSingleRun(ladderMoves);
        Assertions.assertEquals( 33,maxClimb);
    }

    @Test
    public void testMaxSlides(){
        final int[] snakeMoves = new int[]{27,5,40,3,43,18,54,31,66,45,76,58,89,53,99,41};
        int maxSlides = stats.getBiggestSlideInSingleRun(snakeMoves);
        Assertions.assertEquals(58,maxSlides);
    }

}
