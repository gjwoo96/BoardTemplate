package backend.api.controller;

import backend.api.entity.Board;
import backend.api.service.BoardService;
import backend.api.vo.BoardVo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:13010")
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

    @GetMapping(value = "/getOneData/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> getOneData(@PathVariable Integer id){
        Board data = boardService.findOneById(Long.valueOf(id)).get();
        return ResponseEntity.ok(data);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Board>> insertData(@RequestBody Board request){
        boardService.insertBoardData(request);
        List<Board> lists = boardService.findAll();
        return ResponseEntity.ok(lists);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Board>> updateData(Board request){
        boardService.updateBoardData(request);
        List<Board> lists = boardService.findAll();
        return ResponseEntity.ok(lists);
    }
}
