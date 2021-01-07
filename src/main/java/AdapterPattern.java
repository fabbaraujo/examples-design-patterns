public class AdapterPattern {
    public static class PecaQuadrada {
        private double largura;

        public PecaQuadrada(double largura) {
            this.largura = largura;
        }

        public double getLargura() {
            return largura;
        }

        public double getPecaQuadrada() {
            double resultado;
            resultado = Math.pow(this.largura, 2);
            return resultado;
        }
    }

    public static class PecaCirculo {
        private double raio;

        public PecaCirculo() {}

        public PecaCirculo(double raio) {
            this.raio = raio;
        }

        public double getRaio() {
            return raio;
        }
    }

    public static class BuracoCirculo {
        private double raio;

        public BuracoCirculo(double raio) {
            this.raio = raio;
        }

        public double getRaio() {
            return raio;
        }

        public boolean encaixar(PecaCirculo peca) {
            boolean resultado;
            resultado = (this.getRaio() >= peca.getRaio());
            return resultado;
        }
    }

    public static class PecaQuadradaAdapter extends PecaCirculo {
        private PecaQuadrada peca;

        public PecaQuadradaAdapter(PecaQuadrada peca) {
            this.peca = peca;
        }

        @Override
        public double getRaio() {
            double resultado;

            resultado = (Math.sqrt(Math.pow((peca.getLargura() / 2), 2) * 2));
            return resultado;
        }
    }

    public static void main(String[] args) {

        BuracoCirculo buraco = new BuracoCirculo(5);
        PecaCirculo pecaCirculo = new PecaCirculo(5);
        if (buraco.encaixar(pecaCirculo)) {
            System.out.println("Peca circulo r5 encaixa no buraco circulo r5.");
        }

        PecaQuadrada pecaQuadradaPequena = new PecaQuadrada(2);
        PecaQuadrada pecaQuadradaGrande = new PecaQuadrada(20);
        // buraco.encaixar(pecaQuadradaPequena); // Não compila

        // Adapter resolve o problema.
        PecaQuadradaAdapter pecaQuadradaPequenaAdapter = new PecaQuadradaAdapter(pecaQuadradaPequena);
        PecaQuadradaAdapter pecaQuadradaGrandeAdapter = new PecaQuadradaAdapter(pecaQuadradaGrande);

        if (buraco.encaixar(pecaQuadradaPequenaAdapter)) {
            System.out.println("Peca quadrada l2 encaixa no buraco circulo r5.");
        }
        if (!buraco.encaixar(pecaQuadradaGrandeAdapter)) {
            System.out.println("Peca quadrada l20 não se encaixa no buraco circulo r5.");
        }
    }

}
