package cn.org.rapid_framework.generator.provider.db.table.model;

public class LeftJoin {

	private Table table;
	private Column leftColumn;
	private Column rightColumn;

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Column getLeftColumn() {
		return leftColumn;
	}

	public void setLeftColumn(Column leftColumn) {
		this.leftColumn = leftColumn;
	}

	public Column getRightColumn() {
		return rightColumn;
	}

	public void setRightColumn(Column rightColumn) {
		this.rightColumn = rightColumn;
	}

	
}
