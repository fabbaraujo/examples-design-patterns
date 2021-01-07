import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FactoryPattern {
    public abstract static class Dialog {

        public void renderizarJanela() {
            Botao okBotao = createBotao();
            okBotao.renderizar();
        }

        public abstract Botao createBotao();
    }

    public static class LinuxDialog extends Dialog {

        @Override
        public Botao createBotao() {
            return new LinuxBotao();
        }
    }

    public static class HtmlDialog extends Dialog {

        @Override
        public Botao createBotao() {
            return new HtmlBotao();
        }
    }

    public interface Botao {
        void renderizar();
        void onClick();
    }

    public static class LinuxBotao implements Botao {
        JPanel painel = new JPanel();
        JFrame frame = new JFrame();
        JButton botao;

        public void renderizar() {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel label = new JLabel("Hello World!");
            label.setOpaque(true);
            label.setBackground(new Color(235, 233, 126));
            label.setFont(new Font("Dialog", Font.BOLD, 44));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            painel.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.getContentPane().add(painel);
            painel.add(label);
            onClick();
            painel.add(botao);

            frame.setSize(320, 200);
            frame.setVisible(true);
            onClick();
        }

        public void onClick() {
            botao = new JButton("Exit");
            botao.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    System.exit(0);
                }
            });
        }
    }

    public static class HtmlBotao implements Botao {

        public void renderizar() {
            System.out.println("<button>Botao de Teste</button>");
            onClick();
        }

        public void onClick() {
            System.out.println("Click! Botao retorna - 'Olá Mundo!'");
        }
    }

    private static Dialog dialog;

    public static void main(String[] args) {
        configurar();
        executaRegraDeNegocio();
    }

    static void configurar() {
        if (System.getProperty("os.name").equals("Linux")) {
            dialog = new LinuxDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    static void executaRegraDeNegocio() {
        dialog.renderizarJanela();
    }

}