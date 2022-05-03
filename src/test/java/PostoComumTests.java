import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PostoComumTests {

  @Test
  public void PostoComumSituacaoNormal() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
    assertEquals(CentroDistribuicao.SITUACAO.NORMAL, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(400 - 6, resp[0]);
    // Gasolina
    assertEquals(9000 - 84, resp[1]);
    // Alcool 1
    assertEquals(1000 - 15, resp[2]);
    // Alcool 2
    assertEquals(1000 - 15, resp[3]);
  }

  @Test
  public void PostoComumSituacaoSobreavisoAditivo() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(240, 9000, 1000, 1000);
    assertEquals(CentroDistribuicao.SITUACAO.SOBRAVISO, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(240 - (6 / 2), resp[0]);
    // Gasolina
    assertEquals(9000 - (84 / 2), resp[1]);
    // Alcool 1
    assertEquals(1000 - (15 / 2), resp[2]);
    // Alcool 2
    assertEquals(1000 - (15 / 2), resp[3]);
  }

  @Test
  public void PostoComumSituacaoSobreavisoAlcool() {
  }

  @Test
  public void PostoComumSituacaoSobreavisoGasolina() {
  }

  @Test
  public void PostoComumSituacaoEmergenciaAditivo() {
  }

  @Test
  public void PostoComumSituacaoEmergenciaAlcool() {
  }

  @Test
  public void PostoComumSituacaoEmergenciaGasolina() {
  }
}
