package com.git.gdsbuilder.type.validatorOption;

import org.json.simple.JSONArray;

public class Z_ValueAmbiguous extends ValidatorOption {

	JSONArray attributeKey;

	public enum Type {

		Z_VALUEAMBIGUOUS("zValueAmbiguous", "AttributeError");
		String errName;
		String errType;

		Type(String errName, String errType) {
			this.errName = errName;
			this.errType = errType;
		}

		public String errName() {
			return errName;
		}

		public String errType() {
			return errType;
		}
	};

	public Z_ValueAmbiguous(JSONArray attributeKey) {
		super();
		this.attributeKey = attributeKey;
	}

	public JSONArray getAttributeKey() {
		return attributeKey;
	}

	public void setAttributeKey(JSONArray attributeKey) {
		this.attributeKey = attributeKey;
	}

}
