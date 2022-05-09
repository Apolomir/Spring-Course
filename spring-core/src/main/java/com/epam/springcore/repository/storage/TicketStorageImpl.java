package com.epam.springcore.repository.storage;

import com.epam.springcore.model.Ticket;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketStorageImpl implements Storage<Ticket> {

  private static final Logger LOGGER = LoggerFactory.getLogger(TicketStorageImpl.class);

  private String filePath;
  private Map<Long, Ticket> storage;

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void init() {
    LOGGER.debug("File path: {}", filePath);
    try {
      this.storage = readFile(filePath, Ticket.class);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public Map<Long, Ticket> getStorage() {
    return storage;
  }
}
