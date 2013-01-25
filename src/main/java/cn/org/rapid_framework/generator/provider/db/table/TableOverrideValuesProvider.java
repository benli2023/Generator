package cn.org.rapid_framework.generator.provider.db.table;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import cn.org.rapid_framework.generator.provider.db.table.model.Column;
import cn.org.rapid_framework.generator.provider.db.table.model.ForeignColumn;
import cn.org.rapid_framework.generator.provider.db.table.model.ForeignInfo;
import cn.org.rapid_framework.generator.provider.db.table.model.Table;
import cn.org.rapid_framework.generator.util.FileHelper;
import cn.org.rapid_framework.generator.util.GLogger;

import com.thoughtworks.xstream.XStream;


public  class TableOverrideValuesProvider implements Serializable {
	
	private static final long serialVersionUID = 2334163905052731962L;

	private final Map<String,Table> columnsCacheMap=new HashMap<String,Table>();
	
	private final static TableOverrideValuesProvider instance=new TableOverrideValuesProvider();
	
	
	public static TableOverrideValuesProvider getInstance() {
		return instance;
	}
	
	private static final String XML_FOLDER="generator_config/table/";
	
	public TableOverrideValuesProvider() {
		File rootDir=null;
		try {
			rootDir=FileHelper.getFileByClassLoader(XML_FOLDER);
		} catch (IOException e) {
			//e.printStackTrace();
			
		}
		if(rootDir!=null) {
			File[] files=rootDir.listFiles();
			List<Table> tables=new ArrayList<Table>();
			for (int i = 0; i < files.length; i++) {
				File f=files[i];
				Table t=deSerialization(f);
				tables.add(t);
			}
			/** validate the foreign reference table if exists **/
			for (Iterator<Table> iterator = tables.iterator(); iterator.hasNext();) {
				Table table = (Table) iterator.next();
				LinkedHashSet<Column> columnsSet=table.getColumns();
				for (Iterator<Column> iterator2 = columnsSet.iterator(); iterator2.hasNext();) {
					Column column = (Column) iterator2.next();
					if(column.getForeignInfo()!=null) {
						ForeignInfo foreignInfo=column.getForeignInfo();
						String foreignTable=foreignInfo.getTable();
						Table referTable=columnsCacheMap.get(foreignTable);
						if(referTable==null) {
							String errmsg=" Table '"+table.getSqlName()+"."+column.getSqlName()+"' have reference a foreign table '"+foreignTable+"' which does not exist on '"+rootDir.getAbsolutePath()+"' ,please check the configuration.";
							GLogger.warn(errmsg);
						}else {
							
							/** initial foreignColumns **/
							List<ForeignColumn>  foreignColumns=foreignInfo.getForeignColumns();
							
							int i=0;
							for (Iterator<ForeignColumn> iterator3 = foreignColumns.iterator(); iterator3.hasNext();) {
								ForeignColumn foreignColumn = (ForeignColumn) iterator3.next();
								String referColumn=foreignColumn.getRefer();
								Column originalColumn=referTable.getColumnByName(referColumn);
								i++;
								if(referColumn==null) {
									String errmsg=" Table:"+table.getSqlName()+"."+column.getSqlName()+".ForeignColumns["+i+"] don't have any 'refer' attribute which reference to  a column of foreign table '"+foreignTable+"' ,please add 'refer' attibute .  ";
									GLogger.warn(errmsg);
									continue;
								}
								if(originalColumn==null) {
									String errmsg=" Column '"+table.getSqlName()+"."+column.getSqlName()+"' have reference a foreign column :'"+foreignTable+"."+referColumn+"' which does not exist on '"+rootDir.getAbsolutePath()+"\\"+foreignTable+".xml' "+" ,please check the configuration.";
									GLogger.warn(errmsg);
								}else {
									foreignColumn.setReferColumn(originalColumn);
									originalColumn.setReferredColumn(foreignColumn);
								}
							}
							foreignInfo.setReferTable(referTable);
							referTable.setReferredColums(Collections.unmodifiableList(foreignInfo.getForeignColumns()));
						}
					}
				}
			}
		}
		
		
	}
	
	public Table getTable(String tableName) {
		return deSerialization(tableName);
	}
	
	
	private Table deSerialization(File f) {
		String tableName=f.getName().substring(0, f.getName().length()-4);
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(Table.class);
		Table table = (Table) xstream.fromXML(f);
		columnsCacheMap.put(tableName, table);
		return table;
	}
	
	
	private Table deSerialization(String tableName) {
		Table tableCache = columnsCacheMap.get(tableName);
		if (tableCache == null) {
			XStream xstream = new XStream();
			xstream.autodetectAnnotations(true);
			xstream.processAnnotations(Table.class);
			File file = null;
			try {
				file = FileHelper.getFileByClassLoader(XML_FOLDER+ tableName + ".xml");
			} catch (IOException e) {
				GLogger.warn("file don't exists", e);
			}
			if (file != null) {
				Table table = (Table) xstream.fromXML(file);
				columnsCacheMap.put(tableName, table);
				return table;
			}
		}
		return tableCache;
	}
	
	
	public Column getColumn(String tableName,String columnName)  {
		Table tableCache = deSerialization(tableName);
		if (tableCache != null)
			return tableCache.getColumnByName(columnName);
		else
			return null;
	}
}