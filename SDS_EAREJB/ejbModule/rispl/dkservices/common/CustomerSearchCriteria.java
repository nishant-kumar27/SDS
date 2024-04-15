package rispl.dkservices.common;

import java.util.List;

import rispl.dk.Employee.EmployeeIfc;

public class CustomerSearchCriteria implements CustomerSearchCriteriaIfc {

	private static final long serialVersionUID = 1L;

	String customerId;

	String firstName;

	String lastName;

	String storeId;

	String telephoneNumber;

	boolean isSearchByCustomerId;

	boolean isSearchByCustomerName;

	boolean isWildCardSearch = false;

	boolean isSearchByTelephoneNumber;

	private List<String> divisionIdFilter;

	private int maxCustomers;

	private EmployeeIfc employee;
	
	@Override
	public String getCustomerId() {
		return customerId;
	}

	@Override
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getStoreId() {
		return storeId;
	}

	@Override
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Override
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	@Override
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public List<String> getDivisionIdFilter() {
		return divisionIdFilter;
	}

	@Override
	public void setDivisionIdFilter(List<String> divisionIdFilter) {
		this.divisionIdFilter = divisionIdFilter;
	}
	
	@Override
	public boolean isWildCardSearch() {
		return isWildCardSearch;
	}

	@Override
	public void setWildCardSearch(boolean isWildCardSearch) {
		this.isWildCardSearch = isWildCardSearch;
	}

	@Override
	public void setMaxCustomers(Integer maxCustomers) {
		this.maxCustomers = maxCustomers;
		
	}

	public Integer getMaxCustomers() {
		return maxCustomers;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}
}
