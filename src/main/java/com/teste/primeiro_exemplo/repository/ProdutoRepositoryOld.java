package com.teste.primeiro_exemplo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.primeiro_exemplo.model.Produto;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepositoryOld {

    private List<Produto> produtos = new ArrayList<>();

    private Integer ultimoId = 0;

    /**
     * Método para retornar uma lista de produtos.
     * @return Lista de produtos.
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Método que retorna o produto encontrado pelo seu id.
     * @param id do produto que será localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<Produto> obeterPorId(Integer id) {
        return produtos.stream().filter(produto -> produto.getId() == id).findFirst();
    }

    /**
     * Método para adicionar produto na lista.
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Método para deletar o produto por id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Método para atualizar o produto na lista.
     * @param produto que será atualziado.
     * @param id do produto.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Produto produto, Integer id) {
        Optional<Produto> produtoEncontrado = obeterPorId(id);
        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não pode ser atualizado pois não existe.");
        }
        deletar(id);
        produtos.add(produto);
        return produto;
    }
}
