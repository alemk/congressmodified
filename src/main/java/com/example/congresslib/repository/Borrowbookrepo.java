package com.example.congresslib.repository;

import com.example.congresslib.Addbooks;
import com.example.congresslib.Borrowers;
import org.springframework.data.repository.CrudRepository;

public interface Borrowbookrepo extends CrudRepository<Addbooks, Long> {

}
