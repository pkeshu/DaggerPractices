package com.keshar.daggerpractice.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.keshar.daggerpractice.ui.auth.AuthResource;

public class Resource<T> {

    @NonNull
    public final Resource.Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(@Nullable String message, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, message);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    public enum Status {SUCCESS, ERROR, LOADING}
}
