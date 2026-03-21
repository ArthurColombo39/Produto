package br.com.projeto.produto.service;

import br.com.projeto.produto.dto.ProdutoDto;
import br.com.projeto.produto.exception.ResourceNotFoundException;
import br.com.projeto.produto.mapper.CustomModelMapper;
import br.com.projeto.produto.model.ProdutoModel;
import br.com.projeto.produto.repository.CategoriaRepository;
import br.com.projeto.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoDto findById(long id){
        var found = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );
        return CustomModelMapper.parseObject(found, ProdutoDto.class);
    }

    public List<ProdutoDto> findAll(){
        var produtos = produtoRepository.findAll();
        return CustomModelMapper.parseObjectList(produtos, ProdutoDto.class);
    }

    public ProdutoDto create(ProdutoDto produtoDto){
        ProdutoModel produtoModel = CustomModelMapper.parseObject(produtoDto, ProdutoModel.class);

        long categoriaId = produtoDto.getCategoria().getId();
        var categoria = categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada!")
        );
        produtoModel.setCategoria(categoria);

        return CustomModelMapper.parseObject(produtoRepository.save(produtoModel), ProdutoDto.class);
    }

    public ProdutoDto update(ProdutoDto produtoDto){
        var found = produtoRepository.findById(produtoDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );
        found.setName(produtoDto.getName());

        long categoriaId = produtoDto.getCategoria().getId();
        var categoria = categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada!")
        );
        found.setCategoria(categoria);

        return CustomModelMapper.parseObject(produtoRepository.save(found), ProdutoDto.class);
    }

    public void delete(long id){
        var found = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );
        produtoRepository.delete(found);
    }

}