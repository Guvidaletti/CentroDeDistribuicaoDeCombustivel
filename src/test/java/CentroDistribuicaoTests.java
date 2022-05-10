import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

  @ParameterizedTest
  @CsvSource({
          "0, 0, 0, 0, -1, 0",
          "0, 0, 0, 0, 100, 100",
          "450, 0, 0, 0, 50, 51",
  })
  public   void recebeAditivoTest(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2, int esperado, int qtidade) {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(tAditivo, tGasolina, tAlcool1, tAlcool2);
    assertEquals(esperado, centroDistribuicao.recebeAditivo(qtidade));
  }

  @ParameterizedTest
  @CsvSource({
          "0, 0, 0, 0, -1, 0",
          "0, 0, 0, 0, 100, 100",
          "0, 8000, 0, 0, 2000, 2001",
  })
  public   void recebeGasolinaTest(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2, int esperado, int qtidade) {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(tAditivo, tGasolina, tAlcool1, tAlcool2);
    assertEquals(esperado, centroDistribuicao.recebeGasolina(qtidade));
  }

  @ParameterizedTest
  @CsvSource({
          "0, 0, 0, 0, -1, 0",
          "0, 0, 0, 0, 100, 100",
          "0, 0, 1200, 1200, 100, 102",
  })
  public void recebeAlcoolTest(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2, int esperado, int qtidade) {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(tAditivo, tGasolina, tAlcool1, tAlcool2);
    assertEquals(esperado, centroDistribuicao.recebeAlcool(qtidade));
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