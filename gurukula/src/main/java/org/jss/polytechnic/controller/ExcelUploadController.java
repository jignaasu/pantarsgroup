package org.jss.polytechnic.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.jss.polytechnic.bean.BoardResult;
import org.jss.polytechnic.dao.ResultDao;
import org.jss.polytechnic.web.ExcelUtility;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "excelUploadController")
@RequestScoped
public class ExcelUploadController {

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("Inside upload button ");
		List<BoardResult> resultList = null;
		try {
			resultList = ExcelUtility.parseResultSheet(event.getFile()
					.getInputstream(),
					event.getFile().getFileName().endsWith("xlsx"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ResultDao dao = new ResultDao();
		boolean isSuccessful = dao.insertExamResults(resultList);
		if (isSuccessful) {
			FacesMessage msg = new FacesMessage("Successful", event.getFile()
					.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Unsuccessful", event.getFile()
					.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

}
