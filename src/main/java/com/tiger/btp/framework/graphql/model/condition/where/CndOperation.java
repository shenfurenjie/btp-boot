package com.tiger.btp.framework.graphql.model.condition.where;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class CndOperation {

    @JSONField(name = "_eq")
    Object _eq;

    @JSONField(name = "_neq")
    Object _neq;

    @JSONField(name = "_like")
    Object _like;

    @JSONField(name = "_isNull")
    Object _isNull;

    @JSONField(name = "_in")
    List<Object> _in;

    @JSONField(name = "_nin")
    List<Object> _nin;

    public static CndOperation eq(Object value) {
        CndOperation operation = new CndOperation();
        operation.set_eq(value);
        return operation;
    }

    public static CndOperation in(Object... value) {
        CndOperation operation = new CndOperation();
        operation.set_in(Lists.newArrayList(value));
        return operation;
    }

    public static CndOperation neq(Object value) {
        CndOperation operation = new CndOperation();
        operation.set_neq(value);
        return operation;
    }

    public static CndOperation startWith(Object value) {
        CndOperation operation = new CndOperation();
        operation.set_like(value);
        return operation;
    }

    public static CndOperation contain(Object value) {
        CndOperation operation = new CndOperation();
        operation.set_like(value);
        return operation;
    }

    public static CndOperation isEmpty(Object value) {
        CndOperation operation = new CndOperation();
        operation.set_isNull(value);
        return operation;
    }
}
