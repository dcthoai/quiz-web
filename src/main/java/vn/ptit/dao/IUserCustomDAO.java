package vn.ptit.dao;

import vn.ptit.model.UserCustom;

public interface IUserCustomDAO extends IGenericDAO<UserCustom>{
	public UserCustom getUserByUsername(String username);
}