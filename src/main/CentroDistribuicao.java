public class CentroDistribuicao {
    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    //Variavéis da minha cabeça.
    int gasolina;
    int aditivo;
    int alcool1;
    int alcool2;
    SITUACAO situacao;

    /**
     * O método construtor recebe as quantidades iniciais de gasolina nos tanques e ajusta a “situação” de acordo.
     */
    public CentroDistribuicao (int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        if(tAditivo > 0 && tAditivo <= MAX_ADITIVO) {
            aditivo = tAditivo;
        }

        if(tGasolina > 0 && tGasolina <= MAX_GASOLINA) {
            gasolina = tGasolina;
        }

        //Capacidade do primeiro Tanque é 1250L
        if(tAlcool1 > 0 && tAlcool1 <= (MAX_ALCOOL / 2)) {
            alcool1 = tAlcool1;
        }

        //Capacidade do segundo Tanque é 1250L
        if(tAlcool2 > 0 && tAlcool2 <= (MAX_ALCOOL / 2)) {
            alcool2 = tAlcool2;
        }

        defineSituacao();
    }

    /**
     * O método “defineSituacao” ajusta a situação de acordo com as regras.
     * Ele deve ser chamado tanto pelos métodos que sinalizam a chegada de componentes no centro de distribuição
     * quanto pelo método “encomendaCombustivel” que sinaliza o fornecimento de combustível para um posto.
     *
     * Quando todos os tanques estiverem acima de 50% da capacidade o sistema opera em modo NORMAL e as
     * encomendas são entregues normalmente para qualquer tipo de posto.
     *
     * Se o volume armazenado em QUALQUER UM dos tanques cair abaixo de 50% o sistema passa a operar em modo SOBRAVISO.
     * Neste modo o sistema só entrega 50% do que é solicitado pelos postos COMUNS e o total solicitado pelos ESTRATEGICOS.
     *
     * Caso o volume em QUALQUER UM dos tanques caia abaixo de 25%, então o sistema passa a operar em modo de EMERGËNCIA e
     * as encomendas dos postos COMUNS deixam de ser atendidas e as dos ESTRATÉGICOS são atendidas em 50%.
     */
    public void defineSituacao(){

        //SITUAÇÃO NORMAL
        //50% de Aditivo
        if(gettAditivo() >= 250){

            //50% de Alcool
            if(gettAlcool1() >= 625){
                if(gettAlcool2() >= 625){

                    //50% de Gasolina
                    if(gettGasolina() >= 5000) {
                        situacao = SITUACAO.NORMAL;
                    }
                }
            }
        }

        //SITUAÇÃO SOBRAVISO
        //Menos de 50% de Aditivo e acima de 25%
        if(gettAditivo() < 250 && gettAditivo() >= 125){
            situacao = SITUACAO.SOBRAVISO;
        }

        //Menos de 50% de Alcool e acima de 25%
        if(gettAlcool1() < 625 && gettAlcool1() >= 312){
            situacao = SITUACAO.SOBRAVISO;
        }

        if(gettAlcool2() < 625 && gettAlcool2() >= 312){
            situacao = SITUACAO.SOBRAVISO;
        }

        //Menos de 50% de Gasolina e acima de 25%
        if(gettGasolina() < 5000 && gettGasolina() >= 2500) {
            situacao = SITUACAO.SOBRAVISO;
        }

        //SITUAÇÃO EMERGENCIA
        //Menos de 25% de Aditivo
        if(gettAditivo() < 125){
            situacao = SITUACAO.EMERGENCIA;
        }

        //Menos de 25% de Alcool
        if(gettAlcool1() < 312){
            situacao = SITUACAO.EMERGENCIA;
        }
        if(gettAlcool2() < 312){
            situacao = SITUACAO.EMERGENCIA;
        }

        //Menos de 25% de Gasolina
        if(gettGasolina() < 2500) {
            situacao = SITUACAO.EMERGENCIA;
        }
    }

    public SITUACAO getSituacao(){
        return situacao;
    }

    public int gettGasolina(){
        return gasolina;
    }

    public int gettAditivo(){
        return aditivo;
    }

    public int gettAlcool1(){
        return alcool1;
    }

    public int gettAlcool2(){
        return alcool2;
    }

    /**
     * Os métodos “recebeAditivo”, “recebeGasolina” e “recebeAlcool” são usados quando o centro de distribuição
     * recebe carga dos componentes. Todos recebem por parâmetro a quantidade do componente (aditivo, gasolina ou álcool)
     * recebida e retornam à quantidade que PUDERAM armazenar devido a limitação do tamanho dos tanques e de quanto
     * ainda tinham armazenado.
     *
     * Devem retornar “-1” caso a quantidade recebida por parâmetro seja inválida.
     *
     * FALTA FAZER A VERIFICAÇÃO PRA DOUBLE
     */
    public int recebeAditivo(int qtdade) {

        int result;

        if(qtdade <= 0){
            result = -1;
        } else {
            aditivo = aditivo + qtdade;

            //Verificação
            if(gettAditivo() > MAX_ADITIVO){

                int resto = gettAditivo() - MAX_ADITIVO;
                aditivo = aditivo - resto;

                result = qtdade - resto;
            } else {
                result = qtdade;
            }
        }

        defineSituacao();
        return result;
    }

    public int recebeGasolina(int qtdade) {

        int result;

        if(qtdade <=0){
            result = -1;
        } else {
            gasolina = gasolina + qtdade;

            //Verificação
            if(gettGasolina() > MAX_GASOLINA){

                int resto = gettGasolina() - MAX_GASOLINA;
                gasolina = gasolina - resto;

                result = qtdade - resto;
            } else {
                result = qtdade;
            }
        }

        defineSituacao();
        return result;
    }

    /**
     * Os tanques de álcool devem ter sempre a mesma quantidade de combustível de maneira a manter o equilíbrio
     * da estrutura devido a forma como foram construídos. Isso vale tanto para o armazenamento como para a retirada.
     */
    public int recebeAlcool(int qtdade) {

        int result;

        if(qtdade <= 0){
            result = -1;
        } else {
            int divisaoQtd = qtdade / 2;

            alcool1 = alcool1 + divisaoQtd;
            alcool2 = alcool2 + divisaoQtd;

            //Verificação
            if (gettAlcool1() > (MAX_ALCOOL/2) && gettAlcool2() > (MAX_ALCOOL/2)) {
                int resto1 = gettAlcool1() - (MAX_ALCOOL/2);
                int resto2 = gettAlcool2() - (MAX_ALCOOL/2);

                alcool1 = alcool1 - resto1;
                alcool2 = alcool2 - resto2;

                result = qtdade - (resto1 + resto2);
            } else {
                result = qtdade;
            }
        }

        defineSituacao();
        return result;
    }

    /**
     * O método “encomendaCombustivel” é usado quando o centro de distribuição recebe o pedido de um posto.
     * Este método recebe por parâmetro a quantidade solicitada pelo posto e o tipo do posto.
     * Se o pedido puder ser atendido, o método retorna um arranjo com a quantidade de combustível remanescente
     * em cada tanque, DEPOIS do pedido atendido.
     *
     * As quantidades devem ser retornadas pela ordem: aditivo, gasolina, álcool T1 e álcool T2.
     * A primeira posição do arranjo é usada também para indicar códigos de erro.
     * No caso de ser recebido um valor inválido por parâmetro deve-se retornar “-7” na primeira posição do arranjo,
     * se o pedido não puder ser atendido em função da “situação” retorna-se “-14” e,
     * caso não haja combustível suficiente para completar a mistura, retorna-se “-21”.
     *
     * Na hora de fazer os cálculos multiplique os valores por 100.
     * Depois de feitos os cálculos dividam por 100 novamente e despreze a parte fracionária;
     *
     * Se houver falta de qualquer um dos componentes na quantidade adequada a encomenda não pode ser entregue.
     *
     * Quando todos os tanques estiverem acima de 50% da capacidade o sistema opera em modo NORMAL e as
     * encomendas são entregues normalmente para qualquer tipo de posto.
     *
     * Se o volume armazenado em QUALQUER UM dos tanques cair abaixo de 50% o sistema passa a operar em modo SOBRAVISO.
     * Neste modo o sistema só entrega 50% do que é solicitado pelos postos COMUNS e o total solicitado pelos ESTRATEGICOS.
     *
     * Caso o volume em QUALQUER UM dos tanques caia abaixo de 25%, então o sistema passa a operar em modo de EMERGËNCIA e
     * as encomendas dos postos COMUNS deixam de ser atendidas e as dos ESTRATÉGICOS são atendidas em 50%.
     *
     * a gasolina vendida nos postos é resultado de uma mistura de 3 componentes:
     * 5% de aditivo, 25% de álcool e 70% de gasolina pura.
     */
    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {

        SITUACAO atual = getSituacao();
        int[] valores = new int[3];

        if (qtdade <= 0){
            valores[0] = -7;
            return valores;
        }

        double targetAditivo = qtdade * 0.05;
        double targetAlcool = qtdade * 0.25;
        double targetGasolina = qtdade * 0.7;

        //Separação Extra pros tanque de Alcool
        double a1 = targetAlcool / 2;
        double a2 = targetAlcool / 2;


        if (atual == SITUACAO.NORMAL) {
            if(gettAditivo() < targetAditivo){
                valores[0] = -21;
                return valores;
            }

            if(gettAlcool1() < a1){
                valores[0] = -21;
                return valores;
            }

            if(gettAlcool2() < a2){
                valores[0] = -21;
                return valores;
            }

            if(gettGasolina() < targetGasolina){
                valores[0] = -21;
                return valores;
            }

            aditivo = (int) (aditivo - targetAditivo);
            alcool1 = (int) (alcool1 - a1);
            alcool2 = (int) (alcool2 - a2);
            gasolina = (int) (gasolina - targetGasolina);

            defineSituacao();

            //aditivo, gasolina, álcool T1 e álcool T2.
            valores[0] = gettAditivo();
            valores[1] = gettGasolina();
            valores[2] = gettAlcool1();
            valores[3] = gettAlcool2();

            //return valores;
        }

        if (atual == SITUACAO.SOBRAVISO && tipoPosto == TIPOPOSTO.COMUM) {
            if(gettAditivo() < targetAditivo){
                valores[0] = -21;
                return valores;
            }

            if(gettAlcool1() < a1){
                valores[0] = -21;
                return valores;
            }

            if(gettAlcool2() < a2){
                valores[0] = -21;
                return valores;
            }

            if(gettGasolina() < targetGasolina){
                valores[0] = -21;
                return valores;
            }

            aditivo = (int) ((aditivo - targetAditivo) / 2);
            alcool1 = (int) ((alcool1 - a1) / 2);
            alcool2 = (int) ((alcool2 - a2) / 2);
            gasolina = (int) ((gasolina - targetGasolina) / 2);

            defineSituacao();

            valores[0] = gettAditivo();
            valores[1] = gettGasolina();
            valores[2] = gettAlcool1();
            valores[3] = gettAlcool2();

            //return valores;
        }

        if (atual == SITUACAO.SOBRAVISO && tipoPosto == TIPOPOSTO.ESTRATEGICO) {
            if(gettAditivo() < targetAditivo){
                valores[0] = -21;
                return valores;
            }

            if(gettAlcool1() < a1){
                valores[0] = -21;
                return valores;
            }

            if(gettAlcool2() < a2){
                valores[0] = -21;
                return valores;
            }

            if(gettGasolina() < targetGasolina){
                valores[0] = -21;
                return valores;
            }

            aditivo = (int) (aditivo - targetAditivo);
            alcool1 = (int) (alcool1 - a1);
            alcool2 = (int) (alcool2 - a2);
            gasolina = (int) (gasolina - targetGasolina);

            defineSituacao();

            valores[0] = gettAditivo();
            valores[1] = gettGasolina();
            valores[2] = gettAlcool1();
            valores[3] = gettAlcool2();

            //return valores;
        }

        if (atual == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.COMUM) {
            valores[0] = -14;
            return valores;
        }

        if (atual == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.ESTRATEGICO) {
            if(gettAditivo() < targetAditivo){
                valores[0] = -21;
                return valores;
            }

            if(gettAlcool1() < a1){
                valores[0] = -21;
                return valores;
            }

            if(gettAlcool2() < a2){
                valores[0] = -21;
                return valores;
            }

            if(gettGasolina() < targetGasolina){
                valores[0] = -21;
                return valores;
            }

            aditivo = (int) ((aditivo - targetAditivo) / 2);
            alcool1 = (int) ((alcool1 - a1) / 2);
            alcool2 = (int) ((alcool2 - a2) / 2);
            gasolina = (int) ((gasolina - targetGasolina) / 2);

            defineSituacao();

            valores[0] = gettAditivo();
            valores[1] = gettGasolina();
            valores[2] = gettAlcool1();
            valores[3] = gettAlcool2();

            //return valores;
        }

        return valores;
    }
    //FIM
}