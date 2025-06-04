package com.repository;

import com.tut.common.entity.IssueBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueBookRepository extends JpaRepository<IssueBook,Long> {
}
