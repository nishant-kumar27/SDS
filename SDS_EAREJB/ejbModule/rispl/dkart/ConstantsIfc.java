package rispl.dkart;

public interface ConstantsIfc {

	public enum EmpSearchCriteriaEnum {

		ALL("All"), LINKED("Linked"), WITHIN_DIVISION("Division");

		String value;

		EmpSearchCriteriaEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public static enum TransTypeEnum {
		SALE, CLAIM_REGISTER, CLAIM_APPROVE, CLAIM_ACCEPT, PAYMENT, INVOICE, TEST, INVENTORY_LOW, EMP_TEMP_PASSWORD, EMP_RESET_PASSWORD, CRMEMO, RETURN;
	}

	public static enum MailRecipientTypeEnum {
		CUSTOMER, SALES_AGENT, DEPARTMENT_HEAD, DATA_ENTRY_OPERATOR, ACCOUNT_MANAGER, WAREHOUSE, CUSTOM, EMPLOYEE;
	}

	public static enum InvoiceStatusEnum {
		ANY, OPEN, CLOSE;
	}

	public static enum InvoiceDateRangeEnum {
		TODAY, WEEK, MONTH, QUARTER, YEAR;
	}
}
