package rispl.ds.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.dispatcher.SessionMap;

import com.rispl.sds.parameter.service.ParameterException;
import com.test.entities.SdsCollectionsDashboard;

import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.collection.lookup.dao.CollectionDAOBeanIfc;
import rispl.dkart.collection.lookup.dao.CollectionData;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class CollectionsDashBoardAction extends DSAction {

	private static final long serialVersionUID = 1L;
	/*private static final long DIVISION_HEAD=7;*/
	private String range;
	private String x_axis_field;
	private EmployeeIfc employee;
	CollectionDAOBeanIfc dao;
	private List<SdsCollectionsDashboard> collections;
	//private SessionMap<String, Object> sessionmap;
	private int ischart;


	public CollectionsDashBoardAction(){
		if(this.range==null || this.range.isEmpty()){
			try {
				range=DKartContext.getParamterBean().fetchXMLParameterValues().getDashboardRange();
				if(range.contains("_")){
					range=DKartContext.getParamterBean().fetchXMLParameterValues().getDashboardRange().toLowerCase().split("_")[1];
				}
			} catch (ParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public List<SdsCollectionsDashboard> getCollections() {
		return collections;
	}

	public void setCollections(List<SdsCollectionsDashboard> collections) {
		this.collections = collections;
	}

	public String getRange() {
		return range;
	}

	
	
	public int getIschart() {
		return ischart;
	}

	public void setIschart(int ischart) {
		this.ischart = ischart;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getX_axis_field() {
		return x_axis_field;
	}

	public void setX_axis_field(String x_axis_field) {
		this.x_axis_field = x_axis_field;
	}
	
	public String execute() {
		employee = super.getEmployee();
		//sessionmap=getSessionmap();
		/*int division=-1;*/
		List<Integer> division=new ArrayList();
		if(employee.getMerchAssoc()!=null){
			for(EmpMerchAssociationIfc merchID:employee.getMerchAssoc()){
				String id=merchID.getMerchId();
				String[] splitId=id.split(":");
				if(splitId[0].equals("1"))
					//division=Integer.parseInt(splitId[1]);
					division.add(Integer.parseInt(splitId[1]));
			}
		}
		try {
			@SuppressWarnings("unchecked")
			ArrayList<CollectionData> collection_data=(ArrayList<CollectionData>)getFromSession(SESSION.COLLECTION_DATA);
			dao = DKartContext.getCollectionDAOBean();
			Long DIVISION_HEAD = Long
					.valueOf(DKartContext.getParamterBean().fetchXMLParameterValues().getDivisionHeadRoleID());
			/*if(employee.getRoleId()==DIVISION_HEAD && division!=-1){*/
			if(employee.getRoleAccess().equalsIgnoreCase("Within Division")){
				if(ischart==1){
					for(CollectionData each:collection_data){
						if(each.getLabel().equals(x_axis_field))
							collections=dao.getCollectionsByDivision(division, each);
					}
				}
				else{
					collections = dao.getCollectionsByDivision(division, range);
				}
			}else if(employee.getRoleAccess().equalsIgnoreCase("All")){
				if(ischart==1){
					for(CollectionData each:collection_data){
						if(each.getLabel().equals(x_axis_field))
							collections=dao.getCollectionsByAllDiv(each);
					}
				}
				else{
					collections = dao.getCollectionsByAllDiv(range);
				}
			}
			else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")){
				if(ischart==1){
					for(CollectionData temp:collection_data){
						if(temp.getLabel().equals(x_axis_field))
							collections=dao.getCollectionsByEmpId(employee.getEmployeeId(), temp);
					}
				}
				else{
					collections = dao.getCollectionsByEmpId(employee.getEmployeeId(), range);
				}
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch 
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
