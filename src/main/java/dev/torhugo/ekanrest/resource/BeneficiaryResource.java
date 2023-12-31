package dev.torhugo.ekanrest.resource;

import dev.torhugo.ekanrest.lib.data.dto.*;
import dev.torhugo.ekanrest.service.BeneficiaryService;
import dev.torhugo.ekanrest.service.DocumentService;
import dev.torhugo.ekanrest.util.resource.ResourceUtil;
import dev.torhugo.ekanrest.util.resource.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficiary")
@RequiredArgsConstructor
public class BeneficiaryResource implements ResourceUtil {

    private final BeneficiaryService service;
    private final DocumentService documentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseUtil<BeneficiaryResponseDTO>> createBeneficiary(
            @RequestBody final BeneficiaryInclusionDTO beneficiary
    ){
        return returnSuccess(service.createBeneficiary(beneficiary));
    }

    @PostMapping("/add-document/{beneficiaryId}")
    public ResponseEntity<ResponseUtil<BeneficiaryResponseDTO>> addDocument(
            @PathVariable final Long beneficiaryId,
            @RequestBody final List<DocumentInclusionDTO> documents
    ){
        return returnSuccess(service.addDocument(beneficiaryId, documents));
    }

    @PutMapping("/update-beneficiary/{beneficiaryId}")
    public ResponseEntity<ResponseUtil<BeneficiaryFullResponseDTO>> updateBeneficiary(
            @PathVariable final Long beneficiaryId,
            @RequestBody final BeneficiaryBaseDTO newBeneficiary
    ){
        return returnSuccess(service.updateBeneficiary(beneficiaryId, newBeneficiary));
    }

    @DeleteMapping("/delete-beneficiary/{beneficiaryId}")
    public ResponseEntity<ResponseUtil<BeneficiaryResponseDTO>> deleteBeneficiary(
            @PathVariable final Long beneficiaryId
    ){
        return returnSuccess(service.deleteBeneficiary(beneficiaryId));
    }

    @PostMapping("/reactivate")
    public ResponseEntity<ResponseUtil<BeneficiaryResponseDTO>> reactivateBeneficiary(
            @RequestParam(value = "name") final String name
    ){
        return returnSuccess(service.reactivateBeneficiary(name));
    }

    @GetMapping("/retrieve/all")
    public ResponseEntity<ResponseUtil<List<BeneficiariesDTO>>> getAllBeneficiaries(){
        return returnSuccess(service.getAllBeneficiaries());
    }

    @GetMapping("/retrieve/{beneficiaryId}")
    public ResponseEntity<ResponseUtil<BeneficiariesDTO>> getBeneficiary(
            @PathVariable final Long beneficiaryId
    ){
        return returnSuccess(service.getBeneficiaryById(beneficiaryId));
    }

    @GetMapping("/retrieve/documents/{beneficiaryId}")
    public ResponseEntity<ResponseUtil<List<DocumentDTO>>> getDocuments(
            @PathVariable final Long beneficiaryId
    ){
        return returnSuccess(documentService.retrieveDocumentByBeneficiaryId(beneficiaryId));
    }
}
