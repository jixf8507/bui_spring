package com.jxf.bui;

import java.util.List;

public class ConfigMenu {

	private String id;
	private String homePage;
	private String text;
	private List<MenuItem> menu;

	public ConfigMenu() {
		super();
	}

	public ConfigMenu(String id, String homePage, String text,
			List<MenuItem> menu) {
		super();
		this.id = id;
		this.homePage = homePage;
		this.text = text;
		this.menu = menu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public List<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
