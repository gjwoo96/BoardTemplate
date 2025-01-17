package backend.api.service;

import backend.api.entity.Board;
import backend.api.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    @Transactional
    public Optional<Board> findOneById (Long id){
        // 조회수 증가
        boardRepository.incrementViewCount(Math.toIntExact(id));
        return boardRepository.findByIdWithViewCount(Math.toIntExact(id));
    }

    public Board insertBoardData (Board data){
        return boardRepository.save(data);
    }

    public Board updateBoardData (Board updateData){
        Optional<Board> oldBoardData = boardRepository.findById(Long.valueOf(updateData.getId()));
        if(oldBoardData.isPresent()){
            Board editBoardData = oldBoardData.get();
            Field[] fields = updateData.getClass().getDeclaredFields();

            for(Field field: fields){
                field.setAccessible(true);
                try {
                    Object value = field.get(updateData);
                    if(value != null && !"".equals(value)){
                        Field targetField = editBoardData.getClass().getDeclaredField(field.getName());
                        targetField.setAccessible(true);
                        targetField.set(editBoardData,value);
                    }
                }catch (NoSuchFieldException | IllegalAccessException e){
                    throw new RuntimeException("field access failed"+field.getName(),e);
                }
            }
            return boardRepository.save(editBoardData);
        }
        return updateData;
    }

}
