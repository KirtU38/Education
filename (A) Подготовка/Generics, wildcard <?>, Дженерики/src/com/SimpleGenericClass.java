package com;

import java.util.ArrayList;
import java.util.List;

public class SimpleGenericClass<T> {

    T object;

    public SimpleGenericClass(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Class<?> getType() {
        return object.getClass();
    }
}
// Мы не можем задать <T super Human>, тк это всё равно приводит к Object(надо почитать) и смысл Обобщения теряется,
// но мы может задать потом нужный тип в параметрах метода (SimpleGenericClass<? super Human>)
