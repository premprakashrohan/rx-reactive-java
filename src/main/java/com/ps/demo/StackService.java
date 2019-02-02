package com.ps.demo;

import java.util.Arrays;
import java.util.Objects;

import rx.Observable;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StackService {
	private static String[] quotes = { "GOOG", "AAPL","INTC","BABA","TSLA","AIR.PA" };

	public Observable<Stock> getStock() {
		return Observable.create(subscriber -> {
			if (!subscriber.isUnsubscribed()) {
				Arrays.stream(quotes).map(this::getStock).filter(Objects::nonNull).forEach(
						stock ->{
						
						subscriber.onNext(stock);
						sleep(1000);
						}
						);
				subscriber.onCompleted();
			}
		});

	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private Stock getStock(String quote) {
		try {
			return YahooFinance.get(quote);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
