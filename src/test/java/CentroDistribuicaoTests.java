import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

  @Test
  public void recebeAditivoErro() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 0, 0);
    assertEquals(-1, centroDistribuicao.recebeAditivo(0));
  }

  @Test
  public void recebeAlcoolErro() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 0, 0);
    assertEquals(-1, centroDistribuicao.recebeAlcool(0));
  }

  @Test
  public void recebeGasolinaErro() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 0, 0);
    assertEquals(-1, centroDistribuicao.recebeGasolina(0));
  }

  @Test
  public void recebeAditivoNormal() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 0, 0);
    assertEquals(100, centroDistribuicao.recebeAditivo(100));
  }

  @Test
  public void recebeAlcoolNormal() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 0, 0);
    assertEquals(100, centroDistribuicao.recebeAlcool(100));
  }

  @Test
  public void recebeGasolinaNormal() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 0, 0);
    assertEquals(100, centroDistribuicao.recebeGasolina(100));
  }

  @Test
  public void recebeAditivoLimite() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(450, 0, 0, 0);
    assertEquals(50, centroDistribuicao.recebeAditivo(51));
  }

  @Test
  public void recebeAlcoolLimite() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 1200, 1200);
    assertEquals(100, centroDistribuicao.recebeAlcool(102));
  }

  @Test
  public void recebeGasolinaLimite() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 8000, 0, 0);
    assertEquals(2000, centroDistribuicao.recebeGasolina(2001));
  }

  @Test
  public void alcoolDiferenteTest() {
    assertThrows(IllegalArgumentException.class, () -> {
      CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 0, 1);
    });
  }

  @Test
  public void aditivoNegativoTest() {
    assertThrows(IllegalArgumentException.class, () -> {
      CentroDistribuicao centroDistribuicao = new CentroDistribuicao(-1, 0, 0, 0);
    });
  }

  @Test
  public void gasolinaNegativaTest() {
    assertThrows(IllegalArgumentException.class, () -> {
      CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, -1, 0, 0);
    });
  }

  @Test
  public void alcoolNegativoTest() {
    assertThrows(IllegalArgumentException.class, () -> {
      CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, -1, -1);
    });
  }
}
