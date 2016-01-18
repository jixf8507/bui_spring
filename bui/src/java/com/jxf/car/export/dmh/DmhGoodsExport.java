package com.jxf.car.export.dmh;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.jxf.car.model.DmhGoods;
import com.jxf.common.base.BaseExport;

public class DmhGoodsExport extends BaseExport {

	@Override
	protected void writeTile() throws Exception {
		int column = 0;
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "大马花商品信息列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "商品名称", wctB));
		wsheet.addCell(new Label(column++, 1, "价格", wctB));
		wsheet.addCell(new Label(column++, 1, "类型", wctB));
		wsheet.addCell(new Label(column++, 1, "是否首页显示", wctB));
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
						.get("price")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("type")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, DmhGoods
						.getTop(toString(map.get("isTop"))), wcsB));
				wsheet.addCell(new Label(column++, i + 2, DmhGoods
						.getStatus(toString(map.get("status"))), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "大马花商品信息列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
