package com.ahaines.utils;

public class MemoryUtil {
	
	private static final Runtime RUNTIME = Runtime.getRuntime();

	public static long getMemoryUse(){
	    putOutTheGarbage();
	    long totalMemory = RUNTIME.totalMemory();

	    putOutTheGarbage();
	    long freeMemory = RUNTIME.freeMemory();

	    return (totalMemory - freeMemory);
	  }

	  public static void putOutTheGarbage() {
	    collectGarbage();
	    collectGarbage();
	  }

	  private static void collectGarbage() {
	      System.gc();
	      System.runFinalization();
	  }
}
