package com.sportsladder.dataaccess;

import com.sportsladder.domain.Player;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAll();
    List<Player> findByRank(Integer rank);
    Player findPlayerById(Long id);
    List<Player> findAllByRankGreaterThanAndRankLessThan(Integer rank1, Integer rank2);


}
