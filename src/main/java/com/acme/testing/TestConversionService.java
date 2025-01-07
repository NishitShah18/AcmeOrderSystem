package com.acme.testing;

import com.acme.utils.ConversionService;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestConversionService {
	public static void main(String[] args) {
		System.out.println(ConversionService.fluidOunces(1.1f));
		System.out.println(ConversionService.gallons(2.2f));
		System.out.println(ConversionService.grams(BigInteger.valueOf(30)));
		System.out.println(ConversionService.milliliters(40));
		System.out.println(ConversionService.ounces(BigDecimal.valueOf(50)));
		System.out.println(ConversionService.pints(6.6f));
		System.out.println(ConversionService.pounds(BigDecimal.valueOf(7.7)));

		// can you figure out why these do not compile
		//Cannot do a downcast, double to float, implicitly
//		ConversionService.fluidOunces(1.1);
		//Cannot do a downcast, long to int, implicitly
//		ConversionService.grams(30L);
		//Need an explicit cast to go from double to int
//		ConversionService.milliliters(4.0);
		//Cannot downcast return int to a short implicitly
//		short grams = ConversionService.grams(30);
		//Cannot downcast return int to a byte implicitly
//		byte byteGrams = ConversionService.grams(30);


		// why do these still work even though the types are different
		//– all of these method calls are performing an implicit upcast
		System.out.println("------------------------------------");
		double ounces = ConversionService.fluidOunces(1.1f);
		System.out.println(ounces);
		long milliliters = ConversionService.milliliters(40);
		System.out.println(milliliters);
		double decimalmilliliters = ConversionService.milliliters(40);
		System.out.println(decimalmilliliters);
		short s = 30;
		System.out.println(ConversionService.grams(BigInteger.valueOf(s)));
		byte b = 4;
		System.out.println(ConversionService.milliliters(b));
		char z = 'z';
		System.out.println(ConversionService.milliliters(z));
		System.out.println(ConversionService.gallons(200));
		System.out.println(ConversionService.ounces(BigDecimal.valueOf(50.5f)));
		System.out.println(ConversionService.pints(6L));
		System.out.println(ConversionService.pounds(BigDecimal.valueOf(7L)));

		// compare these results.  Can you tell why they are different?

		System.out.println("------------------------------------");
		float bigGallons = ConversionService.gallons(123456789123456789L);
		System.out.println(bigGallons);
		////precision is lost due to working large numbers and float type
		double bigGallons2 = 123456789123456789L * 0.2642;
		System.out.println(bigGallons2);
		////precision is retained due to working with doubles and large numbers

		System.out.println("------------------------------------");
		BigInteger bigGrams = ConversionService.grams(BigInteger.valueOf(1234567890));
		System.out.println(bigGrams);
		////precision is lost due to multiplying 2 ints
		long bigGrams2 = 1234567890L * 1000L;
		System.out.println(bigGrams2);
		////precision is maintained using longs

	}

}
