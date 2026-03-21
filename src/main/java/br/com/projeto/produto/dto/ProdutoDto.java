package br.com.projeto.produto.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProdutoDto {

    private long id;
    private String name;
    private CategoryDto category;

}
