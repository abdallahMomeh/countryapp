package com.g2mdx.eltager.model.dto.jeniric;

import java.util.List;

public class ListModel<T> {

    private List<T> model;

    public List<T> getModel() {
        return model;
    }

    public void setModel(List<T> model) {
        this.model = model;
    }
}
