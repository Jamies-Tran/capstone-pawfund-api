package com.paw.fund.utils.request;

import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Builder
public record PageRequestCustom(PageRequest pageRequest) {
    public static PageRequestCustom of(int current, int pageSize) {
        return PageRequestCustom.builder()
                .pageRequest(PageRequest.of(current, pageSize))
                .build();
    }

    public static PageRequestCustom of(int current, int pageSize, String sorter) {
        return PageRequestCustom.builder()
                .pageRequest(PageRequest.of(current, pageSize, configSorter(sorter)))
                .build();
    }

    private static Sort configSorter(String sorter) {
        String[] splitSorter = sorter.split("_");
        if(splitSorter.length == 1) {
            return Sort.by(Sort.Direction.DESC, splitSorter[0]);
        } else if(splitSorter.length == 2) {
            return switch (splitSorter[1].toLowerCase()) {
                case "asc" -> Sort.by(Sort.Direction.ASC, splitSorter[0]);
                case "desc" -> Sort.by(Sort.Direction.DESC, splitSorter[0]);
                default -> Sort.by(splitSorter[0]);
            };
        } else {
            return Sort.by(splitSorter[0]);
        }
    }
}
