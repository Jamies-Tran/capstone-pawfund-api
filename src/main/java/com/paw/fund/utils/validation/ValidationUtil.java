package com.paw.fund.utils.validation;

import com.paw.fund.configuration.handler.exceptions.IllegalArgumentException;
import com.paw.fund.configuration.handler.exceptions.NullPointerException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ValidationUtil {
    public static void validateArgumentNotNull(Object argument) {
        Optional.ofNullable(argument).orElseThrow(IllegalArgumentException::new);
    }

    public static void validateArgumentListNotNull(Collection<?> arguments) {
        if(Objects.isNull(arguments) || arguments.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNotNullPointerException(Object argument) {
        Optional.ofNullable(argument).orElseThrow(NullPointerException::new);
    }

    public static List<LocalDateTime> validateNotNullOrDefaultTimeRange(List<LocalDateTime> timeRange) {
        if(!CollectionUtils.isEmpty(timeRange)) {
            return timeRange;
        } else {
            if(timeRange.isEmpty()) {
                return List.of(
                        LocalDateTime.now().minusDays(7),
                        LocalDateTime.now());
            } else if(timeRange.size() == 1) {
                return List.of(
                        timeRange.getFirst().minusDays(7),
                        timeRange.getFirst());
            } else {
                return timeRange;
            }
        }


    }
}
