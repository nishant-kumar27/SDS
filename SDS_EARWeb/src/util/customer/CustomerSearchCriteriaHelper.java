package util.customer;

import java.util.ArrayList;
import java.util.List;

import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;

public class CustomerSearchCriteriaHelper {

	private static CustomerSearchCriteriaHelper helper;

	public static CustomerSearchCriteriaHelper getInstance() {
		if (helper == null)
			helper = new CustomerSearchCriteriaHelper();

		return helper;
	}

	public List<String> getDivisionsFromEmp(EmployeeIfc employee) {
		if (employee != null && employee.getMerchAssoc() != null) {
			List<String> divisionIds = new ArrayList<String>();
			for(EmpMerchAssociationIfc empMerchAssoc : employee.getMerchAssoc())
			{
				//TODO check merch type if implemented
				divisionIds.add(empMerchAssoc.getMerchId());
			}
			return divisionIds;
		}
		return null;
	}
}
