package com.paw.fund.app.modules.pet_management.controller.type.v2.pub;

import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.utils.response.PageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v2/public/pet/type")
public interface IPetTypeV2PubAPI {
    @GetMapping
    PageResponse<PetTypeResponse> getPetTypeList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}
