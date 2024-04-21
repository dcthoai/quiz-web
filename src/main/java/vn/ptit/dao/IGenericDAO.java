package vn.ptit.dao;

public interface IGenericDAO<T> {
	public int insert(T object);
	public int update(T object);
	public int delete(int id);
	public T getById(int id);
}