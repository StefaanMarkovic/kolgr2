package mk.ukim.finki.wp.kol2025g2.service.impl;

import mk.ukim.finki.wp.kol2025g2.model.SkiResort;
import mk.ukim.finki.wp.kol2025g2.model.SkiSlope;
import mk.ukim.finki.wp.kol2025g2.model.SlopeDifficulty;
import mk.ukim.finki.wp.kol2025g2.model.exceptions.InvalidSkiSlopeIdException;
import mk.ukim.finki.wp.kol2025g2.repository.SkiSlopeRepository;
import mk.ukim.finki.wp.kol2025g2.service.SkiResortService;
import mk.ukim.finki.wp.kol2025g2.service.SkiSlopeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkiSlopeServiceImpl implements SkiSlopeService {

    private final SkiSlopeRepository skiSlopeRepository;
    private final SkiResortService skiResortService;

    public SkiSlopeServiceImpl(SkiSlopeRepository skiSlopeRepository, SkiResortService skiResortService) {
        this.skiSlopeRepository = skiSlopeRepository;
        this.skiResortService = skiResortService;
    }

    @Override
    public List<SkiSlope> listAll() {
        return this.skiSlopeRepository.findAll();
    }

    @Override
    public SkiSlope findById(Long id) {
        return this.skiSlopeRepository.findById(id)
                .orElseThrow(() -> new InvalidSkiSlopeIdException(id));
    }

    @Override
    public SkiSlope create(String name, Integer length, SlopeDifficulty difficulty, Long skiResort) {
        SkiResort resort = this.skiResortService.findById(skiResort);
        SkiSlope skiSlope = new SkiSlope(name, length, difficulty, resort);
        return this.skiSlopeRepository.save(skiSlope);
    }

    @Override
    public SkiSlope update(Long id, String name, Integer length, SlopeDifficulty difficulty, Long skiResort) {
        SkiSlope skiSlope = this.findById(id);
        SkiResort resort = this.skiResortService.findById(skiResort);

        skiSlope.setName(name);
        skiSlope.setLength(length);
        skiSlope.setDifficulty(difficulty);
        skiSlope.setSkiResort(resort);

        return this.skiSlopeRepository.save(skiSlope);
    }

    @Override
    public SkiSlope delete(Long id) {
        SkiSlope skiSlope = this.findById(id);
        this.skiSlopeRepository.delete(skiSlope);
        return skiSlope;
    }

    @Override
    public SkiSlope close(Long id) {
        SkiSlope skiSlope = this.findById(id);
        skiSlope.setClosed(true);
        return this.skiSlopeRepository.save(skiSlope);
    }

    @Override
    public Page<SkiSlope> findPage(String name, Integer length, SlopeDifficulty difficulty, Long skiResort, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return this.skiSlopeRepository.findByFilters(name, length, difficulty, skiResort, pageable);
    }
}
