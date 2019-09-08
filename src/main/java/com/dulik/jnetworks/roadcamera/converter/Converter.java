package com.dulik.jnetworks.roadcamera.converter;

public interface Converter<R, T> {

    R convert(T object);
}