package cn.org.rapid_framework.generator.enums;

public enum ReservePropertyName {

	createId, createdDate, updateId, updateDate;
	
	public static boolean isReserveKeyword(String keyword) {
		for (ReservePropertyName v : ReservePropertyName.values()) {
			if (v.toString().equals(keyword)) {
				return true;
			}
		}
		return false;
		
	}
}
