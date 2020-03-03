package com.datasource;


import java.util.ArrayList;
import java.util.List;

import com.domain.DomainObject;

public class UnitOfWork {
	@SuppressWarnings("rawtypes")
	private static ThreadLocal current = new ThreadLocal();

	private List<DomainObject> newObjects = new ArrayList<DomainObject>();
	private List<DomainObject> dirtyObjects = new ArrayList<DomainObject>();
	private List<DomainObject> deletedObjects = new ArrayList<DomainObject>();

	public static void newCurrent() {
		setCurrent(new UnitOfWork());
	}

	@SuppressWarnings("unchecked")
	public static void setCurrent(UnitOfWork uow) {
		current.set(uow);
	}

	public static UnitOfWork getCurrent() {
		return (UnitOfWork) current.get();
	}

	public void registerNew(DomainObject obj) {
	 assert obj.getId()!=null: "id is null";
	 assert !dirtyObjects.contains(obj): "object is deleted";
	 assert !deletedObjects.contains(obj): "object is deleted";
	 assert !newObjects.contains(obj): "object is new";
	 newObjects.add(obj);
	 }

	public void registerDirty(DomainObject obj) {
		assert obj.getId()!=null: "id is null";
		assert !deletedObjects.contains(obj): "object is deleted";
		if (!dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
			dirtyObjects.add(obj);
		}
	}

	public void registerDeleted(DomainObject obj) {
		assert obj.getId()!=null: "id is null";
		if (newObjects.remove(obj))
			return;
		dirtyObjects.remove(obj);
		if (!deletedObjects.contains(obj)) {
			deletedObjects.add(obj);
		}
	}

	public void registerClean(Object obj) {
	}

	public void commit() {
		try {
			for (DomainObject obj : newObjects) {

				IDataMapper.getMapper(obj.getClass()).create(obj);

			}
			for (Object obj : dirtyObjects) {
				IDataMapper.getMapper(obj.getClass()).update(obj);
			}
			for (Object obj : deletedObjects) {
				IDataMapper.getMapper(obj.getClass()).delete(obj);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
