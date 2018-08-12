package com.sportsladder.dataaccess;

import com.sportsladder.domain.Challenge;
import com.sportsladder.domain.Player;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
    List<Challenge> findAll();
    List<Challenge> findByChallenger(Player challenger);
    List<Challenge> findByDefender(Player defender);
    List<Challenge> findByStatus(Integer status);
    List<Challenge> findByCompletionDateAfter(LocalDateTime startDate);



}
