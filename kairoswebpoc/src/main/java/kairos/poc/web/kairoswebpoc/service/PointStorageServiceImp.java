package kairos.poc.web.kairoswebpoc.service;

import kairos.poc.web.kairoswebpoc.pojo.Points;

import java.util.ArrayList;
import java.util.List;

public class PointStorageServiceImp implements PointStorageService{
    private PointResourceService pointResourceService;
    private List<Points> pointsDetail = new ArrayList<>();

    @Override
    public void savePoints(Points points) {
        pointsDetail.add(points);
    }

    public void setPointResourceService(PointResourceService pointResourceService) {
        this.pointResourceService = pointResourceService;
    }

    public List<Points> getPointsDetail() {
        return pointsDetail;
    }

    public PointResourceService getPointResourceService() {
        return pointResourceService;
    }
}
