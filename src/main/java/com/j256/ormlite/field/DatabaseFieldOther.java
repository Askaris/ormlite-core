package com.j256.ormlite.field;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.j256.ormlite.field.types.VoidType;

/**
 * This can be used instead of {@link DatabaseField}. This adds fields to the {@link DatabaseFieldSimple} annotations
 * which _must_ also be specified for this annotation to be detected.
 * 
 * @author graywatson
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface DatabaseFieldOther {

	/**
	 * @see DatabaseField#dataType()
	 */
	DataType dataType() default DataType.UNKNOWN;

	/**
	 * @see DatabaseField#unknownEnumName()
	 */
	String unknownEnumName() default "";

	/**
	 * @see DatabaseField#throwIfNull()
	 */
	boolean throwIfNull() default false;

	/**
	 * @see DatabaseField#format()
	 */
	String format() default "";

	/**
	 * @see DatabaseField#persisterClass()
	 */
	Class<? extends DataPersister> persisterClass() default VoidType.class;

	/**
	 * @see DatabaseField#useGetSet()
	 */
	boolean useGetSet() default false;

	/**
	 * @see DatabaseField#columnDefinition()
	 */
	String columnDefinition() default "";

	/**
	 * @see DatabaseField#version()
	 */
	boolean version() default false;
}