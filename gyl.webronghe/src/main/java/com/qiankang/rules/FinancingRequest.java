package com.qiankang.rules;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FinancingRequest {
	private BigDecimal financingAmount; //融资金额
	private int months; //融资期限  月数
	
	public BigDecimal getFinancingAmount() {
		return financingAmount;
	}
	public void setFinancingAmount(BigDecimal financingAmount) {
		this.financingAmount = financingAmount;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
}
