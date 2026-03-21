package br.com.projeto.produto.controller;

import br.com.projeto.produto.dto.ProdutoDto;
import br.com.projeto.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDto> create(@RequestBody ProdutoDto produtoDto){
        var created = produtoService.create(produtoDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable("id") long id){
        var found = produtoService.findById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll(){
        var produtos = produtoService.findAll();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProdutoDto> update(@RequestBody ProdutoDto produtoDto){
        var updated = produtoService.update(produtoDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        produtoService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}