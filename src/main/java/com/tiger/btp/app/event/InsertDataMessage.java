package com.tiger.btp.app.event;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class InsertDataMessage extends DataMessage {

    List<Map<String, Object>> insertObjects = new ArrayList<>();

}
