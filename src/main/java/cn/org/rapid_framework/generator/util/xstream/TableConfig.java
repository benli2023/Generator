package cn.org.rapid_framework.generator.util.xstream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.org.rapid_framework.generator.provider.db.table.model.Column;
import cn.org.rapid_framework.generator.provider.db.table.model.ForeignColumn;
import cn.org.rapid_framework.generator.provider.db.table.model.ForeignInfo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("table")
public class TableConfig {
	
	@XStreamImplicit(itemFieldName="column")
	private List<Column> columns=null;
	
	@XStreamAlias("sqlName")
	@XStreamAsAttribute
	private String sqlName=null;
	
	@XStreamAlias("className")
	@XStreamAsAttribute
	private String className=null;
	
	
	@XStreamAlias("tableAlias")
	@XStreamAsAttribute
	private String tableAlias=null;
	
	
	

	public List<Column> getColumns() {
		return columns;
	}






	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}






	public String getSqlName() {
		return sqlName;
	}






	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}






	public String getClassName() {
		return className;
	}






	public void setClassName(String className) {
		this.className = className;
	}






	public String getTableAlias() {
		return tableAlias;
	}






	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}






	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TableConfig config = new TableConfig();
		config.setClassName("dd");
		config.setSqlName("11");
		config.setTableAlias("11");
		List<Column> columns = new ArrayList<Column>();
		Column column = new Column();
		column.setAsType("ddd");
		column.setJavaType("ddd");
		columns.add(column);

		Column column1 = new Column();
		column1.setAsType("ddd");
		column1.setJavaType("ddd");
		columns.add(column1);

		ForeignInfo foreignInfo = new ForeignInfo();

		foreignInfo.setTable("staff");

		ForeignColumn foreignColumn = new ForeignColumn();

		//foreignColumn.setColumnName("staff_id");
		
		//foreignColumn.setPk(true);

		ForeignColumn foreignColumn2 = new ForeignColumn();

		//foreignColumn2.setColumnName("staff_name");

		foreignInfo.addForeignColumn(foreignColumn);

		foreignInfo.addForeignColumn(foreignColumn2);

		column1.setForeignInfo(foreignInfo);

		Column column2 = new Column();
		column2.setAsType("ddd");
		column2.setJavaType("ddd");
		columns.add(column2);

		Column column3 = new Column();
		column3.setAsType("ddd");
		column3.setJavaType("ddd");
		columns.add(column3);

		config.setColumns(columns);
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		// xStream.processAnnotations(TableConfig.class);
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream("D:/test.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		xstream.toXML(config, fs);

	}

}
