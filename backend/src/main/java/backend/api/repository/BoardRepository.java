package backend.api.repository;

import backend.api.entity.Board;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    // 단건 데이터 조회 + 조회수 증가
    @Modifying
    @Query("UPDATE Board b SET b.viewCount = b.viewCount + 1 WHERE b.id = :id")
    void incrementViewCount(@Param("id") Integer id);

    // 단건 데이터 조회
    @Query("SELECT b FROM Board b WHERE b.id = :id")
    Optional<Board> findByIdWithViewCount(@Param("id") Integer id);
}
