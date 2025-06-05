package com.tut.service;

import com.tut.common.dto.FineDto;
import com.tut.common.entity.Fine;

import java.util.List;

public interface FineService {
    FineDto calculateFine(Long issueBookId);
   // Fine payFine(Long fineId);
    List<FineDto> getAllFines();
    FineDto getFineById(Long fineId);
    void deleteFine(Long fineId);

    FineDto updateFine(FineDto dto);
}
