package com.piggymetrics.account.domain;

public enum Currency {

	CNY, USD, EUR, RUB;

	public static Currency getDefault() {
		return CNY;
	}
}
