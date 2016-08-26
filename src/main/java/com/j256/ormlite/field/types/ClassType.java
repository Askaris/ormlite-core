package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

import java.sql.SQLException;

/**
 * Type that persists a Class object.
 * 
 * @author graywatson
 */
public class ClassType extends BaseDataType
{

	public static int DEFAULT_WIDTH = 255;

	private static final ClassType singleTon = new ClassType();

	public static ClassType getSingleton() {
		return singleTon;
	}

	private ClassType() {
		super(SqlType.STRING, new Class<?>[] { Class.class });
	}

	protected ClassType(SqlType sqlType, Class<?>[] classes) {
		super(sqlType, classes);
	}

	protected ClassType(SqlType sqlType) {
		super(sqlType);
	}

	@Override
	public int getDefaultWidth() {
		return DEFAULT_WIDTH;
	}

	@Override
	public Object parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException
	{
		return defaultStr;
	}

	@Override
	public Object resultStringToJava(FieldType fieldType, String stringValue, int columnPos) throws SQLException
	{
		try {
			return Class.forName(stringValue);
		} catch (ClassNotFoundException cnfex) {
			throw new SQLException("Could not provide Class instance for class name '" + stringValue + "'", cnfex);
		}
	}

	@Override
	public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException
	{
		return results.getString(columnPos);
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException
	{
		return ((Class)javaObject).getName();
	}

	@Override
	public Object resultToJava(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException
	{
		String fqcn = results.getString(columnPos);
		try {
			return Class.forName(fqcn);
		} catch (ClassNotFoundException cnfex) {
			throw new SQLException("Could not provide Class instance for class name '" + fqcn + "'", cnfex);
		}
	}
}
