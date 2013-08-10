package cn.org.rapid_framework.generator.provider.db.table.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("foreign-info")
public class ForeignInfo {

	@XStreamImplicit(itemFieldName = "column")
	private List<ForeignColumn> foreignColumns = new LinkedList<ForeignColumn>();

	@XStreamImplicit(itemFieldName = "copy-column")
	private List<CopyColumn> copyColumns = new LinkedList<CopyColumn>();

	@XStreamAlias("refer")
	@XStreamAsAttribute
	private String refer = null;

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id = null;

	@XStreamAlias("default")
	@XStreamAsAttribute
	private boolean _default = false;

	@XStreamAlias("title")
	@XStreamAsAttribute
	private String title = null;

	private Column parentColumn = null;

	private ForeignInfo referForeignInfo = null;

	private Table referTable = null;

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public Iterator<ForeignColumn> iterator() {
		if (referForeignInfo != null) {
			referForeignInfo.iterator();
		} else {
			return foreignColumns.iterator();
		}
		return null;
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

	public ForeignInfo getReferForeignInfo() {
		return referForeignInfo;
	}

	public void setReferForeignInfo(ForeignInfo referForeignInfo) {
		this.referForeignInfo = referForeignInfo;
	}

	public void setCopyColumns(List<CopyColumn> copyColumns) {
		this.copyColumns = copyColumns;
	}

	public List<CopyColumn> getCopyColumns() {
		return copyColumns;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDefault() {
		return _default;
	}

	public void setDefault(boolean _default) {
		this._default = _default;
	}

	public Table getReferTable() {
		return referTable;
	}

	public void setReferTable(Table referTable) {
		this.referTable = referTable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Column getParentColumn() {
		return parentColumn;
	}

	public void setParentColumn(Column parentColumn) {
		this.parentColumn = parentColumn;
	}

	public ForeignColumn[] getValueTextColumns() {

		ForeignColumn[] result = new ForeignColumn[2];

		ForeignColumn textColumn = null, valueColumn = null;
		List<ForeignColumn> foreignColumns = getForeignColumns();
		for (Iterator<ForeignColumn> it2 = foreignColumns.iterator(); it2.hasNext();) {
			ForeignColumn foreignColumn = it2.next();
			if (foreignColumn.getFtype() != null) {
				if (foreignColumn.getFtype().equals("value")) {
					// if(foreignColumn.isSearchable()) {
					// valueCoumn=foreignColumn.getSqlName();
					// }else {
					// valueCoumn=foreignColumn.getColumnNameLower();
					// }
					valueColumn = foreignColumn;

				} else if (foreignColumn.getFtype().equals("text")) {
					// if(foreignColumn.isSearchable()) {
					// textColumn=foreignColumn.getSqlName();
					// }else {
					// textColumn=foreignColumn.getColumnNameLower();
					// }
					textColumn = foreignColumn;
				}
			}
		}

		if (textColumn == null || valueColumn == null) {
			if (valueColumn == null) {
				ForeignColumn foreignColumn = foreignColumns.get(0);
				// if(foreignColumn.isSearchable()) {
				// valueCoumn=foreignColumn.getSqlName();
				// }else {
				// valueCoumn=foreignColumn.getColumnNameLower();
				// }
				valueColumn = foreignColumn;
			}
			if (textColumn == null) {
				ForeignColumn foreignColumn = foreignColumns.get(1);
				// if(foreignColumn.isSearchable()) {
				// textColumn=foreignColumn.getSqlName();
				// }else {
				// textColumn=foreignColumn.getColumnNameLower();
				// }
				textColumn = foreignColumn;
			}
		}

		result[0] = valueColumn;
		result[1] = textColumn;

		return result;
	}

}
