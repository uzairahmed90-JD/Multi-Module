package com.tut.controller;

import com.tut.common.dto.FineDto;
import com.tut.common.entity.Fine;
import com.tut.service.FineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {


    private final FineService fineService;
    public FineController(FineService fineService) {
        this.fineService = fineService;
    }


    @PostMapping("/calculate")
    public ResponseEntity<FineDto> calculateFine(
            @RequestParam Long issueBookId) {

        return ResponseEntity.ok(fineService.calculateFine(issueBookId));
    }


    @PutMapping("/update")
    public ResponseEntity<FineDto> updateF(@RequestBody FineDto dto){
        return ResponseEntity.ok(fineService.updateFine(dto));
    }


    @GetMapping
    public ResponseEntity<List<FineDto>> getAllFines() {
        return ResponseEntity.ok(fineService.getAllFines());
    }


    @GetMapping("/{fineId}")
    public ResponseEntity<FineDto> getFineById(@PathVariable Long fineId) {
        return ResponseEntity.ok(fineService.getFineById(fineId));
    }


    @DeleteMapping("/{fineId}")
    public ResponseEntity<String> deleteFine(@PathVariable Long fineId) {
        fineService.deleteFine(fineId);
        return ResponseEntity.ok("Fine with ID " + fineId + " deleted successfully.");
    }
}
