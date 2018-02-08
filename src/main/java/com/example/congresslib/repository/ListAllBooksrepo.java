package com.example.congresslib.repository;

import com.example.congresslib.Addbooks;
import org.springframework.data.repository.CrudRepository;

public interface ListAllBooksrepo extends CrudRepository<Addbooks, Long> {
    Iterable<Addbooks> findAllByBooktitle(String booktitle);

}
