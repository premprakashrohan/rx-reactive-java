package com.ps.demo;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import yahoofinance.Stock;

public class ReactiveResource {

	public static void main(String[] args) {
		Observable<Stock> stockQuote = new ReactiveResource().getStockQuote();
		stockQuote.subscribe(stock -> callBack(stock),
							throwable -> errorCallBack(throwable),
							() -> completeCallBack());
	}

	private static Object completeCallBack() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object errorCallBack(Throwable throwable) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Action1 callBack(Stock stock) {
		System.out.println(
				String.format("Quote: %s"
						+ "  Price: %s"
						+ "  Day's High: %s"
						+ "  Day's Low: %s", stock.getSymbol(),
						stock.getQuote().getPrice(),stock.getQuote().getDayHigh(),stock.getQuote().getDayLow()) 
				);
		return null;
	}

	private static Action0 onComplete() {
		System.out.println("Completed Successfully!!!");
		return null;
	}

	private static Action1 onError() {
		return null;
	}

	private Observable<Stock> getStockQuote() {
		return new StackService().getStock();
	}

}
