package com.sahaj.SnakeAndLadderApplication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.sahaj.SnakeAndLadderApplication.config.SnakeAndLadderApplicationConfiguration;

@SpringBootTest(classes = SnakeAndLadderApplicationConfiguration.class)
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class SnakeAndLadderApplicationTests {

	@Test
	void contextLoads() {
	}

}
