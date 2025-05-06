package com.paw.fund.app.modules.pet_management.controller.hobby.v1;

import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyListRequest;
import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyRequest;
import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyResponse;
import com.paw.fund.utils.response.ListResponse;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/api/hobby")
@Tag(name = "Hobby V1", description = "QL sở thích")
public interface IHobbyV1API {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    PageResponse<HobbyResponse> getHobbyList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "sorter", defaultValue = "hobbyName")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );

    @PostMapping("/{petTypeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<HobbyResponse> createHobby(
            @PathVariable
            Long petTypeId,

            @Valid @RequestBody
            HobbyRequest request);

    @PostMapping("/{petTypeId}/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ListResponse<HobbyResponse> createHobbyList(
            @PathVariable
            Long petTypeId,

            @Valid @RequestBody
            HobbyListRequest request);
}
