package com.jxf.car.export.mechant;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;

import com.jxf.car.model.MerchantDrawMoney;
import com.jxf.common.base.BaseExport;

public class MerchantDrawMoneyExport extends BaseExport {

	public static void createExcel(HttpServletResponse response,
			List<Map<String, Object>> list) throws Exception {
		BaseExport be = new MerchantExport();
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
		Label label = new Label(0, 0, "商户提款记录列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "商户名称", wctB));
		wsheet.addCell(new Label(column++, 1, "商户编号", wctB));
		wsheet.addCell(new Label(column++, 1, "提款金额", wctB));
		wsheet.addCell(new Label(column++, 1, "银行名称", wctB));
		wsheet.addCell(new Label(column++, 1, "银行卡号", wctB));
		wsheet.addCell(new Label(column++, 1, "状态", wctB));
		wsheet.addCell(new Label(column++, 1, "申请时间", wctB));
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
						.get("code")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("money")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("bankName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("cardNumber")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, MerchantDrawMoney
						.getStatus(toString(map.get("status"))), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "商户提款记录列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
