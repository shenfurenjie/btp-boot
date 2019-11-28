package com.tiger.btp.framework.graphql.model.condition.order;

import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class OrderBy {

    List<Map<String, String>> _orders = new ArrayList<>();


    public OrderBy() {

    }

    public OrderBy(String property) {
        this.asc(property);
    }

    protected OrderBy add(String property, String order) {
        _orders.add(ImmutableMap.of(property, order));
        return this;
    }


    public OrderBy asc(String property) {
        this.add(property, "asc");
        return this;
    }


    public OrderBy ascNullsLast(String property) {
        this.add(property, "asc_nulls_last");
        return this;
    }


    public OrderBy ascNullsFirst(String property) {
        this.add(property, "asc_nulls_first");
        return this;
    }


    public OrderBy desc(String property) {
        this.add(property, "desc");
        return this;
    }

    public OrderBy descNullsFirst(String property) {
        this.add(property, "desc_nulls_first");
        return this;
    }


    public OrderBy descNullsLast(String property) {
        this.add(property, "desc_nulls_last");
        return this;
    }

    public String toString() {
        if (this._orders == null || this._orders.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder("[");
        List<String> list = new ArrayList<>();
        for (Map<String, String> key : _orders) {
            String add = "{";
            for (String k : key.keySet()) {
                add += (k + ":" + key.get(k));
            }
            add += "}";
            list.add(add);
        }
        builder.append(StringUtils.join(list, ","));
        builder.append("]");
        return builder.toString();
    }


}
