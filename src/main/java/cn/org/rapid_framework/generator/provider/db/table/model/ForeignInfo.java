package cn.org.rapid_framework.generator.provider.db.table.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ForeignInfo {
	
	private List<ForeignColumn> foreignColumns=new LinkedList<ForeignColumn>();
	
	
	public void addForeignColumn(ForeignColumn fColumn) {
		this.foreignColumns.add(fColumn);
	}
	
	
	public Iterator<ForeignColumn>  iterator() {
		return foreignColumns.iterator();
	}


	public List<ForeignColumn> getForeignColumns() {
		return foreignColumns;
	}


	public void setForeignColumns(List<ForeignColumn> foreignColumns) {
		this.foreignColumns = foreignColumns;
	}
	
	
	
	

}
