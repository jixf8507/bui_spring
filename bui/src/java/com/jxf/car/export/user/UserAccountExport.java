package com.jxf.car.export.user;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;

import com.jxf.car.model.UserAccount;
import com.jxf.common.base.BaseExport;

public class UserAccountExport extends BaseExport {

	public static void createExcel(HttpServletResponse response,
			List<Map<String, Object>> list) throws Exception {
		BaseExport be = new UserAccountExport();
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "用户账户信息列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);

		wsheet.addCell(new Label(column++, 1, "姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "身份证", wctB));
		wsheet.addCell(new Label(column++, 1, "可用额度", wctB));
		wsheet.addCell(new Label(column++, 1, "白条额度", wctB));
		wsheet.addCell(new Label(column++, 1, "账户余额", wctB));
		wsheet.addCell(new Label(column++, 1, "状态", wctB));
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
						.get("idCard")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("usableLimit")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("whiteBarLimit")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("balance")), wcsB));
				String accountStatus = toString(map.get("accountStatus"));
				wsheet.addCell(new Label(column++, i + 2, UserAccount
						.getStatus(accountStatus), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "用户账户信息列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
