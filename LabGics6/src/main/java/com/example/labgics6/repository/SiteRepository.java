package com.example.labgics6.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.labgics6.entity.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {
}
