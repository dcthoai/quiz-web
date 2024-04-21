package vn.ptit.dao;

import java.util.List;

import vn.ptit.model.ResultAnswer;

public interface IResultAnswerDAO extends IGenericDAO<ResultAnswer>{
	public List<ResultAnswer> getAnswersByResultId(int resultId);
}
