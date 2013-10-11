package com.gavinmhackeling.frequentitemset;

import com.google.common.hash.BloomFilter;

public class BloomFilterTe {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringFunnel stringFunnel = new StringFunnel();
		BloomFilter<String> bloomFilter = BloomFilter.create(stringFunnel, 1000); 
		
		bloomFilter.put("a");
		bloomFilter.put("b");
		bloomFilter.put("b");
		
		if (bloomFilter.mightContain("a"))
			System.out.println("the filter might contain a");
		else System.out.println("the filter does not contain a");
		if (bloomFilter.mightContain("c"))
			System.out.println("the filter might contain c");
		else System.out.println("the filter does not contain c");
	}

}
