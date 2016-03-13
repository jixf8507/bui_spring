package com.jxf.car.export.user;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;

import com.jxf.common.base.BaseExport;

public class UserBillDetailExport extends BaseExport {

	public static void createExcel(HttpServletResponse response,
			List<Map<String, Object>> list) throws Exception {
		BaseExport be = new UserMonthBillExport();
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "用户分期账单明细列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "分期还款日期", wctB));
		wsheet.addCell(new Label(column++, 1, "姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "本金", wctB));
		wsheet.addCell(new Label(column++, 1, "利息", wctB));
		wsheet.addCell(new Label(column++, 1, "总还款金额", wctB));
		wsheet.addCell(new Label(column++, 1, "账单状态", wctB));
		wsheet.addCell(new Label(column++, 1, "创建时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("repaymentTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("mobilePhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("capital")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("interest")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("totleCost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2,
						getBillType(toString(map.get("status"))), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "用户分期账单明细列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

	private static String getBillType(String type) {
		switch (type) {
		case "0":
			return "未还清";
		case "1":
			return "已还清";
		default:
			return "未形成还款账单";
		}
	}

}
