package rispl.dkart.services.entities.tenders;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DK_TRAN_CHK_TND database table.
 * 
 */
@Entity
@Table(name="DK_TRAN_CHK_TND")
@NamedQuery(name="TranCheckTender.findAll", query="SELECT t FROM TranCheckTender t")
public class TranCheckTender implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TranCheckTenderPK id;

	@Column(name="ACNT_MSK_CHK")
	private String acntMskChk;

	@Column(name="ACNT_NCRPT_CHK")
	private String acntNcrptChk;

	@Column(name="CD_AZN_APVL")
	private String cdAznApvl;

	@Column(name="CD_CNV_AZN_CHK")
	private String cdCnvAznChk;

	@Column(name="CD_CO_MICR")
	private BigDecimal cdCoMicr;

	@Column(name="CD_ST")
	private String cdSt;

	@Column(name="DK_AI_CHK")
	private String dkAiChk;

	@Column(name="DK_BIRTH_DATE")
	private String dkBirthDate;

	@Column(name="DK_BNK_ID")
	private String dkBnkId;

	@Column(name="DK_TA_CT")
	private String dkTaCt;

	@Column(name="DK_TL_CT")
	private String dkTlCt;

	@Column(name="FG_CNV")
	private String fgCnv;

	@Column(name="ID_AJD_CHK")
	private String idAjdChk;

	@Column(name="ID_MSK_PRSL_AZN")
	private String idMskPrslAzn;

	@Column(name="ID_NCRPT_PRSL_AZN")
	private String idNcrptPrslAzn;

	@Column(name="LU_CHK_SCN_KY")
	private String luChkScnKy;

	@Column(name="LU_ID_PRSL_ISSR")
	private String luIdPrslIssr;

	@Column(name="LU_MTH_AZN")
	private String luMthAzn;

	@Column(name="NR_MSK_MICR")
	private String nrMskMicr;

	@Column(name="NR_NCRPT_MICR")
	private String nrNcrptMicr;

	@Column(name="SWP_ID")
	private String swpId;

	@Column(name="TRK1_ID")
	private String trk1Id;

	@Column(name="TRK2_ID")
	private String trk2Id;

	@Column(name="TY_ID_PRSL_RQ")
	private String tyIdPrslRq;
	
	@Column(name="FILE_TYPE")
	private String fileType;
	
	@Column(name="IMG_CHK_SLIP")
	private byte[] checkSlipImage;

	//bi-directional many-to-one association to TranLineItemTender
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="DC_DY_ORD", referencedColumnName="DC_DY_ORD"),
		@JoinColumn(name="ORD_WS", referencedColumnName="ORD_WS"),
		@JoinColumn(name="RT_STR_ID", referencedColumnName="RT_STR_ID"),
		@JoinColumn(name="TRN_LN_ITM_SEQ", referencedColumnName="TRN_LN_ITM_SEQ"),
		@JoinColumn(name="TRN_SEQ", referencedColumnName="TRN_SEQ")
		})
	private TranLineItemTender dkTranLtmTnd;

	public TranCheckTender() {
	}

	public TranCheckTenderPK getId() {
		return this.id;
	}

	public void setId(TranCheckTenderPK id) {
		this.id = id;
	}

	public String getAcntMskChk() {
		return this.acntMskChk;
	}

	public void setAcntMskChk(String acntMskChk) {
		this.acntMskChk = acntMskChk;
	}

	public String getAcntNcrptChk() {
		return this.acntNcrptChk;
	}

	public void setAcntNcrptChk(String acntNcrptChk) {
		this.acntNcrptChk = acntNcrptChk;
	}

	public String getCdAznApvl() {
		return this.cdAznApvl;
	}

	public void setCdAznApvl(String cdAznApvl) {
		this.cdAznApvl = cdAznApvl;
	}

	public String getCdCnvAznChk() {
		return this.cdCnvAznChk;
	}

	public void setCdCnvAznChk(String cdCnvAznChk) {
		this.cdCnvAznChk = cdCnvAznChk;
	}

	public BigDecimal getCdCoMicr() {
		return this.cdCoMicr;
	}

	public void setCdCoMicr(BigDecimal cdCoMicr) {
		this.cdCoMicr = cdCoMicr;
	}

	public String getCdSt() {
		return this.cdSt;
	}

	public void setCdSt(String cdSt) {
		this.cdSt = cdSt;
	}

	public String getDkAiChk() {
		return this.dkAiChk;
	}

	public void setDkAiChk(String dkAiChk) {
		this.dkAiChk = dkAiChk;
	}

	public String getDkBirthDate() {
		return this.dkBirthDate;
	}

	public void setDkBirthDate(String dkBirthDate) {
		this.dkBirthDate = dkBirthDate;
	}

	public String getDkBnkId() {
		return this.dkBnkId;
	}

	public void setDkBnkId(String dkBnkId) {
		this.dkBnkId = dkBnkId;
	}

	public String getDkTaCt() {
		return this.dkTaCt;
	}

	public void setDkTaCt(String dkTaCt) {
		this.dkTaCt = dkTaCt;
	}

	public String getDkTlCt() {
		return this.dkTlCt;
	}

	public void setDkTlCt(String dkTlCt) {
		this.dkTlCt = dkTlCt;
	}

	public String getFgCnv() {
		return this.fgCnv;
	}

	public void setFgCnv(String fgCnv) {
		this.fgCnv = fgCnv;
	}

	public String getIdAjdChk() {
		return this.idAjdChk;
	}

	public void setIdAjdChk(String idAjdChk) {
		this.idAjdChk = idAjdChk;
	}

	public String getIdMskPrslAzn() {
		return this.idMskPrslAzn;
	}

	public void setIdMskPrslAzn(String idMskPrslAzn) {
		this.idMskPrslAzn = idMskPrslAzn;
	}

	public String getIdNcrptPrslAzn() {
		return this.idNcrptPrslAzn;
	}

	public void setIdNcrptPrslAzn(String idNcrptPrslAzn) {
		this.idNcrptPrslAzn = idNcrptPrslAzn;
	}

	public String getLuChkScnKy() {
		return this.luChkScnKy;
	}

	public void setLuChkScnKy(String luChkScnKy) {
		this.luChkScnKy = luChkScnKy;
	}

	public String getLuIdPrslIssr() {
		return this.luIdPrslIssr;
	}

	public void setLuIdPrslIssr(String luIdPrslIssr) {
		this.luIdPrslIssr = luIdPrslIssr;
	}

	public String getLuMthAzn() {
		return this.luMthAzn;
	}

	public void setLuMthAzn(String luMthAzn) {
		this.luMthAzn = luMthAzn;
	}

	public String getNrMskMicr() {
		return this.nrMskMicr;
	}

	public void setNrMskMicr(String nrMskMicr) {
		this.nrMskMicr = nrMskMicr;
	}

	public String getNrNcrptMicr() {
		return this.nrNcrptMicr;
	}

	public void setNrNcrptMicr(String nrNcrptMicr) {
		this.nrNcrptMicr = nrNcrptMicr;
	}

	public String getSwpId() {
		return this.swpId;
	}

	public void setSwpId(String swpId) {
		this.swpId = swpId;
	}

	public String getTrk1Id() {
		return this.trk1Id;
	}

	public void setTrk1Id(String trk1Id) {
		this.trk1Id = trk1Id;
	}

	public String getTrk2Id() {
		return this.trk2Id;
	}

	public void setTrk2Id(String trk2Id) {
		this.trk2Id = trk2Id;
	}

	public String getTyIdPrslRq() {
		return this.tyIdPrslRq;
	}

	public void setTyIdPrslRq(String tyIdPrslRq) {
		this.tyIdPrslRq = tyIdPrslRq;
	}

	public TranLineItemTender getDkTranLtmTnd() {
		return this.dkTranLtmTnd;
	}

	public void setDkTranLtmTnd(TranLineItemTender dkTranLtmTnd) {
		this.dkTranLtmTnd = dkTranLtmTnd;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getCheckSlipImage() {
		return checkSlipImage;
	}

	public void setCheckSlipImage(byte[] checkSlipImage) {
		this.checkSlipImage = checkSlipImage;
	}
	

}