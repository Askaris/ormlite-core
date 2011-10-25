package com.j256.ormlite.field.types;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.table.DatabaseTable;

public class DateTypeTest extends BaseTypeTest {

	private static final String DATE_COLUMN = "date";

	@Test
	public void testDate() throws Exception {
		Class<LocalDate> clazz = LocalDate.class;
		Dao<LocalDate, Object> dao = createDao(clazz, true);
		Date val = new Date();
		String format = "yyyy-MM-dd HH:mm:ss.SSSSSS";
		DateFormat dateFormat = new SimpleDateFormat(format);
		String valStr = dateFormat.format(val);
		LocalDate foo = new LocalDate();
		foo.date = val;
		assertEquals(1, dao.create(foo));
		testType(dao, foo, clazz, val, val, val, valStr, DataType.DATE, DATE_COLUMN, false, true, true, false, true,
				false, true, false);
	}

	@Test
	public void testDateNull() throws Exception {
		Class<LocalDate> clazz = LocalDate.class;
		Dao<LocalDate, Object> dao = createDao(clazz, true);
		LocalDate foo = new LocalDate();
		assertEquals(1, dao.create(foo));
		testType(dao, foo, clazz, null, null, null, null, DataType.DATE, DATE_COLUMN, false, true, true, false, true,
				false, true, false);
	}

	@Test(expected = SQLException.class)
	public void testDateParseInvalid() throws Exception {
		FieldType fieldType =
				FieldType.createFieldType(connectionSource, TABLE_NAME, LocalDate.class.getDeclaredField(DATE_COLUMN),
						LocalDate.class);
		DataType.DATE.getDataPersister().parseDefaultString(fieldType, "not valid date string");
	}

	@DatabaseTable(tableName = TABLE_NAME)
	protected static class LocalDate {
		@DatabaseField(columnName = DATE_COLUMN)
		Date date;
	}
}