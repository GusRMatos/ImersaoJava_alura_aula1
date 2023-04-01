package functions;

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

public class StickersGenerator {

    //declara o escopo do metodo gerar
    public void generate(InputStream inputStream, String archiveName, String text) throws IOException {

        //cria uma imagem copia do arquivo original com novo layout transparente para receber a legenda
        BufferedImage originalImage = ImageIO.read(inputStream);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage NewHeight = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        //chama o metodo que permite escrever na imagem e personaliza a fonte
        Graphics2D graphics = (Graphics2D) NewHeight.getGraphics();
        graphics.drawImage(originalImage, 0, 0,null);
        var font = new Font("comic sans",Font.BOLD,100);
        graphics.setColor(Color.yellow);
        graphics.setFont(font);

        //faz o alinhamento do text na imagem e adiciona uma borda no text
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle =fontMetrics.getStringBounds(text,graphics);
        int widthText = (int)rectangle.getWidth();
        int textPositionX = (width - widthText)/2;
        int textPositionY = newHeight - 80;
        graphics.drawString(text,textPositionX,textPositionY);
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout =   new TextLayout(text, font, fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(textPositionX, textPositionY);
        graphics.setTransform(transform);
        var outlineStroke = new  BasicStroke(width * 0.004f);
        graphics.setStroke( outlineStroke);
        graphics.setColor(Color.black);
        graphics.draw(outline);

        //escreve na imagem
        ImageIO.write(NewHeight,"png",new File(archiveName));

    }

}
