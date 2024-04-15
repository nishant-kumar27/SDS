package util.security;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class SdsRealm extends JdbcRealm {
	private String saltStyleString;

	public String getSaltStyleString() {
		return saltStyleString;
	}

	public void setSaltStyleString(String saltStyleString) {
		if (saltStyleString != null && !saltStyleString.isEmpty()) {
			this.saltStyleString = saltStyleString;
			updateSaltStyle(saltStyleString);
		}
	}

	protected void updateSaltStyle(String saltStyleString) {
		if (SaltStyle.NO_SALT.name().equalsIgnoreCase(saltStyleString))
			saltStyle = SaltStyle.NO_SALT;
		else if (SaltStyle.EXTERNAL.name().equalsIgnoreCase(saltStyleString))
			saltStyle = SaltStyle.EXTERNAL;
		else if (SaltStyle.CRYPT.name().equalsIgnoreCase(saltStyleString))
			saltStyle = SaltStyle.CRYPT;
		else if (SaltStyle.COLUMN.name().equalsIgnoreCase(saltStyleString))
			saltStyle = SaltStyle.COLUMN;

	}
	
	public void clearCache(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
}
