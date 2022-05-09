package com.epam.springcore.repository.storage;

import com.epam.springcore.model.Event;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventStorageImpl implements Storage<Event> {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventStorageImpl.class);

  private String filePath;
  private Map<Long, Event> storage;

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void init() {
    LOGGER.debug("File path: {}", filePath);
    try {
      this.storage = readFile(filePath, Event.class);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public Map<Long, Event> getStorage() {
    return storage;
  }
}
