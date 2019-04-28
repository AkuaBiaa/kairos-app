package kairos.poc.web.kairoswebpoc.service;

import kairos.poc.web.kairoswebpoc.pojo.Points;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: banhu
 * Description: kairos points service receives points requests, and stores the points through {@link PointStorageServiceImp}
 * information of points accumulated is also stored in the totalPoints and pointsDetail.
 */
@RestController
@RequestMapping("pointService/")
public class PointResourceService {
    private static Logger logger = LoggerFactory.getLogger(PointResourceService.class);
    private int totalPoints;
    private Points pointsDetail;
    private PointStorageServiceImp storageServiceImp;
    private PersistPointsToFile persistPointsToFile;
    /**
     * TODO: 8/22/2018 Use pojo w/ getter nad setters to use JSON Object - point,task,time
     * @site: https://stackoverflow.com/questions/37841373/java-lang-illegalargumentexception-no-converter-found-for-return-value-of-type
     */
    // TODO: 10/20/2018 Refactor or add a seperate resource for points details, and one for returning total points
    @GetMapping("/fetchPoints")
    public String fetchPoints() {
        JSONObject jsonPoints = new JSONObject("{\"points\":\"" + String.valueOf(totalPoints) + "\"}");
        System.out.println("Completed @Get Endpoint: Returned " + totalPoints);
        return jsonPoints.toString();
    }

    @CrossOrigin
    @GetMapping("/fetchPointDetails")
    public List<Points> fetchPointsDetail() {
        logger.info("Fetching static pointDetails resource...");
        return fetchMockPointDetailList();
    }

    @PutMapping("/savePoints/{points}")
    public int savePoints(@PathVariable String points) {
        totalPoints += Integer.parseInt(points);
        System.out.println("Completed @Put Endpoint: Current points " + totalPoints);
        return totalPoints;
    }

    // FIXME: 11/4/2018 Endpoint for pojo of points details from UI
    @PutMapping("/savePointsDetail/{points}")
    public void savePoints(@PathVariable Points points) {
        totalPoints += points.getPoints();
        System.out.println("Completed @Put Endpoint: Current points " + totalPoints);
        System.out.println("Saving points detail...");
        storageServiceImp.savePoints(points);
        System.out.println("Persisting points to file....");
        persistPointsToFile.persistPointsIntoTextFile(storageServiceImp.getPointsDetail());
    }

    public void setPointsDetail(Points pointsDetail) {
        this.pointsDetail = pointsDetail;
    }

    public Points getPointsDetail() {
        return pointsDetail;
    }

    public  List<Points> fetchMockPointDetailList(){
        List<Points> pointDetails = new ArrayList<>();
        pointDetails.add(new Points(10, "AA,CC,DD", new Date().toString()));
        pointDetails.add(new Points(30, "BB,OO,EE", new Date().toString()));
        pointDetails.add(new Points(20, "KK,WW,ZZ", new Date().toString()));
        return pointDetails;
    }

    public void setStorageServiceImp(PointStorageServiceImp storageServiceImp) {
        this.storageServiceImp = storageServiceImp;
    }

    public void setPersistPointsToFile(PersistPointsToFile persistPointsToFile) {
        this.persistPointsToFile = persistPointsToFile;
    }
}
