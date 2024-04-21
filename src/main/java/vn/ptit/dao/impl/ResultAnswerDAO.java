package vn.ptit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.ptit.dao.IResultAnswerDAO;
import vn.ptit.model.ResultAnswer;

@Repository
public class ResultAnswerDAO extends AbstractDAO<ResultAnswer> implements IResultAnswerDAO{

	@Override
	public int insert(ResultAnswer object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ResultAnswer object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultAnswer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResultAnswer> getAnswersByResultId(int resultId) {
		// TODO Auto-generated method stub
		return null;
	}
}
