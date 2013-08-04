package cn.org.rapid_framework.generator.provider.db.table.model.util;

import org.springframework.util.StringUtils;

import cn.org.rapid_framework.generator.provider.db.table.model.Column;
import cn.org.rapid_framework.generator.util.typemapping.DatabaseDataTypesUtils;

public class ColumnHelper {

	public static String[] removeHibernateValidatorSpecialTags(String str) {
		if (str == null || str.trim().length() == 0)
			return new String[] {};
		return str.trim().replaceAll("@", "").replaceAll("\\(.*?\\)", "").trim().split("\\s+");
	}

	/** 得到JSR303 bean validation的验证表达式 */
	public static String getHibernateValidatorExpression(Column c) {
		StringBuilder builder = new StringBuilder(128);
		if (!c.isPk() && !c.isNullable()) {
			if (DatabaseDataTypesUtils.isString(c.getJavaType())) {
				builder.append("@NotBlank ");

			} else {
				builder.append("@NotNull ");
			}
		}
		String foreignSearchable = getForeignSearchable(c);
		if (StringUtils.hasLength(foreignSearchable)) {
			builder.append(getForeignSearchable(c));
		}
		String notRequiredExpression = getNotRequiredHibernateValidatorExpression(c);
		if (StringUtils.hasLength(notRequiredExpression)) {
			builder.append(notRequiredExpression);
		}
		builder.append(" ");
		if (c.getIsDateTimeColumn()) {
			builder.append("@JsonSerialize(using = JsonDateSerializer.class) ");
		}

		return builder.toString();
	}

	private static String getForeignSearchable(Column c) {
		if (c.isForeignSearchable()) {
			StringBuilder builder = new StringBuilder(56);
			builder.append(" @JsonProperty");
			builder.append("(\"");
			builder.append(c.getSqlName());
			builder.append("\") ");
			return builder.toString();
		} else {
			return "";
		}

	}

	public static String getNotRequiredHibernateValidatorExpression(Column c) {
		String result = "";
		if (c.getSqlName().indexOf("mail") >= 0) {
			result += "@Email ";
		}
		if (DatabaseDataTypesUtils.isString(c.getJavaType())) {
			result += String.format("@Length(max=%s)", c.getSize());
		}
		if (DatabaseDataTypesUtils.isIntegerNumber(c.getJavaType())) {
			String javaType = DatabaseDataTypesUtils.getPreferredJavaType(c.getSqlType(), c.getSize(), c.getDecimalDigits());
			if (javaType.toLowerCase().indexOf("short") >= 0) {
				result += " @Max(" + Short.MAX_VALUE + ")";
			} else if (javaType.toLowerCase().indexOf("byte") >= 0) {
				result += " @Max(" + Byte.MAX_VALUE + ")";
			}
		}
		return result.trim();
	}

	public static final String BLANK = " ";

	/** 得到rapid validation的验证表达式 */
	public static String getRapidValidation(Column c) {
		StringBuilder builder = new StringBuilder();
		if (c.isDefineForeignInfo()) {
			return builder.toString();
		}
		if (StringUtils.hasLength(c.getJavascriptValidatorExprssion())) {
			builder.append(BLANK);
			builder.append(c.getJavascriptValidatorExprssion());
		}
		if (c.getSqlName().indexOf("mail") >= 0 && !containJSExpression(c, "validate-email")) {
			builder.append(BLANK);
			builder.append("validate-email");
		}
		if (DatabaseDataTypesUtils.isFloatNumber(c.getJavaType()) && !containJSExpression(c, "validate-number")) {
			builder.append(BLANK);
			builder.append("validate-number");
		}
		if (DatabaseDataTypesUtils.isIntegerNumber(c.getJavaType()) && !containJSExpression(c, "validate-integer")) {
			builder.append(BLANK);
			builder.append("validate-integer");
			if (!containJSExpression(c, "max-value-")) {
				if (c.getJavaType().toLowerCase().indexOf("short") >= 0) {
					builder.append(BLANK);
					builder.append("max-value-" + Short.MAX_VALUE);
				} else if (c.getJavaType().toLowerCase().indexOf("integer") >= 0) {
					builder.append(BLANK);
					builder.append("max-value-" + Integer.MAX_VALUE);
				} else if (c.getJavaType().toLowerCase().indexOf("byte") >= 0) {
					builder.append(BLANK);
					builder.append("max-value-" + Byte.MAX_VALUE);
				}
			}
		}
		return builder.toString();
	}

	private static boolean containJSExpression(Column c, String expr) {
		String jsExpr = c.getJavascriptValidatorExprssion();
		if (!StringUtils.hasLength(jsExpr)) {
			return false;
		}
		return jsExpr.indexOf(expr) > 0;
	}

}
