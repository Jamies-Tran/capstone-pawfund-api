package com.paw.fund.app.modules.pet_management.controller.pet.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.pet.models.PetResponse;
import com.paw.fund.utils.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/public/pet")
@Tag(name = "Pet V1", description = "QL thú cưng")
public interface IPetV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh sách thú cưng",
            description = """
                    - Danh sách thú cưng
                    - [USER - Người dùng]
                    """)
    PageResponse<PetResponse> getPetList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "shelterId", defaultValue = "")
            Long shelterId,

            @RequestParam(required = false, value = "timeRange", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "receivedAtTimeRange", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            List<LocalDate> receivedAtTimeRange,

            @RequestParam(required = false, value = "dateOfBirthTimeRange", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            List<LocalDate> dateOfBirthTimeRange,

            @RequestParam(required = false, value = "petTypeCodes", defaultValue = "")
            List<String> petTypeCodes,

            @RequestParam(required = false, value = "petBreedCodes", defaultValue = "")
            List<String> petBreedCodes,

            @RequestParam(required = false, value = "petHobbyCodes", defaultValue = "")
            List<String> petHobbyCodes,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "receiveSourceCodes", defaultValue = "")
            List<String> receiveSourceCodes,

            @RequestParam(required = false, value = "sorter", defaultValue = "petName")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSized", defaultValue = "25")
            Integer pageSized
    );
}
