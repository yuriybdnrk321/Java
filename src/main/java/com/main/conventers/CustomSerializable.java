package com.main.conventers;

import java.util.Map;

public interface CustomSerializable {
    String serialize();
    Object deserialize(String data);
}
