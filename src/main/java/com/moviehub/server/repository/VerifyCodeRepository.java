package com.moviehub.server.repository;

import com.moviehub.server.entity.VerifyCodeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyCodeRepository extends JpaRepository<VerifyCodeTable, String> {
}
