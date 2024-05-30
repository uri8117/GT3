package cat.uvic.teknos.gt3.file.jpa.models;

import jakarta.persistence.*;

import cat.uvic.teknos.gt3.domain.models.Circuit;
import cat.uvic.teknos.gt3.domain.models.Race;
import cat.uvic.teknos.gt3.domain.models.RaceDriver;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RACE")
public class RaceImpl implements Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RACE")
    private int id;

    @Column(name = "RACE_NAME", nullable = false)
    private String raceName;

    @Column(name = "RACE_DATE", nullable = false)
    private LocalDate raceDate;

    @ManyToOne(targetEntity = CircuitImpl.class)
    @JoinColumn(name = "ID_CIRCUIT")
    private Circuit circuit;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, targetEntity = RaceDriverImpl.class)
    private Set<RaceDriver> raceDrivers = new HashSet<>();

    // Getters and setters

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getRaceName() {
        return raceName;
    }

    @Override
    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    @Override
    public LocalDate getRaceDate() {
        return raceDate;
    }

    @Override
    public void setRaceDate(LocalDate raceDate) {
        this.raceDate = raceDate;
    }

    @Override
    public Circuit getCircuit() {
        return circuit;
    }

    @Override
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    @Override
    public Set<RaceDriver> getRaceDrivers() {
        return raceDrivers;
    }

    @Override
    public void setRaceDrivers(Set<RaceDriver> raceDrivers) {
        this.raceDrivers = raceDrivers;
    }
}
