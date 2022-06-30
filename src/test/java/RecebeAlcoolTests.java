import org.junit.jupiter.api.Test;

public class RecebeAlcoolTests {

  @Test
  public void parametroInvalidoTest() {
    CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
    int qtde = 0;
    centroDistribuicao.recebeAlcool(qtde);

  }
}
