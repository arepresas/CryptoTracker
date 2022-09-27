package stream.arepresas.cryptotracker.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import stream.arepresas.cryptotracker.shared.criteria.PaginationCriteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class DataUtils {

  public static boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

  public static boolean isNullOrEmpty(List<?> value) {
    return value == null || value.isEmpty();
  }

  public static String stringListToString(List<String> value) {
    return isNullOrEmpty(value) ? "" : value.stream().collect(Collectors.joining(","));
  }

  public static List<String> stringToList(String value) {
    return isNullOrEmpty(value) ? new ArrayList<>() : Arrays.stream(value.trim().split(",")).toList();
  }

  public static void logList(List<?> value) {
    value.forEach(object -> log.info(object.toString()));
  }

  public static void logCriteria(PaginationCriteria value) {
    log.info("Criteria - {}", value);
  }
}
