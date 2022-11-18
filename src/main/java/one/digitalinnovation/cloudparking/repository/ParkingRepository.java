package one.digitalinnovation.cloudparking.repository;

import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,String> {

}
