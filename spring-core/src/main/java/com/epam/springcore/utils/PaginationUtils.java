package com.epam.springcore.utils;

import java.util.Collections;
import java.util.List;

public class PaginationUtils {

  private PaginationUtils() {
    throw new UnsupportedOperationException();
  }

  public static <T> List<T> paginateList(List<T> list, int pageSize, int pageNum) {
    if (pageSize <= 0 || pageNum <= 0) {
      throw new IllegalArgumentException("invalid page size: " + pageSize);
    }

    int fromIndex = (pageNum - 1) * pageSize;
    if (list.size() <= fromIndex) {
      return Collections.emptyList();
    }

    return list.subList(fromIndex, Math.min(fromIndex + pageSize, list.size()));
  }
}
