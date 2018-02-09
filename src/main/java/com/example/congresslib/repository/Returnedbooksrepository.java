package com.example.congresslib.repository;

import com.example.congresslib.Addbooks;
import org.springframework.data.repository.CrudRepository;

public interface Returnedbooksrepository extends CrudRepository<Addbooks, Long> {
    Iterable<Addbooks> findAllById(long id);
}
