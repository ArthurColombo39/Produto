package br.com.projeto.produto.service;

import br.com.projeto.produto.dto.CategoriaDto;
import br.com.projeto.produto.exception.ResourceNotFoundException;
import br.com.projeto.produto.mapper.CustomModelMapper;
import br.com.projeto.produto.model.CategoriaModel;
import br.com.projeto.produto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaDto findById(long id){
        var found = categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada!")
        );
        return CustomModelMapper.parseObject(found, CategoriaDto.class);
    }

    public List<CategoriaDto> findAll(){
        var categorias = categoriaRepository.findAll();
        return CustomModelMapper.parseObjectList(categorias, CategoriaDto.class);
    }

    public CategoriaDto create(CategoriaDto categoriaDto){
        CategoriaModel categoriaModel = CustomModelMapper.parseObject(categoriaDto, CategoriaModel.class);
        return CustomModelMapper.parseObject(categoriaRepository.save(categoriaModel), CategoriaDto.class);
    }

    public CategoriaDto update(CategoriaDto categoriaDto){
        var found = categoriaRepository.findById(categoriaDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada!")
        );
        found.setName(categoriaDto.getName());
        return CustomModelMapper.parseObject(categoriaRepository.save(found), CategoriaDto.class);
    }

    public void delete(long id){
        var found = categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada!")
        );
        categoriaRepository.delete(found);
    }

}