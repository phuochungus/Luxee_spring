package me.phuochung.luxee.variant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {}
