package vn.ptit.dao;

import java.util.List;

import vn.ptit.model.Result;

public interface IResultDAO extends IGenericDAO<Result>{
	public List<Result> getResultsByUserId(int userId);
	public List<Result> getResultsByUsername(String username);
	public List<Result> getResultsByExamId(int examId);
}
