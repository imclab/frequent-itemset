package com.gavinmhackeling.frequentitemset;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

public class StringFunnel implements Funnel<String> 
{
	private static final long serialVersionUID = 852688739309678887L;

	public void funnel(String string, PrimitiveSink arg1) 
	{
		arg1.putString(string, Charsets.UTF_8);
	}

}
