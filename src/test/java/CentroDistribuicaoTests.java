import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CentroDistribuicaoTests {

  @Test
  public void parametroInvalidoTest() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
    int[] resp = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
    assertEquals(-7, resp[0]);
  }

//  @Test
//  public void mudouSituacaoAoAbastecerTest() {}
//
//  @Test
//  public void mudouSituacaoAoEnviarPedidoTest() {}
}
