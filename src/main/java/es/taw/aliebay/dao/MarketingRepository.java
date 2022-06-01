package es.taw.aliebay.dao;

import es.taw.aliebay.entity.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketingRepository extends JpaRepository<Marketing, Integer> {
}
