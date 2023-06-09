package com.course.graphql.datasource.problemz.repository;

import com.course.graphql.datasource.problemz.entity.Problemz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemzRepository extends CrudRepository<Problemz, UUID> {
}
