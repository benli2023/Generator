package cn.org.rapid_framework.generator.provider.db.table.model;


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.provider.db.table.TableFactory;
import cn.org.rapid_framework.generator.util.StringHelper;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
/**
 * 用于生成代码的Table对象.对应数据库的table
 * @author badqiu
 * @email badqiu(a)gmail.com
 */

@XStreamAlias("table")
public class Table implements java.io.Serializable,Cloneable {
	
	private static final long serialVersionUID = -24089896374163470L;


	@XStreamAlias("sqlName")
	@XStreamAsAttribute
	String sqlName;
	
	
	String remarks;
	
	@XStreamAlias("className")
	@XStreamAsAttribute
	String className;
	
	/** the name of the owner of the synonym if this table is a synonym */
	private String ownerSynonymName = null;
	/** real table name for oracle SYNONYM */
	private String tableSynonymName = null; 
	
	@XStreamImplicit(itemFieldName = "column")
	Set<Column> columns = new LinkedHashSet<Column>();
	
	
	@XStreamImplicit(itemFieldName = "foreign-info")
	Set<ForeignInfo> foreignInfos = new LinkedHashSet<ForeignInfo>();
	
	
	/** other table refer these column as foreign columns **/
	//List<ForeignColumn> referredColums=new ArrayList<ForeignColumn>();
	
	List<Column> primaryKeyColumns = new ArrayList<Column>();
	
	
	public Table() {}
	
	public Table(Table t) {
		setSqlName(t.getSqlName());
		this.remarks = t.getRemarks();
		this.className = t.getClassName();
		this.ownerSynonymName = t.getOwnerSynonymName();
		this.columns = t.getColumns();
		this.primaryKeyColumns = t.getPrimaryKeyColumns();
		this.tableAlias = t.getTableAlias();
		this.exportedKeys = t.exportedKeys;
		this.importedKeys = t.importedKeys;
	}
	
	public Set<Column> getColumns() {
		return columns;
	}
	
//	public List<ForeignColumn> getReferredColums() {
//		return referredColums;
//	}
//	
//	public void setReferredColums(List<ForeignColumn> referedColums) {
//		this.referredColums = referedColums;
//	}

	public void setColumns(LinkedHashSet<Column> columns) {
		this.columns = columns;
	}
	public String getOwnerSynonymName() {
		return ownerSynonymName;
	}
	public void setOwnerSynonymName(String ownerSynonymName) {
		this.ownerSynonymName = ownerSynonymName;
	}
	public String getTableSynonymName() {
		return tableSynonymName;
	}
	public void setTableSynonymName(String tableSynonymName) {
		this.tableSynonymName = tableSynonymName;
	}

