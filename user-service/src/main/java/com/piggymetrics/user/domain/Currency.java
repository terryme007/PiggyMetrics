package com.piggymetrics.user.domain;

public enum Currency {

	CNY, USD, EUR, RUB;

	public static Currency getDefault() {
		return CNY;
	}
}
