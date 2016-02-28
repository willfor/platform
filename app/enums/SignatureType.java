package enums;

public enum SignatureType {
	FORMAL_SIGNATURE("formal", "正式签名", 1), 
	DEFAULT_SIGNATURE("default", "默认签名",0);

	private String signatureTypeName;
	private String description;
	private int value;

	private SignatureType(String signatureTypeName, String description, int value) {
		this.signatureTypeName = signatureTypeName;
		this.description = description;
		this.value = value;
	}

	public static boolean isFormalSignature(String signatureTypeName) {
		SignatureType signatureType = findBySignatureTypeName(signatureTypeName);
		return FORMAL_SIGNATURE == signatureType;
	}

	public static boolean isDefaultSignature(String signatureTypeName) {
		SignatureType signatureType = findBySignatureTypeName(signatureTypeName);
		return DEFAULT_SIGNATURE == signatureType;
	}

	public static SignatureType findBySignatureTypeName(String signatureTypeName) {
		SignatureType expectedSignatureType = null;
		for (SignatureType signatureType : SignatureType.values()) {
			if (signatureType.signatureTypeName.equals(signatureTypeName)) {
				expectedSignatureType = signatureType;
				break;
			}
		}
		if (expectedSignatureType == null) {
			throw new UnsupportedOperationException(
					"signature type not as expected,pls check!");
		}
		return expectedSignatureType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return signatureTypeName;
	}

	public void setName(String name) {
		this.signatureTypeName = name;
	}

}
