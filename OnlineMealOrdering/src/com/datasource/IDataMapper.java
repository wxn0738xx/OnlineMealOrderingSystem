package com.datasource;

public interface IDataMapper {
	public void create(Object obj);
	public void update(Object obj);
	public void delete(Object obj);
	public Object find(Object obj); 

	/**
	 * 
	 * @param c
	 * @return corresponding datamapper accroding to the domian class
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static IDataMapper getMapper(Class c) throws InstantiationException, IllegalAccessException {
		IDataMapper mapper = null;
		if(c==com.domain.Dish.class)
			mapper = new DishMapper();
		if(c==com.domain.Menu.class)
			mapper = new MenuMapper();
		return mapper;
		
	}

}
