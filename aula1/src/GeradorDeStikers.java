import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeradorDeStikers {

    //declara o escopo do metodo gerar
    public void gerar (InputStream inputStream, String nomeArquivo, String texto) throws IOException {

        //cria uma imagem copia do arquivo original com novo layout transparente para receber a legenda
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        int width = imagemOriginal.getWidth();
        int heigth = imagemOriginal.getHeight();
        int novaaltura = heigth + 200;
        BufferedImage novaimagem = new BufferedImage(width, novaaltura, BufferedImage.TRANSLUCENT);

        //chama o metodo que permite escrever na imagem e personaliza a fonte
        Graphics2D graphics = (Graphics2D) novaimagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0,null);
        var fonte = new Font("comic sans",Font.BOLD,100);
        graphics.setColor(Color.yellow);
        graphics.setFont(fonte);

        //faz o alinhamento do texto na imagem e adiciona uma borda no texto
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo =fontMetrics.getStringBounds(texto,graphics);
        int larguratexto = (int)retangulo.getWidth();
        int posicaotextox = (width - larguratexto)/2;
        int posicaotextoy = novaaltura - 80;
        graphics.drawString(texto,posicaotextox,posicaotextoy);
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textlayout =   new TextLayout(texto, fonte, fontRenderContext);
        Shape outline = textlayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaotextox, posicaotextoy);
        graphics.setTransform(transform);
        var outlineStroke = new  BasicStroke(width * 0.004f);
        graphics.setStroke( outlineStroke);
        graphics.setColor(Color.black);
        graphics.draw(outline);

        //escreve na imagem
        ImageIO.write(novaimagem,"png",new File(nomeArquivo));

    }

}
