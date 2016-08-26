package com.j256.ormlite.field.types;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassTypeTest extends BaseTypeTest {

	private static final String CLASS_COLUMN = "klass";

	@Test
	public void testClass() throws Exception {
		Class<LocalClass> clazz = LocalClass.class;
		Dao<LocalClass, Object> dao = createDao(clazz, true);
		Class val = TheClass.class;
		LocalClass foo = new LocalClass();
		foo.klass = val;
		assertEquals(1, dao.create(foo));
		testType(dao, foo, clazz, val, null, null, null, DataType.CLASS, CLASS_COLUMN, false, true, true, false,
				false, false, true, false);
	}

	@DatabaseTable(tableName = TABLE_NAME)
	protected static class LocalClass
	{
		@DatabaseField(columnName = CLASS_COLUMN)
		Class klass;
	}

	private static class TheClass { }
}
