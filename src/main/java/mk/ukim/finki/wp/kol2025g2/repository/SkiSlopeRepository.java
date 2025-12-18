package mk.ukim.finki.wp.kol2025g2.repository;

import mk.ukim.finki.wp.kol2025g2.model.SkiSlope;
import mk.ukim.finki.wp.kol2025g2.model.SlopeDifficulty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkiSlopeRepository extends JpaRepository<SkiSlope, Long> {
    @Query("SELECT s FROM SkiSlope s WHERE " +
            "(:name IS NULL OR s.name LIKE %:name%) AND " +
            "(:length IS NULL OR s.length > :length) AND " +
            "(:difficulty IS NULL OR s.difficulty = :difficulty) AND " +
            "(:skiResortId IS NULL OR s.skiResort.id = :skiResortId)")
    Page<SkiSlope> findByFilters(@Param("name") String name,
                                  @Param("length") Integer length,
                                  @Param("difficulty") SlopeDifficulty difficulty,
                                  @Param("skiResortId") Long skiResortId,
                                  Pageable pageable);
}
