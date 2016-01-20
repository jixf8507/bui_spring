package com.jxf.car.export.user;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;

import com.jxf.common.base.BaseExport;

public class UserOrderExport extends BaseExport {

	public static void createExcel(HttpServletResponse response,
			List<Map<String, Object>> list) throws Exception {
		BaseExport be = new UserOrderExport();
		be.toExcel(response, list);
	}

	@Override
	protected void writeTile() throws Exception {
		int column = 0;
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "用户消费信息列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "订单号", wctB));
		wsheet.addCell(new Label(column++, 1, "用户姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "商品名称", wctB));
		wsheet.addCell(new Label(column++, 1, "价格", wctB));
		wsheet.addCell(new Label(column++, 1, "商家", wctB));
		wsheet.addCell(new Label(column++, 1, "首付金额", wctB));
		wsheet.addCell(new Label(column++, 1, "类型", wctB));
		wsheet.addCell(new Label(column++, 1, "消费时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("id")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("userName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("mobilePhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("goodsName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("price")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("merchantName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("sfMoney")), wcsB));
				wsheet.addCell(new Label(column++, i + 2,
						getGoodsType(toString(map.get("type"))), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createTime")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "用户消费信息列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

	private static String getGoodsType(String type) {
		switch (type) {
		case "1":
			return "大马花消费";
		case "2":
			return "商家消费";
		default:
			return "其它";
		}
	}

}
