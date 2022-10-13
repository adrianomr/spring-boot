package com.example.demo;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class AnswerWithDelay<T> implements Answer<T> {
  private final T t;

  public AnswerWithDelay(T t) {
    this.t = t;
  }

  @Override
  public T answer(InvocationOnMock invocationOnMock) {
    try {
      Thread.sleep( 2000L );
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return t;
  }
}