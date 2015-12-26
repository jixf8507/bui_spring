package com.jxf.highcharts;

import java.util.ArrayList;
import java.util.List;

public class ChartsBO <DT extends Object>{
	private AxisBO xAxis;
	private List<SerieBO<DT>> series;

	public ChartsBO() {
		this.xAxis = new AxisBO();
		this.series = new ArrayList<>();
	}

	public AxisBO getxAxis() {
		return xAxis;
	}

	public void setxAxis(AxisBO xAxis) {
		this.xAxis = xAxis;
	}

	public List<SerieBO<DT>> getSeries() {
		return series;
	}

	public void setSeries(List<SerieBO<DT>> series) {
		this.series = series;
	}
	
	
	
	@Override
	public String toString() {
		return "ChartsBO [xAxis=" + xAxis + ", series=" + series + "]";
	}
}
