package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EHealthStatus {
    HEALTHY("HEALTHY", "Khỏe mạnh"),
    FIT("FIT", "Cường tráng, sung sức"),
    IN_GOOD_CONDITION("IN_GOOD_CONDITION", "Trong tình trạng tốt"),
    UNDERWEIGHT("UNDERWEIGHT", "Thiếu cân"),
    OVERWEIGHT("OVERWEIGHT", "Thừa cân"),
    MILDLY_ILL("MILDLY_ILL", "Bị bệnh nhẹ"),
    LETHARGIC("LETHARGIC", "Mệt mỏi, ít hoạt động"),
    DEHYDRATED("DEHYDRATED", "Mất nước"),
    STRESS("STRESS", "Bị căng thẳng"),
    SICK("SICK", "Bị bệnh"),
    INJURED("INJURED", "Bị thương"),
    SEVERELY_ILL("SEVERELY_ILL", "Bị bệnh nặng"),
    DISABLE("DISABLE", "Bị khuyết tật"),
    CRITICAL_CONDITION("CRITICAL_CONDITION", "Tình trạng nguy kịch"),
    DYING("DYING", "Hấp hối"),
    DECEASED("DECEASED", "Đã chết"),
    NONE("NONE", "");


    String code;

    String name;

    public static EHealthStatus getValueOf(String code) {
        return Stream.of(values())
                .filter(healthStatus -> Objects.equals(healthStatus.getCode(), code))
                .findAny()
                .orElse(EHealthStatus.NONE);
    }
}
