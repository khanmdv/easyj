/**
 * 
 */
package com.easyJ.junit;

import java.io.Serializable;

/**
 * @author khan.m
 *
 */
public class Employee implements Cloneable, Serializable{

	private int employeeId;
	private String name;
	private String sex;
	private long salary;
	private String type;
	
	public String alias;
	
	/**
	 * @param employeeId
	 * @param name
	 * @param sex
	 * @param salary
	 * @param type
	 */
	public Employee(int employeeId, String name, String sex, long salary,
			String type) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.sex = sex;
		this.salary = salary;
		this.type = type;
		this.alias = name;
	}


	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}


	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}


	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}


	/**
	 * @return the salary
	 */
	public long getSalary() {
		return salary;
	}


	/**
	 * @param salary the salary to set
	 */
	public void setSalary(long salary) {
		this.salary = salary;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Employee emp2 = (Employee)obj;
		return this.employeeId == emp2.employeeId;
	}
	
	

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}


	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String toString()
	{
		return "" + this.getEmployeeId();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
