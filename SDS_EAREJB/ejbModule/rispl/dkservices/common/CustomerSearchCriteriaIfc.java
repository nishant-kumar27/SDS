package rispl.dkservices.common;

import java.io.Serializable;
import java.util.List;

import rispl.dk.Employee.EmployeeIfc;

public interface CustomerSearchCriteriaIfc extends Serializable{

	
	public String getCustomerId() ;

	public void setCustomerId(String customerId) ;

	public String getFirstName();
	
	public void setFirstName(String firstName);

	public String getLastName();
	
	public void setLastName(String lastName) ;

	public String getStoreId() ;

	public void setStoreId(String storeId) ;

	public String getTelephoneNumber();

	public void setTelephoneNumber(String telephoneNumber) ;
	
	public List<String> getDivisionIdFilter();
	
	public void setDivisionIdFilter(List<String> divisionIds);
	
	public boolean isWildCardSearch();
	
	public void setWildCardSearch(boolean isWildCardSearch);

	public void setMaxCustomers(Integer maxCustomers);
	
	public Integer getMaxCustomers();

	public void setEmployee(EmployeeIfc employee);
	
	public EmployeeIfc getEmployee();
}
