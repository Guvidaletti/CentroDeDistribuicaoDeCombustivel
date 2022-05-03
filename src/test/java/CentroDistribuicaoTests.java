import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CentroDistribuicaoTests {

  @Test
  public void parametroInvalidoTest() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
    int[] resp = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
    assertEquals(-7, resp[0]);
  }

  @Test
  public void faltouAditivoParaFazerAMistura() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(399, 9000, 1250, 1250);
    int[] resp = centroDistribuicao.encomendaCombustivel(8001, CentroDistribuicao.TIPOPOSTO.COMUM);
    assertEquals(-21, resp[0]);
  }

  @Test
  public void faltouGasolinaParaFazerAMistura() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(401, 5000, 1250, 1250);
    int[] resp = centroDistribuicao.encomendaCombustivel(8000, CentroDistribuicao.TIPOPOSTO.COMUM);
    assertEquals(-21, resp[0]);
  }

  @Test
  public void faltouAlcoolParaFazerAMistura() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(401, 9000, 700, 700);
    int[] resp = centroDistribuicao.encomendaCombustivel(8000, CentroDistribuicao.TIPOPOSTO.COMUM);
    assertEquals(-21, resp[0]);
  }
}
