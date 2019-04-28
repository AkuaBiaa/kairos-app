package kairos.poc.web.kairoswebpoc;

import kairos.poc.web.kairoswebpoc.pojo.Points;
import kairos.poc.web.kairoswebpoc.service.PointResourceService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PointGeneratorServiceTest {
	Logger logger = LoggerFactory.getLogger(PointGeneratorServiceTest.class);
	public static PointResourceService generatorService;
	@Before
	public void init(){
		generatorService = new PointResourceService();
	}
	@Test
	public void fetchMockPointDetailList(){
		List<Points> pointsList = generatorService.fetchMockPointDetailList();
		for (Points pointDetails : pointsList) {
			logger.info("Printing point data...");
			logger.info("points ==> {}",pointDetails.getPoints());
			logger.info("tasks ==> {}",pointDetails.getTasks());
			logger.info("time ==> {}",pointDetails.getTime());
		}
	}
}
