public class AbstractFactoryPattern {

    public interface GUIFactory {
        Botao criarBotao();
        Checkbox criarCheckbox();
    }

    public static class WindowsFactory implements GUIFactory {

        @Override
        public Botao criarBotao() {
            return new WindowsBotao();
        }

        @Override
        public Checkbox criarCheckbox() {
            return new WindowsCheckbox();
        }
    }

    public static class LinuxFactory implements GUIFactory {

        @Override
        public Botao criarBotao() {
            return new LinuxBotao();
        }

        @Override
        public Checkbox criarCheckbox() {
            return new LinuxCheckbox();
        }
    }

    public interface Checkbox {
        void pintar();
    }

    public static class WindowsCheckbox implements Checkbox {

        @Override
        public void pintar() {
            System.out.println("Você criou um checkbox Windows.");
        }
    }

    public static class LinuxCheckbox implements Checkbox {

        @Override
        public void pintar() {
            System.out.println("Você criou um checkbox Linux.");
        }
    }

    public interface Botao {
        void pintar();
    }

    public static class WindowsBotao implements Botao {

        @Override
        public void pintar() {
            System.out.println("Você criou um botão Windows.");
        }
    }

    public static class LinuxBotao implements Botao {

        @Override
        public void pintar() {
            System.out.println("Você criou um botão Linux.");
        }
    }

    public static class Aplicacao {
        private Botao botao;
        private Checkbox checkbox;

        public Aplicacao(GUIFactory factory) {
            botao = factory.criarBotao();
            checkbox = factory.criarCheckbox();
        }

        public void pintar() {
            botao.pintar();
            checkbox.pintar();
        }
    }

    private static Aplicacao configurarAplicacao() {
        Aplicacao app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("linux")) {
            factory = new LinuxFactory();
            app = new Aplicacao(factory);
        } else {
            factory = new WindowsFactory();
            app = new Aplicacao(factory);
        }
        return app;
    }

    public static void main(String[] args) {
        Aplicacao app = configurarAplicacao();
        app.pintar();
    }
}
