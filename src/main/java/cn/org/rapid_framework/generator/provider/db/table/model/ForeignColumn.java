package cn.org.rapid_framework.generator.provider.db.table.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;



public class ForeignColumn  {
	
	private static final long serialVersionUID = 1568565694691647451L;

	
	@XStreamAlias("searchable")
   	@XStreamAsAttribute
	private boolean searchable = false;
	
	@XStreamAlias("pk")
   	@XStreamAsAttribute
	private boolean pk=false;
	
	@XStreamAlias("columnName")
   	@XStreamAsAttribute
	private String columnName=null;
	
	@XStreamAlias("columnAlias")
   	@XStreamAsAttribute
	private String columnAlias=null;
	
	
	
	public ForeignColumn() {
		super();
	}
	
	
	public ForeignColumn(boolean searchable, boolean pk, String columnName,
			String columnAlias) {
		super();
		this.searchable = searchable;
		this.pk = pk;
		this.columnName = columnName;
		this.columnAlias = columnAlias;
	}


	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public String getColumnAlias() {
		return columnAlias;
	}


	public void setColumnAlias(String columnAlias) {
		this.columnAlias = columnAlias;
	}
	

}
