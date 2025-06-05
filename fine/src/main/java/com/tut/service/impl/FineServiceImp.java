package com.tut.service.impl;

import com.repository.IssueBookRepository;
import com.tut.common.dto.FineDto;
import com.tut.common.entity.Fine;
import com.tut.common.entity.IssueBook;
import com.tut.common.exception.ResourceNotFoundException;
import com.tut.common.mapper.FineMapper;
import com.tut.repository.FineRepository;
import com.tut.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FineServiceImp implements FineService {
    @Autowired
    private FineRepository fineRepository;
    @Autowired
    private IssueBookRepository repository;


    @Override
    public FineDto calculateFine(Long issueBookId) {
        IssueBook issueBook= repository.findById(issueBookId).orElseThrow(() -> new ResourceNotFoundException("not get issue id"));
        LocalDate dueDate=issueBook.getDueDate();
        LocalDate returnDate=issueBook.getReturnDate();
        long daysLate=ChronoUnit.DAYS.between(dueDate, returnDate);

        if (daysLate > 0) {
            double fineAmount=daysLate * 5.0;

            FineDto fine=FineDto.builder()
                    .issue_book_id(issueBookId)
                    .amount(fineAmount)
                    .paid(false)
                    .paidDate(null)
                    .build();

          Fine f= FineMapper.toMap(fine,issueBook);
            Fine savedFine = fineRepository.save(f);

           return FineMapper.toDto(savedFine);

        }
        return null;
    }


    @Override
    public List<FineDto> getAllFines() {
        return fineRepository.findAll().stream()
                .map(FineMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public FineDto getFineById(Long fineId) {
        Fine  f=   fineRepository.findById(fineId)
                .orElseThrow(() -> new RuntimeException("Fine not found with ID: " + fineId));
        return FineMapper.toDto(f);
    }

    @Override
    public void deleteFine(Long fineId) {
        fineRepository.deleteById(fineId);
    }
    public FineDto updateFine(FineDto fineDto) {
        Fine fine = fineRepository.findById(fineDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Fine ID not found"));

        // Update fields
       if(fineDto.getAmount()!=null) fine.setAmount(fineDto.getAmount());
       if(fineDto.getPaid()!=null)fine.setPaid(fineDto.getPaid());
       if(fineDto.getPaidDate()!=null)fine.setPaidDate(fineDto.getPaidDate());

        // Save updated entity
        Fine updatedFine = fineRepository.save(fine);

        // Return DTO
        return FineMapper.toDto(updatedFine);
    }

}