package kairos.poc.web.kairoswebpoc.service;

import kairos.poc.web.kairoswebpoc.pojo.Points;

public interface PointStorageService {
    /**
     * Stores the points
     * @param points
     */
    public void savePoints(Points points);
}
