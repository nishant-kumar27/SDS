package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the RISPL_DK_EMP_MSTR database table.
 * 
 */
@Entity
@Table(name = "RISPL_DK_EMP_MSTR")
@NamedQuery(name = "RisplDkEmpMstr.findAll", query = "SELECT r FROM RisplDkEmpMstr r")
public class RisplDkEmpMstr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkEmpMstrPK id;

	@Column(name = "ALT_ID")
	private String altId;

	@Temporal(TemporalType.DATE)
	@Column(name = "DC_EXP_TMP")
	private Date dcExpTmp;

	@Column(name = "EMP_ACS_PWD")
	private String empAcsPwd;

	@Column(name = "EMP_FST_NME")
	private String empFstNme;

	@Column(name = "EMP_LCL")
	private String empLcl;

	@Column(name = "EMP_LST_NME")
	private String empLstNme;

	@Column(name = "EMP_MDL_NME")
	private String empMdlNme;

	@Column(name = "EMP_NME")
	private String empNme;

	@Column(name = "EMP_PWD_SLT")
	private String empPwdSlt;

	@Column(name = "EMP_STS_CDE")
	private String empStsCde;

	@Column(name = "EMP_TYPE")
	private BigDecimal empType;

	@Column(name = "FL_PW_NW_REQ")
	private String flPwNwReq;

	@Column(name = "GP_ID")
	private String gpId;

	@Column(name = "GP_TYPE")
	private String gpType;

	@Column(name = "ID_STRGP_FNC")
	private BigDecimal idStrgpFnc;

	@Column(name = "LOGIN_ID")
	private String loginId;

	@Column(name = "NUMB_DYS_VLD")
	private BigDecimal numbDysVld;

	@Column(name = "NUMB_FLD_PW")
	private BigDecimal numbFldPw;

	@Column(name = "PRTY_ID")
	private BigDecimal prtyId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_CRT_PW")
	private Date tsCrtPw;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_LOGIN_LST")
	private Date tsLoginLst;
	
	@Column(name = "EMAIL")
	private String email;

	//bi-directional many-to-one association to RisplDkEmpRole
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "WRK_GP_ID")
	private RisplDkEmpRole risplDkEmpRole;

	//bi-directional one-to-one association to RisplDkEmpPwdHist
	@OneToOne(mappedBy = "risplDkEmpMstr", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private RisplDkEmpPwdHist risplDkEmpPwdHist;

	//bi-directional many-to-one association to RisplDkEmpMerchAssoc
	@OneToMany(mappedBy = "risplDkEmpMstr", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("id.merchId ASC")
	private List<RisplDkEmpMerchAssoc> risplDkEmpMerchAssocs;

	public RisplDkEmpMstr() {
	}

	public RisplDkEmpMstrPK getId() {
		return this.id;
	}

	public void setId(RisplDkEmpMstrPK id) {
		this.id = id;
	}

	public String getAltId() {
		return this.altId;
	}

	public void setAltId(String altId) {
		this.altId = altId;
	}

	public Date getDcExpTmp() {
		return this.dcExpTmp;
	}

	public void setDcExpTmp(Date dcExpTmp) {
		this.dcExpTmp = dcExpTmp;
	}

	public String getEmpAcsPwd() {
		return this.empAcsPwd;
	}

	public void setEmpAcsPwd(String empAcsPwd) {
		this.empAcsPwd = empAcsPwd;
	}

	public String getEmpFstNme() {
		return this.empFstNme;
	}

	public void setEmpFstNme(String empFstNme) {
		this.empFstNme = empFstNme;
	}

	public String getEmpLcl() {
		return this.empLcl;
	}

	public void setEmpLcl(String empLcl) {
		this.empLcl = empLcl;
	}

	public String getEmpLstNme() {
		return this.empLstNme;
	}

	public void setEmpLstNme(String empLstNme) {
		this.empLstNme = empLstNme;
	}

	public String getEmpMdlNme() {
		return this.empMdlNme;
	}

	public void setEmpMdlNme(String empMdlNme) {
		this.empMdlNme = empMdlNme;
	}

	public String getEmpNme() {
		return this.empNme;
	}

	public void setEmpNme(String empNme) {
		this.empNme = empNme;
	}

	public String getEmpPwdSlt() {
		return this.empPwdSlt;
	}

	public void setEmpPwdSlt(String empPwdSlt) {
		this.empPwdSlt = empPwdSlt;
	}

	public String getEmpStsCde() {
		return this.empStsCde;
	}

	public void setEmpStsCde(String empStsCde) {
		this.empStsCde = empStsCde;
	}

	public BigDecimal getEmpType() {
		return this.empType;
	}

	public void setEmpType(BigDecimal empType) {
		this.empType = empType;
	}

	public String getFlPwNwReq() {
		return this.flPwNwReq;
	}

	public void setFlPwNwReq(String flPwNwReq) {
		this.flPwNwReq = flPwNwReq;
	}

	public String getGpId() {
		return this.gpId;
	}

	public void setGpId(String gpId) {
		this.gpId = gpId;
	}

	public String getGpType() {
		return this.gpType;
	}

	public void setGpType(String gpType) {
		this.gpType = gpType;
	}

	public BigDecimal getIdStrgpFnc() {
		return this.idStrgpFnc;
	}

	public void setIdStrgpFnc(BigDecimal idStrgpFnc) {
		this.idStrgpFnc = idStrgpFnc;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public BigDecimal getNumbDysVld() {
		return this.numbDysVld;
	}

	public void setNumbDysVld(BigDecimal numbDysVld) {
		this.numbDysVld = numbDysVld;
	}

	public BigDecimal getNumbFldPw() {
		return this.numbFldPw;
	}

	public void setNumbFldPw(BigDecimal numbFldPw) {
		this.numbFldPw = numbFldPw;
	}

	public BigDecimal getPrtyId() {
		return this.prtyId;
	}

	public void setPrtyId(BigDecimal prtyId) {
		this.prtyId = prtyId;
	}

	public Date getTsCrtPw() {
		return this.tsCrtPw;
	}

	public void setTsCrtPw(Date tsCrtPw) {
		this.tsCrtPw = tsCrtPw;
	}

	public Date getTsLoginLst() {
		return this.tsLoginLst;
	}

	public void setTsLoginLst(Date tsLoginLst) {
		this.tsLoginLst = tsLoginLst;
	}

	public List<RisplDkEmpMerchAssoc> getRisplDkEmpMerchAssocs() {
		return this.risplDkEmpMerchAssocs;
	}

	public void setRisplDkEmpMerchAssocs(List<RisplDkEmpMerchAssoc> risplDkEmpMerchAssocs) {
		this.risplDkEmpMerchAssocs = risplDkEmpMerchAssocs;
	}

	public RisplDkEmpMerchAssoc addRisplDkEmpMerchAssoc(RisplDkEmpMerchAssoc risplDkEmpMerchAssoc) {
		getRisplDkEmpMerchAssocs().add(risplDkEmpMerchAssoc);
		risplDkEmpMerchAssoc.setRisplDkEmpMstr(this);

		return risplDkEmpMerchAssoc;
	}

	public RisplDkEmpMerchAssoc removeRisplDkEmpMerchAssoc(RisplDkEmpMerchAssoc risplDkEmpMerchAssoc) {
		getRisplDkEmpMerchAssocs().remove(risplDkEmpMerchAssoc);
		risplDkEmpMerchAssoc.setRisplDkEmpMstr(null);

		return risplDkEmpMerchAssoc;
	}

	public RisplDkEmpRole getRisplDkEmpRole() {
		return this.risplDkEmpRole;
	}

	public void setRisplDkEmpRole(RisplDkEmpRole risplDkEmpRole) {
		this.risplDkEmpRole = risplDkEmpRole;
	}

	public RisplDkEmpPwdHist getRisplDkEmpPwdHist() {
		return this.risplDkEmpPwdHist;
	}

	public void setRisplDkEmpPwdHist(RisplDkEmpPwdHist risplDkEmpPwdHist) {
		this.risplDkEmpPwdHist = risplDkEmpPwdHist;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RisplDkEmpMstr [id=" + id + ", altId=" + altId + ", dcExpTmp=" + dcExpTmp + ", empAcsPwd=" + empAcsPwd
				+ ", empFstNme=" + empFstNme + ", empLcl=" + empLcl + ", empLstNme=" + empLstNme + ", empMdlNme="
				+ empMdlNme + ", empNme=" + empNme + ", empPwdSlt=" + empPwdSlt + ", empStsCde=" + empStsCde
				+ ", empType=" + empType + ", flPwNwReq=" + flPwNwReq + ", gpId=" + gpId + ", gpType=" + gpType
				+ ", idStrgpFnc=" + idStrgpFnc + ", loginId=" + loginId + ", numbDysVld=" + numbDysVld + ", numbFldPw="
				+ numbFldPw + ", prtyId=" + prtyId + ", tsCrtPw=" + tsCrtPw + ", tsLoginLst=" + tsLoginLst
				+ ", risplDkEmpRole=" + risplDkEmpRole + ", risplDkEmpPwdHist=" + risplDkEmpPwdHist
				+ ", risplDkEmpMerchAssocs=" + risplDkEmpMerchAssocs + ", email=" + email + "]";
	}

}