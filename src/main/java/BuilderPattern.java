public class BuilderPattern {
    static class Cafe {

        private Cafe(Builder builder) {
            this.tipo = builder.tipo;
            this.acucar = builder.acucar;
            this.leite = builder.leite;
            this.tamanho = builder.tamanho;
        }

        private String tipo;
        private boolean acucar;
        private boolean leite;
        private String tamanho;

        public static class Builder {
            private String tipo;
            private boolean acucar;
            private boolean leite;
            private String tamanho;

            public Builder(String tipo) {
                this.tipo = tipo;
            }

            public Builder acucar(boolean value) {
                this.acucar = value;
                return this;
            }

            public Builder leite(boolean value) {
                this.leite = value;
                return this;
            }

            public Builder tamanho(String value) {
                this.tamanho = value;
                return this;
            }

            public Cafe build() {
                return new Cafe(this);
            }
        }

        @Override
        public String toString() {
            return String.format("Cafe [tipo=%s, acucar=%s, leite=%s, tamanho=%s]", this.tipo, acucar, leite, tamanho);
        }
    }

    public static void main(String[] args) {
        Cafe coffee = new BuilderPattern.Cafe.Builder("Mocha").leite(true).acucar(false).tamanho("Large").build();
        System.out.println(coffee);
    }
}