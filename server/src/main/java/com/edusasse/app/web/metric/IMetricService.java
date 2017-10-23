package com.edusasse.app.web.metric;

import java.util.Map;

public interface IMetricService {

    void increaseCount(final String request, final int status);

    Map getFullMetric();

    Map getStatusMetric();

    Object[][] getGraphData();
}
