package com.jxf.bui;

import java.util.List;
import java.util.Map;

public class BuiMenu {

	private List<ConfigMenu> configMenus;
	private Map<String, List<ButtonMenu>> buttomMap;

	public BuiMenu() {

	}

	public BuiMenu(List<ConfigMenu> configMenus,
			Map<String, List<ButtonMenu>> buttomMap) {
		super();
		this.configMenus = configMenus;
		this.buttomMap = buttomMap;
	}

	public List<ConfigMenu> getConfigMenus() {
		return configMenus;
	}

	public void setConfigMenus(List<ConfigMenu> configMenus) {
		this.configMenus = configMenus;
	}

	public Map<String, List<ButtonMenu>> getButtomMap() {
		return buttomMap;
	}

	public void setButtomMap(Map<String, List<ButtonMenu>> buttomMap) {
		this.buttomMap = buttomMap;
	}

}
