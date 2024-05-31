package cat.uvic.teknos.gt3.file.jpa.repository;

import cat.uvic.teknos.gt3.domain.models.Race;
import cat.uvic.teknos.gt3.domain.repositories.RaceRepository;
import cat.uvic.teknos.gt3.file.jpa.models.RaceImpl;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaRaceRepository implements RaceRepository {
    private final EntityManager entityManager;

    public JpaRaceRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Race model) {
        try{
            entityManager.getTransaction().begin();
            if(model.getId() <= 0){
                entityManager.persist(model);
            } else if(!entityManager.contains(model)){
                entityManager.merge(model);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public void delete(Race model) {
        try{
            entityManager.getTransaction().begin();
            var race = entityManager.find(cat.uvic.teknos.gt3.domain.models.Race.class, model.getId());
            entityManager.remove(race);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public Race get(Integer id) {
        return entityManager.find(cat.uvic.teknos.gt3.domain.models.Race.class, id);
    }

    @Override
    public Set<Race> getAll() {
        List<Race> raceList = entityManager.createQuery("SELECT u FROM RaceImpl u", Race.class).getResultList();
        return new HashSet<>(raceList);
    }
}
