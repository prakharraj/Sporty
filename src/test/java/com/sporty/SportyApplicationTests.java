package com.sporty;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class SportyApplicationTests {

	@Test
	void testContextLoads() {
		assertDoesNotThrow(() -> SportyApplication.main(new String[] {}));
	}

}
