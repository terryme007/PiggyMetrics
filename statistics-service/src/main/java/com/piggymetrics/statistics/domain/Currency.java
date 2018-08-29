package com.piggymetrics.statistics.domain;

public enum Currency {

	CNY, USD, EUR, RUB;

	public static Currency getBase() {
		return CNY;
	}
}
