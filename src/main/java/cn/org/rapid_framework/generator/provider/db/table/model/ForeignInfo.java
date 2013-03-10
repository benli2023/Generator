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
	private String title=null;

	private ForeignInfo referForeignInfo = null;
	
	private Table referTable=null;

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
	
}
