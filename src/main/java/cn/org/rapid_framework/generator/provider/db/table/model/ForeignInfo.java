package cn.org.rapid_framework.generator.provider.db.table.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ForeignInfo {
	
	@XStreamImplicit(itemFieldName="column")
	private List<ForeignColumn> foreignColumns=new LinkedList<ForeignColumn>();
	
	@XStreamAlias("table")
   	@XStreamAsAttribute
	private String table=null;
	
	
	public String getTable() {
		return table;
	}


	public void setTable(String table) {
		this.table = table;
	}


	
	
	public Iterator<ForeignColumn>  iterator() {
		return foreignColumns.iterator();
	}

	
	public void addForeignColumn(ForeignColumn foreignColumn) {
		foreignColumns.add(foreignColumn);
	}

	public List<ForeignColumn> getForeignColumns() {
		return foreignColumns;
	}


	public void setForeignColumns(List<ForeignColumn> foreignColumns) {
		this.foreignColumns = foreignColumns;
	}
	
	
	
	

}
