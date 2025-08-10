package org.hawoline.domain;

public interface Action<T, V> {
  V act(T t);
}
