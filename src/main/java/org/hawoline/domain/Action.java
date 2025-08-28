package org.hawoline.domain;

public interface Action<T> {
  void act(T t);
}
