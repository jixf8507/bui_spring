package com.jxf.bui;

import java.util.List;

public class MenuItem {

	private String id;
	private String text;
	private String href;
	private boolean closeable = true;

	private List<MenuItem> items;

	public MenuItem() {
		super();
	}

	public static MenuItem createMenuItem(String id, String text, String href) {
		MenuItem mi = new MenuItem();
		mi.id = id;
		mi.text = text;
		mi.href = href;
		return mi;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean getCloseable() {
		return closeable;
	}

	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

}
