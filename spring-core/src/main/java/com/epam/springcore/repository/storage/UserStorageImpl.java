package com.epam.springcore.repository.storage;

import com.epam.springcore.model.User;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserStorageImpl implements Storage<User> {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserStorageImpl.class);

  private String filePath;
  private Map<Long, User> storage;

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void init() {
    LOGGER.debug("File path: {}", filePath);
    try {
      this.storage = readFile(filePath, User.class);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public Map<Long, User> getStorage() {
    return storage;
  }
}
