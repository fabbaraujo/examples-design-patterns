import java.io.File;
import java.util.*;

public class ObserverPattern {

    public interface OuvinteDeEventos {
        void update(String tipoDeEvento, File arquivo);
    }

    public static class GerenciadorDeEventos {
        Map<String, List<OuvinteDeEventos>> ouvintes = new HashMap<>();

        public GerenciadorDeEventos(String... operacoes) {
            for (String operacao : operacoes) {
                this.ouvintes.put(operacao, new ArrayList<>());
            }
        }

        public void assinar(String tipoDeEvento, OuvinteDeEventos ouvinte) {
            List<OuvinteDeEventos> usuarios = ouvintes.get(tipoDeEvento);
            usuarios.add(ouvinte);
        }

        public void desassinar(String tipoDeEvento, OuvinteDeEventos ouvinte) {
            List<OuvinteDeEventos> usuarios = ouvintes.get(tipoDeEvento);
            usuarios.remove(ouvinte);
        }

        public void notify(String tipoDeEvento, File arquivo) {
            List<OuvinteDeEventos> usuarios = ouvintes.get(tipoDeEvento);
            for (OuvinteDeEventos ouvinte : usuarios) {
                ouvinte.update(tipoDeEvento, arquivo);
            }
        }
    }

    public static class LogArquivoOuvinte implements OuvinteDeEventos {
        private File log;

        public LogArquivoOuvinte(String nomeDoArquivo) {
            this.log = new File(nomeDoArquivo);
        }

        @Override
        public void update(String tipoDeEvento, File arquivo) {
            System.out.println("Salvando no log " + log + ": Alguém executou a operação " + tipoDeEvento + " no seguinte arquivo: " + arquivo.getName());
        }
    }

    public static class NotificacaoEmailOuvinte implements OuvinteDeEventos {
        private String email;

        public NotificacaoEmailOuvinte(String email) {
            this.email = email;
        }

        @Override
        public void update(String tipoDeEvento, File arquivo) {
            System.out.println("Email para " + email + ": Alguém executou a operação "+ tipoDeEvento +" no seguinte arquivo: " + arquivo.getName());
        }
    }

    public static class Editor {
        public GerenciadorDeEventos eventos;
        private File arquivo;

        public Editor() {
            this.eventos = new GerenciadorDeEventos("open", "save");
        }

        public void abrirArquivo(String caminhoArquivo) {
            this.arquivo = new File(caminhoArquivo);
            eventos.notify("open", arquivo);
        }

        public void salvarArquivo() throws Exception {
            if (this.arquivo != null) {
                eventos.notify("save", arquivo);
            } else {
                throw new Exception("Por favor, abra o arquivo primeiro.");
            }
        }
    }

    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.eventos.assinar("open", new LogArquivoOuvinte("log.txt"));
        editor.eventos.assinar("save", new NotificacaoEmailOuvinte("admin@example.com"));

        try {
            editor.abrirArquivo("test.txt");
            editor.salvarArquivo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
