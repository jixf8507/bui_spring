package com.jxf.bui;

public class ButtonMenu {

	private String value;
	private String onclick;

	public ButtonMenu() {

	}

	public static ButtonMenu createButtonMenu(String value, String onclick) {
		ButtonMenu bm = new ButtonMenu();
		bm.value = value;
		bm.onclick = onclick;
		return bm;
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
