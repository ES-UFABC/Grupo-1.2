package com.es.agriculturafamiliar.config.email;

public abstract class AsyncMessageSenderFactory<T, U, K> {
    public abstract K createAsyncMessageSender(T data, U sender);
}
