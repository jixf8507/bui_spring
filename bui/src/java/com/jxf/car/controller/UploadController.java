package com.jxf.car.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jxf.car.model.SysFileUrlsPO;
import com.jxf.car.service.UploadService;
import com.jxf.car.web.MSG;
import com.jxf.common.tools.MyDateUtil;
import com.jxf.common.tools.StringTools;

/**
 * 文件上传
 * 
 * @author jixf
 * @date 2015年12月25日
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

	@Autowired
	private UploadService uploadService;

	/**
	 * 上传图片页面
	 * 
	 * @param paraData
	 * @return
	 */
	@RequestMapping(value = "/uploadImages.htm")
	public String uploadImages(Model model, HttpServletRequest request) {
		model.addAttribute("pathType",
				StringTools.decodeMethod(request.getParameter("pathType")));
		model.addAttribute("fileType",
				StringTools.decodeMethod(request.getParameter("fileType")));
		model.addAttribute("tableId",
				StringTools.decodeInteger(request.getParameter("tableId")));
		model.addAttribute("tableName",
				StringTools.decodeMethod(request.getParameter("tableName")));
		return "upload/uploadImages";
	}

	@RequestMapping(value = "/saveUpload.htm")
	@ResponseBody
	public MSG saveUpload(@RequestParam("file") MultipartFile file,
			Integer carId, HttpServletRequest request) {
		MSG msg = new MSG();
		String pathType = StringTools.decodeMethod(request
				.getParameter("pathType"));
		String fileType = StringTools.decodeMethod(request
				.getParameter("fileType"));
		Integer tableId = StringTools.decodeInteger(request
				.getParameter("tableId"));
		String tableName = StringTools.decodeMethod(request
				.getParameter("tableName"));
		try {
			logger.info("------------->上传图片: " + file.getOriginalFilename());
			String fileUrl = copyFile(file, pathType, request);
			SysFileUrlsPO fileUrlsPO = new SysFileUrlsPO();
			fileUrlsPO.setFileName(file.getOriginalFilename());
			fileUrlsPO.setFileType(fileType);
			fileUrlsPO.setTableId(tableId);
			fileUrlsPO.setTableName(tableName);
			fileUrlsPO.setFileUrl(fileUrl);
			// 保存图片
			msg.setInfo(uploadService.saveFile(fileUrlsPO));

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setInfo("上传文件保存失败");
		}
		return msg;
	}

	/**
	 * 复制文件
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	private String copyFile(MultipartFile file, String pathType,
			HttpServletRequest request) {
		ServletContext sc = request.getSession().getServletContext();
		String filePath = "/upload/" + pathType + "/" + MyDateUtil.getCurDate()
				+ "/";
		String dir = sc.getRealPath(filePath);
		String fileName = file.getOriginalFilename();
		String extendName = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID() + extendName;
		File targetDir = new File(dir);
		File targetFile = new File(dir, newFileName);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileUrl = filePath + newFileName;
		return fileUrl;
	}

}
