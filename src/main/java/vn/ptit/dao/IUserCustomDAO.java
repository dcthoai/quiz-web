package vn.ptit.dao;

import java.util.List;

import vn.ptit.model.UserCustom;

public interface IUserCustomDAO extends IGenericDAO<UserCustom>{
	public UserCustom getUserByUsername(String username);
	public List<UserCustom> getAllStudents();
}