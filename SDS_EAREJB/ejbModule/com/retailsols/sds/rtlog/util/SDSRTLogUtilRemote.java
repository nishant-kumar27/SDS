package com.retailsols.sds.rtlog.util;

import javax.ejb.Remote;

@Remote
public interface SDSRTLogUtilRemote {

	public void cancelInvalidOrderTransaction();
	
}
