package com.unipi.mns.mnscrm00.dal;

import com.unipi.mns.mnscrm00.entities.config.GenericConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericConfigRepository extends JpaRepository<GenericConfigEntity, String> {
    List<GenericConfigEntity> findByType(String type);
}
