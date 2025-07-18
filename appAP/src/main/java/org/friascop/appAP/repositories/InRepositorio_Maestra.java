package org.friascop.appAP.repositories;

import org.friascop.appAP.entities.Maestra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InRepositorio_Maestra extends CrudRepository<Maestra,Long> {


}
