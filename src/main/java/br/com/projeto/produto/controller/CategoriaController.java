package br.com.projeto.produto.controller;

import br.com.projeto.produto.dto.CategoriaDto;
import br.com.projeto.produto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDto> create(@RequestBody CategoriaDto categoriaDto){
        var created = categoriaService.create(categoriaDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable("id") long id){
        var found = categoriaService.findById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll(){
        var categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoriaDto> update(@RequestBody CategoriaDto categoriaDto){
        var updated = categoriaService.update(categoriaDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        categoriaService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}