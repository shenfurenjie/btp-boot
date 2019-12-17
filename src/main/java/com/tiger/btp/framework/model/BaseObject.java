package com.tiger.btp.framework.model;

import java.util.HashMap;

public class BaseObject extends HashMap<String, Object> {

    private static final long serialVersionUID = 6722718231506045499L;

    /**
     * @param el    a.b.c  a
     * @param value
     */
    public void setValue(String el, Object value) {
        BaseObject toPut = this;
        int fromIndex = 0;
        int index = el.indexOf('.');
        String name = el;
        while (index != -1) {
            String p = el.substring(fromIndex, index);
            BaseObject o = (BaseObject) toPut.get(p);
            if (o == null) {
                o = new BaseObject();
                toPut.put(p, o);
            }
            fromIndex = index + 1;
            toPut = o;
            name = el.substring(fromIndex);
            index = el.indexOf('.', index + 1);

        }
        toPut.put(name, value);
    }

    /**
     * @param el    a.b.c  a
     * @param value
     */
    public BaseObject setValueReturn(String el, Object value) {
        BaseObject toPut = this;
        int fromIndex = 0;
        int index = el.indexOf('.');
        String name = el;
        while (index != -1) {
            String p = el.substring(fromIndex, index);
            BaseObject o = (BaseObject) toPut.get(p);
            if (o == null) {
                o = new BaseObject();
                toPut.put(p, o);
            }
            fromIndex = index + 1;
            toPut = o;
            name = el.substring(fromIndex);
            index = el.indexOf('.', index + 1);

        }
        toPut.put(name, value);
        return toPut;
    }

    public Object getValue(String el) {
        /**
         * TODO 增强.
         */
        BaseObject toPut = this;
        int fromIndex = 0;
        int index = el.indexOf('.');
        String name = el;
        while (index != -1) {
            String p = el.substring(fromIndex, index);
            BaseObject o = (BaseObject) toPut.get(p);
            if (o == null) {
                return null;
            }
            fromIndex = index + 1;
            toPut = o;
            name = el.substring(fromIndex);
            index = el.indexOf('.', index + 1);
        }
        return toPut.get(name);
    }

}
