import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostoEstrategicoTests {

  @Test
  public void PostoEstrategicoSituacaoNormal() {
    int aditivo = 400;
    int gasolina = 9000;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.NORMAL, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);
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
  public void PostoEstrategicoSituacaoSobreavisoAditivo() {
    int aditivo = 200;
    int gasolina = 9000;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.SOBRAVISO, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);
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
  public void PostoEstrategicoSituacaoSobreavisoAlcool() {
    int aditivo = 400;
    int gasolina = 9000;
    int t1Alcool = 600;
    int t2Alcool = 600;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.SOBRAVISO, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);
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
  public void PostoEstrategicoSituacaoSobreavisoGasolina() {
    int aditivo = 400;
    int gasolina = 4500;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.SOBRAVISO, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);
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
  public void PostoEstrategicoSituacaoEmergenciaAditivo() {
    int aditivo = 100;
    int gasolina = 9000;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.EMERGENCIA, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);
    // Aditivo
    assertEquals(aditivo - (6 / 2), resp[0]);
    // Gasolina
    assertEquals(gasolina - (84 / 2), resp[1]);
    // Alcool 1
    assertEquals(t1Alcool - (15 / 2), resp[2]);
    // Alcool 2
    assertEquals(t2Alcool - (15 / 2), resp[3]);
  }

  @Test
  public void PostoEstrategicoSituacaoEmergenciaAlcool() {
    int aditivo = 400;
    int gasolina = 9000;
    int t1Alcool = 200;
    int t2Alcool = 200;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.EMERGENCIA, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);
    // Aditivo
    assertEquals(aditivo - (6 / 2), resp[0]);
    // Gasolina
    assertEquals(gasolina - (84 / 2), resp[1]);
    // Alcool 1
    assertEquals(t1Alcool - (15 / 2), resp[2]);
    // Alcool 2
    assertEquals(t2Alcool - (15 / 2), resp[3]);
  }

  @Test
  public void PostoEstrategicoSituacaoEmergenciaGasolina() {
    int aditivo = 400;
    int gasolina = 300;
    int t1Alcool = 1000;
    int t2Alcool = 1000;
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, t1Alcool, t2Alcool);
    assertEquals(CentroDistribuicao.SITUACAO.EMERGENCIA, centroDistribuicao.getSituacao());

    int[] resp = centroDistribuicao.encomendaCombustivel(120, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);
    // Aditivo
    assertEquals(aditivo - (6 / 2), resp[0]);
    // Gasolina
    assertEquals(gasolina - (84 / 2), resp[1]);
    // Alcool 1
    assertEquals(t1Alcool - (15 / 2), resp[2]);
    // Alcool 2
    assertEquals(t2Alcool - (15 / 2), resp[3]);
  }
}
