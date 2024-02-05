package me.phuochung.luxee.product;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @NotNull
    @EntityGraph(value = "product-graph")
    Optional<Product> findById(@NotNull @Param("id") Long id);
}
