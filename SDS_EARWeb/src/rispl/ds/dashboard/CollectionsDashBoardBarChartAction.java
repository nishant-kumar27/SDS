package rispl.ds.dashboard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.dispatcher.SessionMap;

import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.collection.lookup.dao.CollectionDAOBeanIfc;
import rispl.dkart.collection.lookup.dao.CollectionData;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class CollectionsDashBoardBarChartAction extends DSAction {

	private static final long serialVersionUID = 1L;
	/* private static final long DIVISION_HEAD=7; */

	private String range;
	private EmployeeIfc employee;
	CollectionDAOBeanIfc dao;
	private ArrayList<BigDecimal> collections;
	private ArrayList<CollectionData> collections_data;
	private BigDecimal collection_total_amount;
	private ArrayList<String> labels;
	//private SessionMap<String, Object> sessionmap;

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public BigDecimal getCollection_total_amount() {
		return collection_total_amount;
	}

	public void setCollection_total_amount(BigDecimal collection_total_amount) {
		this.collection_total_amount = collection_total_amount;
	}

	public ArrayList<String> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}

	public ArrayList<BigDecimal> getCollections() {
		return collections;
	}

	public void setCollections(ArrayList<BigDecimal> collections) {
		this.collections = collections;
	}

	public ArrayList<CollectionData> getCollections_data() {
		return collections_data;
	}

	public void setCollections_data(ArrayList<CollectionData> collections_data) {
		this.collections_data = collections_data;
	}

	public CollectionsDashBoardBarChartAction() {

		range = "quarter";
	}

	public String execute() {
		employee = super.getEmployee();
		//sessionmap = getSessionmap();
		collection_total_amount = new BigDecimal(0);
		collections = new ArrayList<>();
		labels = new ArrayList<>();
		// int division=-1;
		List<Integer> division = new ArrayList();
		if (employee.getMerchAssoc() != null) {
			for (EmpMerchAssociationIfc merchID : employee.getMerchAssoc()) {
				String id = merchID.getMerchId();
				String[] splitId = id.split(":");
				if (splitId[0].equals("1"))
					// division=Integer.parseInt(splitId[1]);
					division.add(Integer.parseInt(splitId[1]));
			}
		}
		try {
			dao = DKartContext.getCollectionDAOBean();
			Long DIVISION_HEAD = Long
					.valueOf(DKartContext.getParamterBean().fetchXMLParameterValues().getDivisionHeadRoleID());
			/* if(employee.getRoleId()==DIVISION_HEAD && division!=-1){ */
			if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
				collections_data = dao.getCollectionChartDataByDivision(division, range);
			} else if(employee.getRoleAccess().equalsIgnoreCase("All")){
				collections_data = dao.getCollectionChartDataByAllDiv(range);
			}else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")){
				collections_data = dao.getCollectionChartData(employee.getEmployeeId(), range);
			}
			if (collections_data != null) {
				putInSession(SESSION.COLLECTION_DATA, collections_data);
				for (CollectionData temp : collections_data) {
					collection_total_amount = collection_total_amount.add(temp.getTotal());
					collections.add(temp.getTotal());
					labels.add(temp.getLabel());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
