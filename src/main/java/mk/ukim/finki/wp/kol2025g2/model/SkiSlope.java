package mk.ukim.finki.wp.kol2025g2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class SkiSlope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer length;
    @Enumerated(EnumType.STRING)
    private SlopeDifficulty difficulty;
    @ManyToOne
    private SkiResort skiResort;

    private boolean closed = false;

    public SkiSlope(String name, Integer length, SlopeDifficulty difficulty, SkiResort skiResort, boolean closed) {
        this.name = name;
        this.length = length;
        this.difficulty = difficulty;
        this.skiResort = skiResort;
        this.closed = closed;
    }

    public SkiSlope(String name, Integer length, SlopeDifficulty difficulty, SkiResort skiResort) {
        this.name = name;
        this.length = length;
        this.difficulty = difficulty;
        this.skiResort = skiResort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public SlopeDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(SlopeDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public SkiResort getSkiResort() {
        return skiResort;
    }

    public void setSkiResort(SkiResort skiResort) {
        this.skiResort = skiResort;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
