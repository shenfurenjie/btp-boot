package com.tiger.btp.framework.graphql.model.condition.where;

import com.tiger.btp.framework.common.utils.JSONUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Where {

    List<Where> _and = new ArrayList<>();

    List<Where> _or = new ArrayList<>();

    List<Where> _not = new ArrayList<>();

    Map<String, CndOperation> propertyMap = new HashMap<>();


    public Where() {
    }

    public Where(String property, Object value) {
        this.eq(property, value);
    }


    public Where and(Where where) {
        this._and.add(where);
        return this;
    }

    public Where or(Where where) {
        this._or.add(where);
        return this;
    }

    public Where not(Where where) {
        this._not.add(where);
        return this;
    }

    public Where eq(String property, Object value) {
        propertyMap.put(property, CndOperation.eq(value));
        return this;
    }

    public Where neq(String property, Object value) {
        propertyMap.put(property, CndOperation.neq(value));
        return this;
    }

    public Where contain(String property, Object value) {
        propertyMap.put(property, CndOperation.contain(value));
        return this;
    }

    public Where isEmpty(String property, Object value) {
        propertyMap.put(property, CndOperation.isEmpty(value));
        return this;
    }

    public Where in(String property, Object... value) {
        propertyMap.put(property, CndOperation.in(value));
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder("{");
        for (String key : propertyMap.keySet()) {
            builder.append(key).append(":").append(JSONUtils.getJSONStringWithoutQuotation(propertyMap.get(key)));
        }
        appendWhere(builder, "_and", _and);
        appendWhere(builder, "_or", _or);
        appendWhere(builder, "_not", _not);
        builder.append("}");
        return builder.toString();
    }

    protected void appendWhere(StringBuilder builder, String ops, List<Where> where) {
        if (where == null || where.size() == 0) {
            return;
        }
        builder.append(ops).append(":[");
        List<String> list = new ArrayList<>();
        for (Where w : where) {
            list.add(w.build());
        }
        builder.append(StringUtils.join(list, ","));
        builder.append("]");
    }


    public String toString() {
        return build();
    }

}
