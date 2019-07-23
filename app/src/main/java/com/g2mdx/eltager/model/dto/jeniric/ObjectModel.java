package com.g2mdx.eltager.model.dto.jeniric;

public class ObjectModel <T> {

    private T model;

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
