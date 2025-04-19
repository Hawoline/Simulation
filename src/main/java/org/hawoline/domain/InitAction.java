package org.hawoline.domain;

public abstract class InitAction<T> implements Action {
    private T result;
    private boolean isInit = false;

    public T act() {
        if (!isInit) {
            result = init();
            isInit = true;
        }
        return result;
    }

    abstract T init();
}