	/** 使用 getPkColumns() 替换*/
	@Deprecated
	public List<Column> getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}
	/** 使用 setPkColumns() 替换*/
	@Deprecated
	public void setPrimaryKeyColumns(List<Column> primaryKeyColumns) {
		this.primaryKeyColumns = primaryKeyColumns;
	}
	/** 数据库中表的表名称,其它属性很多都是根据此属性派生 */
	public String getSqlName() {
		return sqlName;
	}
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public static String removeTableSqlNamePrefix(String sqlName) {
		String prefixs = GeneratorProperties.getProperty("tableRemovePrefixes", "");
		for(String prefix : prefixs.split(",")) {
			String removedPrefixSqlName = StringHelper.removePrefix(sqlName, prefix,true);
			if(!removedPrefixSqlName.equals(sqlName)) {
				return removedPrefixSqlName;
			}
		}
		return sqlName;
	}
	
	/** 数据库中表的表备注 */
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void addColumn(Column column) {
		columns.add(column);
	}
	
	public void setClassName(String customClassName) {
		this.className = customClassName;
	}
	/**
	 * 根据sqlName得到的类名称，示例值: UserInfo
	 * @return
	 */
	public String getClassName() {
	    if(StringHelper.isBlank(className)) {
	        String removedPrefixSqlName = removeTableSqlNamePrefix(sqlName);
	        return StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(removedPrefixSqlName));
	    }else {
	    	return className;
	    }
	}
	/** 数据库中表的别名，等价于:  getRemarks().isEmpty() ? getClassName() : getRemarks() */
	public String getTableAlias() {
		if(StringHelper.isNotBlank(tableAlias)) return tableAlias;
		return StringHelper.removeCrlf(StringHelper.defaultIfEmpty(getRemarks(), getClassName()));
	}
	public void setTableAlias(String v) {
		this.tableAlias = v;
	}
	
	/**
	 * 等价于getClassName().toLowerCase()
	 * @return
	 */
	public String getClassNameLowerCase() {
		return getClassName().toLowerCase();
	}
	/**
	 * 得到用下划线分隔的类名称，如className=UserInfo,则underscoreName=user_info
	 * @return
	 */
	public String getUnderscoreName() {
		return StringHelper.toUnderscoreName(getClassName()).toLowerCase();
	}
	/**
	 * 返回值为getClassName()的第一个字母小写,如className=UserInfo,则ClassNameFirstLower=userInfo
	 * @return
	 */
	public String getClassNameFirstLower() {
		return StringHelper.uncapitalize(getClassName());
	}
	
	/**
	 * 根据getClassName()计算而来,用于得到常量名,如className=UserInfo,则constantName=USER_INFO
	 * @return
	 */
	public String getConstantName() {
		return StringHelper.toUnderscoreName(getClassName()).toUpperCase();
	}
	
	/** 使用 getPkCount() 替换*/
	@Deprecated
	public boolean isSingleId() {
		return getPkCount() == 1 ? true : false;
	}
	
	/** 使用 getPkCount() 替换*/
	@Deprecated
	public boolean isCompositeId() {
		return getPkCount() > 1 ? true : false;
	}

	/** 使用 getPkCount() 替换*/
	@Deprecated
	public boolean isNotCompositeId() {
		return !isCompositeId();
	}
	
	/**
	 * 得到主键总数
	 * @return
	 */
	public int getPkCount() {
		int pkCount = 0;
		for(Column c : columns){
			if(c.isPk()) {
				pkCount ++;
			}
		}
		return pkCount;
	}
	/**
	 * use getPkColumns()
	 * @deprecated 
	 */
	public List getCompositeIdColumns() {
		return getPkColumns();
	}
	
	/**
	 * 得到是主键的全部column
	 * @return
	 */	
	public List<Column> getPkColumns() {
		List results = new ArrayList();
		for(Column c : getColumns()) {
			if(c.isPk())
				results.add(c);
		}
		return results;
	}
	
	/**
	 * 得到不是主键的全部column
	 * @return
	 */
	public List<Column> getNotPkColumns() {
		List results = new ArrayList();
		for(Column c : getColumns()) {
			if(!c.isPk())
				results.add(c);
		}
		return results;
	}
	/** 得到单主键，等价于getPkColumns().get(0)  */
	public Column getPkColumn() {
		if(getPkColumns().isEmpty()) {
			throw new IllegalStateException("not found primary key on table:"+getSqlName());
		}
		return getPkColumns().get(0);
	}
	
	/**使用 getPkColumn()替换 */
	@Deprecated
	public Column getIdColumn() {
		return getPkColumn();
	}
	
	public List<Column> getEnumColumns() {
        List results = new ArrayList();
        for(Column c : getColumns()) {
            if(!c.isEnumColumn())
                results.add(c);
        }
        return results;	    
	}
	
	public Column getColumnByName(String name) {
	    Column c = getColumnBySqlName(name);
	    if(c == null) {
	    	c = getColumnBySqlName(StringHelper.toUnderscoreName(name));
	    }
	    return c;
	}
	
	public Column getColumnBySqlName(String sqlName) {
	    for(Column c : getColumns()) {
	        if(c.getSqlName().equalsIgnoreCase(sqlName)) {
	            return c;
	        }
	    }
	    return null;
	}
	
   public Column getRequiredColumnBySqlName(String sqlName) {
       if(getColumnBySqlName(sqlName) == null) {
           throw new IllegalArgumentException("not found column with sqlName:"+sqlName+" on table:"+getSqlName());
       }
       return getColumnBySqlName(sqlName);
    }
	
	/**
	 * 忽略过滤掉某些关键字的列,关键字不区分大小写,以逗号分隔
	 * @param ignoreKeywords
	 * @return
	 */
	public List<Column> getIgnoreKeywordsColumns(String ignoreKeywords) {
		List results = new ArrayList();
		for(Column c : getColumns()) {
			String sqlname = c.getSqlName().toLowerCase();
			if(StringHelper.contains(sqlname,ignoreKeywords.split(","))) {
				continue;
			}
			results.add(c);
		}
		return results;
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	public void initImportedKeys(DatabaseMetaData dbmd) throws java.sql.SQLException {
		
			   // get imported keys a
	
			   ResultSet fkeys = dbmd.getImportedKeys(catalog,schema,this.sqlName);

			   while ( fkeys.next()) {
				 String pktable = fkeys.getString(PKTABLE_NAME);
				 String pkcol   = fkeys.getString(PKCOLUMN_NAME);
				 String fktable = fkeys.getString(FKTABLE_NAME);
				 String fkcol   = fkeys.getString(FKCOLUMN_NAME);
				 String seq     = fkeys.getString(KEY_SEQ);
				 Integer iseq   = new Integer(seq);
				 getImportedKeys().addForeignKey(pktable,pkcol,fkcol,iseq);
			   }
			   fkeys.close();
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	public void initExportedKeys(DatabaseMetaData dbmd) throws java.sql.SQLException {
			   // get Exported keys
	
			   ResultSet fkeys = dbmd.getExportedKeys(catalog,schema,this.sqlName);
			  
			   while ( fkeys.next()) {
				 String pktable = fkeys.getString(PKTABLE_NAME);
				 String pkcol   = fkeys.getString(PKCOLUMN_NAME);
				 String fktable = fkeys.getString(FKTABLE_NAME);
				 String fkcol   = fkeys.getString(FKCOLUMN_NAME);
				 String seq     = fkeys.getString(KEY_SEQ);
				 Integer iseq   = new Integer(seq);
				 getExportedKeys().addForeignKey(fktable,fkcol,pkcol,iseq);
			   }
			   fkeys.close();
	}
	
	/**
	 * @return Returns the exportedKeys.
	 */
	public ForeignKeys getExportedKeys() {
		if (exportedKeys == null) {
			exportedKeys = new ForeignKeys(this);
		}
		return exportedKeys;
	}
	/**
	 * @return Returns the importedKeys.
	 */
	public ForeignKeys getImportedKeys() {
		if (importedKeys == null) {
			importedKeys = new ForeignKeys(this);
		}
		return importedKeys;
	}
	
	public String toString() {
		return "Database Table:"+getSqlName()+" to ClassName:"+getClassName();
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			//ignore
			return null;
		}
	}
	
	String catalog = TableFactory.getInstance().getCatalog();
	String schema = TableFactory.getInstance().getSchema();
	
	@XStreamAlias("tableAlias")
	@XStreamAsAttribute
	private String tableAlias;
	private ForeignKeys exportedKeys;
	private ForeignKeys importedKeys;
	
	public    static final String PKTABLE_NAME  = "PKTABLE_NAME";
	public    static final String PKCOLUMN_NAME = "PKCOLUMN_NAME";
	public    static final String FKTABLE_NAME  = "FKTABLE_NAME";
	public    static final String FKCOLUMN_NAME = "FKCOLUMN_NAME";
	public    static final String KEY_SEQ       = "KEY_SEQ";
	
	
//	public boolean isReferredColumnSearchable(String referSqlName) {
//		if(this.referredColums!=null) {
//			for (Iterator<ForeignColumn> iterator = referredColums.iterator(); iterator.hasNext();) {
//				ForeignColumn	foreignColumn = (ForeignColumn) iterator.next();
//				if(foreignColumn.getSqlName().equals(referSqlName)) {
//					return foreignColumn.isSearchable();
//				}
//			}
//		}
//		return false;
//	}
	
	
	
	public Set<ForeignColumn> getSearchableColumnFromForeignInfo() {
		Set<ForeignColumn> result=new HashSet<ForeignColumn>();
		if(foreignInfos!=null&&foreignInfos.size()>0) {
			for(Iterator<ForeignInfo> it=foreignInfos.iterator();it.hasNext();) {
				ForeignInfo foreignInfo=it.next();
				List<ForeignColumn> foreignColumns=foreignInfo.getForeignColumns();
				for(Iterator<ForeignColumn> it2=foreignColumns.iterator();it2.hasNext();) {
					ForeignColumn foreignColumn=it2.next();
					if(foreignColumn.isSearchable()) result.add(foreignColumn);
				}
			}
		}
		return result;
	}
	
	
	public ForeignInfo getForeignInfoById(String id) {
		if(foreignInfos!=null&&foreignInfos.size()>0) {
			for(Iterator<ForeignInfo> it=foreignInfos.iterator();it.hasNext();) {
				ForeignInfo foreignInfo=it.next();
				if(foreignInfo.getId().equals(id)) {
					return foreignInfo;
				}
			}
		}
		return null;
	}
	
	public List<PopupOption> getPopupOptions() {
		 List<PopupOption> result=new ArrayList<PopupOption>();
		 if(!isDefineForeignKey()) return result;
			for(Iterator<Column> it=columns.iterator();it.hasNext();) {
				Column column=it.next();
				if(column.getForeignInfo()!=null&&column.getForeignInfo().getRefer()!=null)  {
					PopupOption popupOption=new PopupOption();
					popupOption.setFieldId(column.getHtmlInputId());
					String clazzNameLower=column.getForeignInfo().getReferForeignInfo().getReferTable().getClassNameLowerCase();
					popupOption.setUrl("${ctx}/"+clazzNameLower+"/query");
					
					String title=null;
					
					if(column.getForeignInfo().getReferForeignInfo().getTitle()!=null) {
						title=column.getForeignInfo().getReferForeignInfo().getTitle();
					}else {
						title="选择"+column.getForeignInfo().getReferForeignInfo().getReferTable().getTableAlias();
					}
					popupOption.setTitle(title);
					String textColumn=null,valueCoumn=null;
					List<ForeignColumn> foreignColumns=column.getForeignInfo().getReferForeignInfo().getForeignColumns();
					for(Iterator<ForeignColumn> it2=foreignColumns.iterator();it2.hasNext();) {
						ForeignColumn foreignColumn=it2.next();
						if(foreignColumn.getFtype()!=null) {
							if(foreignColumn.getFtype().equals("value")) {
								if(foreignColumn.isSearchable()) {
									valueCoumn=foreignColumn.getSqlName();
								}else {
									valueCoumn=foreignColumn.getColumnNameLower();
								}
								
							}else if(foreignColumn.getFtype().equals("text")){
								if(foreignColumn.isSearchable()) {
									textColumn=foreignColumn.getSqlName();
								}else {
									textColumn=foreignColumn.getColumnNameLower();
								}
							}
						}
					}
					
					if(textColumn==null||valueCoumn==null) {
						if(valueCoumn==null) {
							ForeignColumn foreignColumn=foreignColumns.get(0);
							if(foreignColumn.isSearchable()) {
								valueCoumn=foreignColumn.getSqlName();
							}else {
								valueCoumn=foreignColumn.getColumnNameLower();
							}
						}
						if(textColumn==null) {
							ForeignColumn foreignColumn=foreignColumns.get(1);
							if(foreignColumn.isSearchable()) {
								textColumn=foreignColumn.getSqlName();
							}else {
								textColumn=foreignColumn.getColumnNameLower();
							}
						}
					}
					popupOption.setTextColumn(textColumn);
					popupOption.setValueCoumn(valueCoumn);
					result.add(popupOption);
				}
			}
		   return result;
	}
	
	public boolean isDefineForeignKey() {
		for(Iterator<Column> it=columns.iterator();it.hasNext();) {
			Column column=it.next();
			if(column.getForeignInfo()!=null) return true;
		}
		return false;
	}
	
	public boolean isDefineForeignInfo() {
		return (foreignInfos!=null&&foreignInfos.size()>0);
	}
	
	public Set<ForeignInfo> getForeignInfos() {
		return foreignInfos;
	}
	

	public void setForeignInfos(LinkedHashSet<ForeignInfo> foreignInfos) {
		this.foreignInfos = foreignInfos;
	}

	public void override(Table table) {
		this.sqlName=table.getSqlName();
		this.className=table.getClassName();
		this.tableAlias=table.getTableAlias();
//		if(table.getReferredColums()!=null) {
//			this.setReferredColums(table.getReferredColums());
//		}
		if(table.getForeignInfos()!=null) {
			this.foreignInfos=table.getForeignInfos();
		}
		
	}
	
}
