package com.edusasse.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edusasse.app.web.metric.IMetricService;

@Controller
public class MetricController {

	@Autowired
	private IMetricService metricService;

	public MetricController() {
		super();
	}

	// API - READ

	@RequestMapping("/graph")
	public String helloWorld(Model model) {
 		return "graph";
	}

	@RequestMapping(value = "/metric-graph-data", method = RequestMethod.GET)
	@ResponseBody
	public Object[][] getMetricData() {
		return metricService.getGraphData();
	}
}