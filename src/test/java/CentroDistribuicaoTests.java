import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CentroDistribuicaoTests {


  @ParameterizedTest
  @CsvSource({
          "400, 9000, 1000, 1000, -50, -7",
          "399, 9000, 1250, 1250, 8001, -21",
          "401, 5000, 1250, 1250, 8000, -21",
          "401, 9000, 700, 700, 8000, -21"
  })
  public void encomendaCombustivelTest(int tAditivo, int tGasolina, int tAlcool1, int tAlcoo2, int qtidade, int esperado) {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(tAditivo, tGasolina, tAlcool1, tAlcoo2);
    int[] resp = centroDistribuicao.encomendaCombustivel(qtidade, CentroDistribuicao.TIPOPOSTO.COMUM);
    assertEquals(esperado, resp[0]);
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

  @ParameterizedTest
  @CsvSource({
          "0, 0, 0, 1",
          "-1, 0, 0, 0",
          "0, -1, 0, 0",
          "0, 0, -1, -1"

  })
  public void construtorTest(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
    assertThrows(IllegalArgumentException.class, () -> {
      CentroDistribuicao centroDistribuicao = new CentroDistribuicao(tAditivo, tGasolina, tAlcool1, tAlcool2);
    });
  }
}