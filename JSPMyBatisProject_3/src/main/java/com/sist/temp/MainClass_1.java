package com.sist.temp;

public class MainClass_1 {
	public static void main(String[] args) {
		String price="20,300원";
		price=price.replaceAll("[^0-9]", "");
		System.out.println(price);
	}
	
}
