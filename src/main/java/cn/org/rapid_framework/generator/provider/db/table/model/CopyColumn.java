package cn.org.rapid_framework.generator.provider.db.table.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class CopyColumn {

	@XStreamAlias("srcColumn")
	@XStreamAsAttribute
	private String srcColumn;

	private Column sourceColumn;

	private Column destinationColumn;

	@XStreamAlias("destColumn")
	@XStreamAsAttribute
	private String destColumn;

	public String getSrcColumn() {
		return srcColumn;
	}

	public void setSrcColumn(String srcColumn) {
		this.srcColumn = srcColumn;
	}

	public String getDestColumn() {
		return destColumn;
	}

	public void setDestColumn(String destColumn) {
		this.destColumn = destColumn;
	}

	public Column getSourceColumn() {
		return sourceColumn;
	}

	public void setSourceColumn(Column sourceColumn) {
		this.sourceColumn = sourceColumn;
	}

	public Column getDestinationColumn() {
		return destinationColumn;
	}

	public void setDestinationColumn(Column destinationColumn) {
		this.destinationColumn = destinationColumn;
	}

	@Override
	public String toString() {
		return "CopyColumn [srcColumn=" + srcColumn + ", destColumn=" + destColumn + "]";
	}

}
