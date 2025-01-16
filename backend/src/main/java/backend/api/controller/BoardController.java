package backend.api.controller;

import backend.api.entity.Board;
import backend.api.service.BoardService;
import backend.api.vo.BoardVo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3010")
@RequestMapping(value = "/api")
@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/getAllData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Board>> getData(){
        List<Board> lists = boardService.findAll();
        return ResponseEntity.ok(lists);
    }

    @GetMapping(value = "/getOneData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> getOneData(Board request){
        Board data = boardService.findOneById(Long.valueOf(request.getId())).get();
        return ResponseEntity.ok(data);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> insertData(Board request){
        Board data = boardService.insertBoardData(request);
        return ResponseEntity.ok(data);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> updateData(Board request){
        Board data = boardService.updateBoardData(request);
        return ResponseEntity.ok(data);
    }
}
