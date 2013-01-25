package cn.org.rapid_framework.generator.provider.db.table.model;

import cn.org.rapid_framework.generator.util.StringHelper;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class ForeignColumn {

	private static final long serialVersionUID = 1568565694691647451L;

	@XStreamAlias("searchable")
	@XStreamAsAttribute
	private boolean searchable = false;
	
	@XStreamAlias("refer")
	@XStreamAsAttribute
	private String refer = null;

	private Column referColumn = null;
	
	private String columnName=null;

	public ForeignColumn() {
		super();
	}

	public ForeignColumn(boolean searchable, String sqlName,
			String columnAlias, String javaType) {
		super();
		this.searchable = searchable;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public boolean isPk() {
		if (referColumn != null) {
			return referColumn.isPk();
		}
		return false;
	}

	public String getSqlName() {
		if (referColumn != null) {
			return referColumn.getSqlName();
		}
		return "";
	}

	public String getColumnAlias() {
		if (referColumn != null) {
			return referColumn.getColumnAlias();
		}
		return "";

	}

	public String getJavaType() {
		if (referColumn != null) {
			return referColumn.getJavaType();
		}
		return "";
	}
	
	public String getColumnNameLower() {
		if(columnName==null) {
			columnName = StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(getSqlName()));
		}
		return StringHelper.uncapitalize(columnName);
	}

	@Override
	public String toString() {
		if (referColumn != null) {
			return (referColumn.getSqlName() + "("
					+ referColumn.getColumnAlias() + ")");
		}
		return this.refer;
	}

	

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public Column getReferColumn() {
		return referColumn;
	}

	public void setReferColumn(Column referColumn) {
		this.referColumn = referColumn;
	}

}
