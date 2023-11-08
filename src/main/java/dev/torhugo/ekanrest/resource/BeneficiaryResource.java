package dev.torhugo.ekanrest.resource;

import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryInclusionDTO;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryResponseDTO;
import dev.torhugo.ekanrest.service.BeneficiaryService;
import dev.torhugo.ekanrest.util.resource.ResourceUtil;
import dev.torhugo.ekanrest.util.resource.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beneficiary")
@RequiredArgsConstructor
public class BeneficiaryResource implements ResourceUtil {

    private final BeneficiaryService service;

    @PostMapping("/create")
    public ResponseEntity<ResponseUtil<BeneficiaryResponseDTO>> createBeneficiary(@RequestBody final BeneficiaryInclusionDTO beneficiary){
        return returnSuccess(service.createBeneficiary(beneficiary));
    }
}
