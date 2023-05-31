package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {

  @Autowired
  private ProdutoRepositorio pr;

  @Autowired
  private RespostaModelo rm;

  public Iterable<ProdutoModelo> listar() {
    return pr.findAll();
  }

  // metodo para cadastrar ou alterar produtos
  public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String operacao) {
    if (pm.getNome().equals("")) {
      rm.setMensagem("Nome não pode ser vazio");
      return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
    } else if (pm.getMarca().equals("")) {
      rm.setMensagem("Marca não pode ser vazia");
      return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
    } else {
      if (operacao.equals("cadastrar")) {
        return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
      } else {
        return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);
      }
    }

  }

}
