package org.ikropachev.votingspringboot.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.ikropachev.votingspringboot.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
@Tag(name = "Vote Controller")
public interface VoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v FROM Vote v ORDER BY v.date DESC")
    List<Vote> findAll();

    @Query("SELECT v FROM Vote v WHERE v.userId=:userId AND v.date=:date")
    Vote getByUserIdAndDate(@Param("userId") int userId, @Param("date") LocalDate date);
}
