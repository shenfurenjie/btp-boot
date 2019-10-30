package com.tiger.btp.framework.model.condition;

import com.tiger.btp.framework.model.enums.OrderByEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SortCnd {

    String colName;

    OrderByEnum order = OrderByEnum.asc;
}
