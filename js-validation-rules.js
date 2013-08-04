﻿['validation-failed' , '验证失败.'],
	['required' , '请输入值.'],
	['validate-number' , '请输入有效的数字.'],
	['validate-digits' , '请输入数字.'],
	['validate-alpha' , '请输入英文字母.'],
	['validate-alphanum' , '请输入英文字母或是数字,其它字符是不允许的.'],
	['validate-email' , '请输入有效的邮件地址,如 username@example.com.'],
	['validate-url' , '请输入有效的URL地址.'],
	['validate-currency-dollar' , 'Please enter a valid $ amount. For example $100.00 .'],
	['validate-one-required' , '在前面选项至少选择一个.'],
	['validate-integer' , '请输入正确的整数'],
	['validate-pattern' , '输入的值不匹配'],
	['validate-ip','请输入正确的IP地址'],
	['min-value' , '最小值为%s'],
	['max-value' , '最大值为%s'],
	['min-length' , '最小长度为%s,当前长度为%s.'],
	['max-length', '最大长度为%s,当前长度为%s.'],
	['int-range' , '输入值应该为 %s 至 %s 的整数'],
	['float-range' , '输入值应该为 %s 至 %s 的数字'],
	['length-range' , '输入值的长度应该在 %s 至 %s 之间,当前长度为%s'],
	['equals','两次输入不一致,请重新输入'],
	['less-than','请输入小于前面的值'],
	['less-than-equal','请输入小于或等于前面的值'],
	['great-than','请输入大于前面的值'],
	['great-than-equal','请输入大于或等于前面的值'],
	['validate-date' , '请输入有效的日期,格式为 %s. 例如:%s.'],
	['validate-selection' , '请选择.'],
	['validate-file' , function(v,elm,args,metadata) {
		return ValidationUtils.format("文件类型应该为[%s]其中之一",[args.join(',')]);
	}],
	//中国特有的相关验证提示信息
	['validate-id-number','请输入合法的身份证号码'],
	['validate-chinese','请输入中文'],
	['validate-phone','请输入正确的电话号码,如:010-29392929,当前长度为%s.'],
	['validate-mobile-phone','请输入正确的手机号码,当前长度为%s.'],
	['validate-zip','请输入有效的邮政编码'],
	['validate-qq','请输入有效的QQ号码.']