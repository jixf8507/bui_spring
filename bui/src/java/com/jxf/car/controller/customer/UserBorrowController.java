package com.jxf.car.controller.customer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.controller.BaseController;
import com.jxf.car.model.UserBorrow;
import com.jxf.car.service.customer.UserBorrowService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;

@Controller
@RequestMapping("/user/borrow")
public class UserBorrowController extends BaseController {

	@Autowired
	private UserBorrowService borrowService;

	@RequestMapping("list")
	public String list() {
		return "user/userBorrowList";
	}

	@RequestMapping("checkList")
	public String checkList() {
		return "user/userBorrowCheckList";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = borrowService.findUserBorrowPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		borrowService.excelUserBorrow(response, jsonObject);
		return null;
	}

	@RequestMapping("detail")
	public String detail(Integer id, Model model) {
		model.addAttribute("userBorrow", borrowService.getUserBorrowMap(id));
		return "user/userBorrowDetail";
	}

	@RequestMapping("checkDetail")
	public String checkDetail(Integer id, Model model) {
		model.addAttribute("userBorrow", borrowService.getUserBorrowMap(id));
		return "user/userBorrowCheckDetail";
	}

	@RequestMapping("submitCheck.htm")
	@ResponseBody
	public MSG submitCheck(UserBorrow userBorrow, HttpSession session)
			throws Exception {
		userBorrow.setCheckMen(this.getSesseionUser(session).getName());
		return borrowService.submitCheckOrder(userBorrow);
	}

}
