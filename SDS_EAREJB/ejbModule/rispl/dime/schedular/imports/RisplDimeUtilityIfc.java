package rispl.dime.schedular.imports;

import java.io.Serializable;

import javax.ejb.Remote;
@Remote
public interface RisplDimeUtilityIfc extends Serializable{

	 public void processMOMFiles(StringBuffer stagingLoc,StringBuffer backupLoc);
}
