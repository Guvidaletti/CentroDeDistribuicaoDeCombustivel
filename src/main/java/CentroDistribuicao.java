public class CentroDistribuicao {

  public enum SITUACAO {
    NORMAL, SOBRAVISO, EMERGENCIA
  }

  public enum TIPOPOSTO {
    COMUM, ESTRATEGICO
  }

  public static final int MAX_ADITIVO = 500;
  public static final int MAX_ALCOOL = 2500;
  public static final int MAX_GASOLINA = 10000;

  private int tAditivo;
  private int tGasolina;
  private int tAlcool1;
  private int tAlcool2;
  private SITUACAO situacaoAtual;
  private TIPOPOSTO tipoPosto;

  public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) throws IllegalArgumentException {
    if (tAlcool1 != tAlcool2) {
      throw new IllegalArgumentException("Alcool não pode ser diferente!");
    }
    if (tAditivo < 0 || tGasolina < 0 || tAlcool1 < 0) {
      throw new IllegalArgumentException("Não pode ser um número negativo!");
    }
    this.tAditivo = tAditivo;
    this.tGasolina = tGasolina;
    this.tAlcool1 = tAlcool1;
    this.tAlcool2 = tAlcool2;
    defineSituacao();
  }

  // ajusta situação de acordo com as regras. Deve ser chamado pelo método
  // encomendaCombustível.
  public void defineSituacao() {
    if (getAditivo() < (MAX_ADITIVO / 4) || getGasolina() < (MAX_GASOLINA / 4) || (getAlcool1() + getAlcool2()) < (MAX_ALCOOL / 4)) {
      situacaoAtual = SITUACAO.EMERGENCIA;
    } else if (getAditivo() < (MAX_ADITIVO / 2) || getGasolina() < (MAX_GASOLINA / 2) || (getAlcool1() + getAlcool2()) < (MAX_ALCOOL / 2)) {
      situacaoAtual = SITUACAO.SOBRAVISO;
    } else {
      situacaoAtual = SITUACAO.NORMAL;
    }
  }

  public SITUACAO getSituacao() {
    return this.situacaoAtual;
  }

  public int getGasolina() {
    return this.tGasolina;
  }

  public int getAditivo() {
    return this.tAditivo;
  }

  public int getAlcool1() {
    return this.tAlcool1;
  }

  public int getAlcool2() {
    return this.tAlcool2;
  }

  // metodos recebe[...] devem retornar o quanto armazenaram de acordo com o
  // tamanho do tanque. Devem retornar -1 caso o parametro seja invalido.
  private boolean validaGasolina(int novaGasolina) {
    if (novaGasolina > MAX_GASOLINA) return false;
    return true;
  }

  private boolean validaÁlcool(int novoAlcool) {
    if (novoAlcool > MAX_ALCOOL) return false;
    return true;
  }

  private boolean validaAditivo(int novoAditivo) {
    if (novoAditivo > MAX_ADITIVO) return false;
    return true;
  }

  public int recebeAditivo(int qtdade) {
    if (validaAditivo(this.tAditivo + qtdade)) {
      this.tAditivo += qtdade / 2;
      defineSituacao();
      return getAditivo();
    }
    return -1;
  }

  public int recebeGasolina(int qtdade) {
    if (validaGasolina(this.tAditivo + qtdade)) {
      this.tGasolina += qtdade;
      defineSituacao();
      return getAditivo();
    }
    if (qtdade > 0) {
      return -21;
    }

    return -1;
  }

  public int recebeAlcool(int qtdade) {
    if (validaÁlcool(this.tAditivo + qtdade)) {
      this.tAlcool1 += qtdade / 2;
      this.tAlcool2 += qtdade / 2;
      defineSituacao();
      return getAditivo();
    }
    return -1;
  }

  public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
    int[] tanqueAtual = new int[4];
    boolean validacao = validaAditivo(getAditivo() - (qtdade / 20)) && validaGasolina(getGasolina() - (qtdade / 10 * 7)) && validaÁlcool((getAlcool1() + getAlcool2()) - (qtdade / 8));
    switch (getSituacao()) {
      case NORMAL:
        if (validacao) {
          tAditivo -= (qtdade / 20);
          tGasolina -= ((qtdade / 10) * 7);
          tAlcool1 -= (qtdade / 8);
          tAlcool2 -= (qtdade / 8);

          tanqueAtual[0] = getAditivo();
          tanqueAtual[1] = getGasolina();
          tanqueAtual[2] = getAlcool1();
          tanqueAtual[3] = getAlcool2();
        } else {
          tanqueAtual[0] = -7;
        }

        break;
      case SOBRAVISO:
        if (tipoPosto == TIPOPOSTO.COMUM) {

          if (validaAditivo(getAditivo() - (qtdade / 40)) && validaGasolina(getGasolina() - (qtdade / 20 * 7)) && validaÁlcool((getAlcool1() + getAlcool2()) - (qtdade / 8))) {
            tAditivo -= (qtdade / 40);
            tGasolina -= ((qtdade / 20) * 7);
            tAlcool1 -= (qtdade / 16);
            tAlcool2 -= (qtdade / 16);

            tanqueAtual[0] = getAditivo();
            tanqueAtual[1] = getGasolina();
            tanqueAtual[2] = getAlcool1();
            tanqueAtual[3] = getAlcool2();
          } else {
            tanqueAtual[0] = -7;
          }

        } else {
          if (validaAditivo(getAditivo() - (qtdade / 20)) && validaGasolina(getGasolina() - (qtdade / 10 * 7)) && validaÁlcool((getAlcool1() + getAlcool2()) - (qtdade / 8))) {
            tAditivo -= (qtdade / 20);
            tGasolina -= ((qtdade / 10) * 7);
            tAlcool1 -= (qtdade / 8);
            tAlcool2 -= (qtdade / 8);

            tanqueAtual[0] = getAditivo();
            tanqueAtual[1] = getGasolina();
            tanqueAtual[2] = getAlcool1();
            tanqueAtual[3] = getAlcool2();
          } else {
            tanqueAtual[0] = -7;
          }

        }
        break;
      case EMERGENCIA:
        if (tipoPosto == TIPOPOSTO.COMUM) {
          tanqueAtual[0] = -14;
        } else {
          if (validaAditivo(getAditivo() - (qtdade / 40)) && validaGasolina(getGasolina() - (qtdade / 20 * 7)) && validaÁlcool((getAlcool1() + getAlcool2()) - (qtdade / 8))) {
            tAditivo -= (qtdade / 40);
            tGasolina -= ((qtdade / 20) * 7);
            tAlcool1 -= (qtdade / 16);
            tAlcool2 -= (qtdade / 16);
            tanqueAtual[0] = getAditivo();
            tanqueAtual[1] = getGasolina();
            tanqueAtual[2] = getAlcool1();
            tanqueAtual[3] = getAlcool2();
          } else {
            tanqueAtual[0] = -7;
          }
        }
        break;
    } return tanqueAtual;
  }

  @Override
  public String toString() {
    return "CentroDistribuicao [tAditivo=" + tAditivo + ", tAlcool1=" + tAlcool1 + ", tAlcool2=" + tAlcool2 + ", tGasolina=" + tGasolina + "]";
  }

  public static void main(String[] args) {
    CentroDistribuicao tanqueDistribuicao = new CentroDistribuicao(500, 10000, 1250, 1250);
    tanqueDistribuicao.encomendaCombustivel(300, TIPOPOSTO.ESTRATEGICO);

  }

}