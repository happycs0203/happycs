package com.changsu.project.domain.persons;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonsRepository extends JpaRepository<Persons, Long> {
}
