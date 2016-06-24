package com.jxf.car.export.user;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;

import com.jxf.common.base.BaseExport;

public class UserBorrowExport extends BaseExport {

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
		Label label = new Label(0, 0, "用户提现数据列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "提现金额", wctB));
		wsheet.addCell(new Label(column++, 1, "分期数", wctB));
		wsheet.addCell(new Label(column++, 1, "银行类型", wctB));
		wsheet.addCell(new Label(column++, 1, "型号卡号", wctB));
		wsheet.addCell(new Label(column++, 1, "状态", wctB));
		wsheet.addCell(new Label(column++, 1, "申请提现时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);

				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("mobilePhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("cost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("aging")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("bankName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("cardNumber")), wcsB));
				wsheet.addCell(new Label(column++, i + 2,
						getBillType(toString(map.get("status"))), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createTime")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "用户提现数据列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

	private static String getBillType(String type) {
		switch (type) {
		case "1":
			return "审核中";
		case "2":
			return "已通过";
		case "3":
			return "未通过";
		default:
			return "状态错误";
		}
	}

}
