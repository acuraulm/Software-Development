package acu.assignment2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import acu.project1.Assignment2Application;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Assignment2Application.class)
@WebAppConfiguration
public class Assignment2ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
