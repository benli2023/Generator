package cn.org.rapid_framework.generator.provider.db.table.model;

import cn.org.rapid_framework.generator.provider.db.table.TableFactory;

public class ForeignColumn extends Column {
	
	private static final long serialVersionUID = 1568565694691647451L;

	private Table parentTable;

	private boolean searchable = false;

	public ForeignColumn() {
		super();
	}

	public ForeignColumn(Column c, String parentTable, boolean searchable) {
		super(c);
		this.parentTable = TableFactory.getInstance().getTable(parentTable);
		this.searchable = searchable;
	}

	public Table getParentTable() {
		return parentTable;
	}

	public void setParentTable(Table parentTable) {
		this.parentTable = parentTable;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

}
