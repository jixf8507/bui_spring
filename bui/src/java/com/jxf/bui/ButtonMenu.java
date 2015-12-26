package com.jxf.bui;

public class ButtonMenu {

	private String value;
	private String onclick;

	public ButtonMenu() {

	}

	public ButtonMenu(String value, String onclick) {
		super();
		this.value = value;
		this.onclick = onclick;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

}
