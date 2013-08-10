package cn.org.rapid_framework.generator.provider.db.table.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PopupOption {
	private String fieldId;
	private String url;
	private String textColumn;
	private String valueCoumn;
	private String title;

	private final Map<String, String> copiedFields = new LinkedHashMap<String, String>(3);

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTextColumn() {
		return textColumn;
	}

	public void setTextColumn(String textColumn) {
		this.textColumn = textColumn;
	}

	public String getValueCoumn() {
		return valueCoumn;
	}

	public void setValueCoumn(String valueCoumn) {
		this.valueCoumn = valueCoumn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void putCopiedField(String src, String target) {
		copiedFields.put(src, target);
	}

	public String getCopyColumnAsString() {
		StringBuilder builder = new StringBuilder(256);
		Iterator<Entry<String, String>> it = copiedFields.entrySet().iterator();
		boolean nonFirst = false;
		builder.append("{");
		while (it.hasNext()) {
			if (nonFirst) {
				builder.append(",");
			}
			Entry<String, String> entry = it.next();
			builder.append("\"" + entry.getKey() + "\"");
			builder.append(":");
			builder.append("\"" + entry.getValue() + "\"");
			if (!nonFirst) {
				nonFirst = true;
			}

		}
		builder.append("}");
		return builder.toString();

	}

	public boolean isDefinedCopyColumn() {
		return !copiedFields.isEmpty();
	}

}
