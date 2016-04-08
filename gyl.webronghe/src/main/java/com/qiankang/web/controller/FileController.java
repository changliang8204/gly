package com.qiankang.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qiankang.web.data.GeneralRespData;

@Controller
public class FileController {

	/***************************************************
	 * URL: /rest/controller/upload upload(): receives files
	 * 
	 * @param request
	 *            : MultipartHttpServletRequest auto passed
	 * @param response
	 *            : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public GeneralRespData upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());

			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("D:/temp/files/" + mpf.getOriginalFilename()));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new GeneralRespData();
	}

	/***************************************************
	 * URL: /rest/controller/get/{value} get(): get file as an attachment
	 * 
	 * @param response
	 *            : passed by the server
	 * @param value
	 *            : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "viewFile", method = RequestMethod.GET)
	public void get(HttpServletResponse response, String fileId) {
		File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\jzg.jpg");
		try {
			FileInputStream fis = new FileInputStream(file);
			response.setContentType("image/jpg");
			response.setHeader("Content-disposition", "attachment; filename=jzg.jpg");
			FileCopyUtils.copy(fis, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
