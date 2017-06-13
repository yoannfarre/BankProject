package bankproject.enumerations;

public enum TypeOperationEnum {

	CREDIT("Credit"), DEBIT("Debit");

	private String type_name;

	private TypeOperationEnum(String type_name) {
		this.type_name = type_name;
	}

	public String getType_name() {
		return type_name;
	}

	public static TypeOperationEnum getType_operationByString(String type_op) {

		for (TypeOperationEnum toe : TypeOperationEnum.values()) {
			if (toe.getType_name().toLowerCase().equals(type_op.toLowerCase())) {
				return toe;
			}
		}
		return null;
	}

}
