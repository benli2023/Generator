package cn.org.rapid_framework.generator.provider.db.table.model;

public class LeftJoinSelectColumn {

	private Table joinTable;
	private Column column;

	public Table getJoinTable() {
		return joinTable;
	}

	public void setJoinTable(Table joinTable) {
		this.joinTable = joinTable;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

}
