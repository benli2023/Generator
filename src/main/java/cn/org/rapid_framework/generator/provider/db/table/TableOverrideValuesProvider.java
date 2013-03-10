package cn.org.rapid_framework.generator.provider.db.table;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.org.rapid_framework.generator.provider.db.table.model.Column;
import cn.org.rapid_framework.generator.provider.db.table.model.ForeignColumn;
import cn.org.rapid_framework.generator.provider.db.table.model.ForeignInfo;
import cn.org.rapid_framework.generator.provider.db.table.model.Table;
import cn.org.rapid_framework.generator.util.FileHelper;
import cn.org.rapid_framework.generator.util.GLogger;

import com.thoughtworks.xstream.XStream;


public  class TableOverrideValuesProvider implements Serializable {
	
	private static final long serialVersionUID = 2334163905052731962L;

	private final Map<String,Table> tableNameCacheMap=new HashMap<String,Table>();
	
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
				Set<Column> columnsSet=table.getColumns();
				for (Iterator<Column> iterator2 = columnsSet.iterator(); iterator2.hasNext();) {
					Column column = (Column) iterator2.next();
					
					//set up foreign info reference
					if(column.getForeignInfo()!=null&&column.getForeignInfo().getRefer()!=null&&column.getForeignInfo().getRefer().trim().length()!=0) {
						String refer=column.getForeignInfo().getRefer();
						int index=refer.lastIndexOf(".");
						Table referTable=null;
						String foreignInfoId=null;
						if(index<0) {
							//refer itself
							referTable=table;
							foreignInfoId=refer;
						}else {
							String referStr=refer.substring(0, index);
							foreignInfoId=refer.substring(index+1);
							Table reftable=tableNameCacheMap.get(referStr);
							if(reftable==null) {
								String errmsg=" Table:"+table.getSqlName()+"."+column.getSqlName()+"  'refer' attribute refer table which do not exist! ";
								GLogger.warn(errmsg);
								continue;
								
							}
							referTable=reftable;
						}
						ForeignInfo referredForeignInfo=referTable.getForeignInfoById(foreignInfoId);
						if(referredForeignInfo==null) {
							String errmsg=" Table:"+table.getSqlName()+"."+column.getSqlName()+"  'refer' attribute refer foreignInfo which do not exist! ";
							GLogger.warn(errmsg);
							continue;
						}
						column.getForeignInfo().setReferForeignInfo(referredForeignInfo);
						column.getForeignInfo().setReferTable(referTable);
						referredForeignInfo.setReferTable(referTable);
						column.getForeignInfo().setParentColumn(column);
					}
				}
				//set up foreign info column reference
				if(table.getForeignInfos()!=null&&table.getForeignInfos().size()>0) {
					for(Iterator<ForeignInfo> it=table.getForeignInfos().iterator();it.hasNext();) {
						ForeignInfo foreignInfo=it.next();
						setupReferColumn(foreignInfo,table);
					}
				}
			}
		}
		
		
	}
	
	
	private void setupReferColumn(ForeignInfo referredForeignInfo,Table table) {
		String foreignInfoId=referredForeignInfo.getId();
		if(referredForeignInfo.getForeignColumns()!=null&&referredForeignInfo.getForeignColumns().size()>0) {
			for(Iterator<ForeignColumn> it=referredForeignInfo.getForeignColumns().iterator();it.hasNext();) {
				ForeignColumn  foreignColumn=it.next();
				String colRefer=foreignColumn.getRefer();	
				if(colRefer==null) {
					String errmsg=" Table:"+table.getSqlName()+".foreign-info['"+foreignInfoId+"']."+foreignColumn.getRefer()+" don't have any 'refer' attribute which reference to table '"+table.getSqlName()+"' ,please add 'refer' attibute .  ";
					GLogger.warn(errmsg);
					continue;
				}
				Column originalColumn=table.getColumnByName(colRefer);
				if(originalColumn==null) {
					String errmsg=" Table:"+table.getSqlName()+".foreign-info['"+foreignInfoId+"']."+foreignColumn.getRefer()+" don't have any column name '"+foreignColumn.getRefer()+"' define in table  "+table.getSqlName();
					GLogger.warn(errmsg);
					continue;
					
				}
				foreignColumn.setReferColumn(originalColumn);
				
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
		GLogger.trace("parsing "+f.getAbsolutePath());
		Table table = (Table) xstream.fromXML(f);
		tableNameCacheMap.put(tableName, table);
		return table;
	}
	
	
	private Table deSerialization(String tableName) {
		Table tableCache = tableNameCacheMap.get(tableName);
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
				tableNameCacheMap.put(tableName, table);
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