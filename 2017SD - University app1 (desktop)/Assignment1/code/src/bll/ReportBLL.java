package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dal.ReportDAO;
import entities.Report;


public class ReportBLL {

	private List<Validator<Report>> validators;

	public ReportBLL() {
		validators = new ArrayList<Validator<Report>>();
	//	validators.add(new EmailValidatorReport());
	//	validators.add(new ReportAgeValidator());
	}

	public Report findReportById(int id) {
		Report st = ReportDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The report with id =" + id + " was not found!");
		}
		return st;
	}
	public List<Report> findAllReportsByStudentId(int idStud) throws SQLException{
		return ReportDAO.findAllByStudentId(idStud);
	}
	public List<Report> findAllReports() throws SQLException{
		return ReportDAO.findAll();
	}

	public int insertReport(Report report) {
		for (Validator<Report> v : validators) {
			v.validate(report);
		}
		return ReportDAO.insert(report);
	}
}
