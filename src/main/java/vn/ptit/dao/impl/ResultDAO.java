package vn.ptit.dao.impl;

import java.util.List;

import vn.ptit.dao.IResultDAO;
import vn.ptit.model.Result;

public class ResultDAO extends AbstractDAO<Result> implements IResultDAO{

	@Override
	public int insert(Result object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Result object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Result getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getResultsByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getResultsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getResultsByExamId(int examId) {
		// TODO Auto-generated method stub
		return null;
	}
}
