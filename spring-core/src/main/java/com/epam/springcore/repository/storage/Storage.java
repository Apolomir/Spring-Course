package com.epam.springcore.repository.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface Storage<T> {

  void init();

  Map<Long, T> getStorage();

  default Map<Long, T> readFile(String filePath, Class<T> tClass) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<Long, T> result = new HashMap<>();
    objectMapper
        .readValue(new File(filePath), new TypeReference<Map<Long, T>>() {})
        .forEach(
            (key, value) -> {
              try {
                String tmp = objectMapper.writeValueAsString(value);
                result.put(key, objectMapper.readValue(tmp, tClass));
              } catch (JsonProcessingException e) {
                throw new IllegalStateException(e);
              }
            });
    return result;
  }
}
