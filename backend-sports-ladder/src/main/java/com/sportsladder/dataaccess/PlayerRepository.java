package com.sportsladder.dataaccess;

import com.sportsladder.domain.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAll();
    List<Player> findByRank(Integer rank);


}
