package com.tkmtwo.util.charparse;


public class NumberOfColumnsException
  extends RuntimeException
{
  private int columnsExpected;
  private int columnsReceived;

  public NumberOfColumnsException()
  {
  }
  
  public NumberOfColumnsException(String s, int e, int r)
  {
    super(s);
    columnsExpected = e;
    columnsReceived = r;
  }
  
  public NumberOfColumnsException(String s, Throwable t, int e, int r)
  {
    super(s, t);
    columnsExpected = e;
    columnsReceived = r;
  }
  
  public NumberOfColumnsException(Throwable t, int e, int r)
  {
    super(t);
    columnsExpected = e;
    columnsReceived = r;
  }
  
  public int getColumnsExpected() { return columnsExpected; }
  public int getColumnsReceived() { return columnsReceived; }
  
}
