package org.codelogger.utils.exceptions;

public class InvalidRangeException extends RuntimeException
{
  private static final long serialVersionUID = 4389475119959062339L;

  public InvalidRangeException(String message)
  {
    super(message);
  }

  public InvalidRangeException(String message, Throwable e)
  {
    super(message, e);
  }
}