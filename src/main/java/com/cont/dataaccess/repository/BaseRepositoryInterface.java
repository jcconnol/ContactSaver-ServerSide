package com.cont.dataaccess.repository;

import java.sql.Connection;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Consumer;

import com.cont.dataaccess.entities.BaseEntity;

public interface BaseRepositoryInterface<T extends BaseEntity<T>> {
	int count();
	T get(String id);
	Collection<T> all();
	boolean exists(String id);
	String getPrimaryTableName();
	void saveMany(Collection<T> allToSave);
	Collection<T> getMany(Collection<String> ids);
	void deleteMany(Collection<T> allToDelete);
	Collection<T> inRange(int limit, int offset);
	void connectAndRun(Consumer<Connection> action);
}
