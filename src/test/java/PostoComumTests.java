

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostoComumTests {

  @Test
  public void PostoComumSituacaoNormal() {
    int aditivo = 400;
    int gasolina = 9000;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.NORMAL, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(aditivo - 6, resp[0]);
    // Gasolina
    assertEquals(gasolina - 84, resp[1]);
    // Alcool 1
    assertEquals(t1Alcool - 15, resp[2]);
    // Alcool 2
    assertEquals(t2Alcool - 15, resp[3]);
  }

  @Test
  public void PostoComumSituacaoSobreavisoAditivo() {
    int aditivo = 240;
    int gasolina = 9000;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.SOBRAVISO, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(aditivo - (6 / 2), resp[0]);
    // Gasolina
    assertEquals(gasolina - (84 / 2), resp[1]);
    // Alcool 1
    assertEquals(t1Alcool - (30 / 2 / 2), resp[2]);
    // Alcool 2
    assertEquals(t2Alcool - (30 / 2 / 2), resp[3]);
  }

  @Test
  public void PostoComumSituacaoSobreavisoAlcool() {
    int aditivo = 400;
    int gasolina = 9000;
    int t1Alcool = 600;
    int t2Alcool = 600;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.SOBRAVISO, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(aditivo - (6 / 2), resp[0]);
    // Gasolina
    assertEquals(gasolina - (84 / 2), resp[1]);
    // Alcool 1
    assertEquals(t1Alcool - (30 / 2 / 2), resp[2]);
    // Alcool 2
    assertEquals(t2Alcool - (30 / 2 / 2), resp[3]);
  }

  @Test
  public void PostoComumSituacaoSobreavisoGasolina() {
    int aditivo = 400;
    int gasolina = 4500;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.SOBRAVISO, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(aditivo - (6 / 2), resp[0]);
    // Gasolina
    assertEquals(gasolina - (84 / 2), resp[1]);
    // Alcool 1
    assertEquals(t1Alcool - (30 / 2 / 2), resp[2]);
    // Alcool 2
    assertEquals(t2Alcool - (30 / 2 / 2), resp[3]);
  }

  @Test
  public void PostoComumSituacaoEmergenciaAditivo() {
    int aditivo = 100;
    int gasolina = 9000;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.EMERGENCIA, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(-14, resp[0]);
  }

  @Test
  public void PostoComumSituacaoEmergenciaAlcool() {
    int aditivo = 400;
    int gasolina = 9000;
    int t1Alcool = 100;
    int t2Alcool = 100;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.EMERGENCIA, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(-14, resp[0]);
  }

  @Test
  public void PostoComumSituacaoEmergenciaGasolina() {
    int aditivo = 400;
    int gasolina = 2000;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.EMERGENCIA, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.COMUM);
    // Aditivo
    assertEquals(-14, resp[0]);
  }
}
