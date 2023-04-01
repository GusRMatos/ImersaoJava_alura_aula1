import exception.HttpClientException;
import functions.ClientHttp;
import functions.NasaExtractContent;
import functions.StickersGenerator;
import model.Content;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, HttpClientException {

        String server = "nasa.prop.server.url";
        var http = new ClientHttp();
        String Json = http.SearchData(server);

        //var imDbExtractContent = new functions.ImDbExtractContent();
        //List<Content> contents = imDbExtractContent.extractContent(Json);

        var nasaExtractContent = new NasaExtractContent();
        List<Content> contents = nasaExtractContent.extractContent(Json);

        //inicializa o gerador de figurinhas
        var generator = new StickersGenerator();
        var directory = new File("D:\\Dev\\alura\\aula1\\src\\output", "stickers/");
        directory.mkdir();


        //faz o tratamento dos dados
        for (int i = 0; i < contents.size(); i++){
            Content content = contents.get(i);
            String archiveName = directory + "/" + content.getTitle() + ".png";
            if(server == "topfilmes.prop.server.url") {
                String text;
                var rating = content.getRating();
                var size = Double.parseDouble(rating);
                //personaliza o text das figurinhas de acordo com o rating
                if (size >= 9) {
                    text = "TOPZEIRA!";
                } else {
                    text = "MARROMENOS";
                }
                //gera as figurinhas e armazena no directory especificado
                InputStream inputStream = new URL(content.getUrlImage()).openStream();
                generator.generate(inputStream, archiveName, text);

                //apresentação personalizada dos dados no terminal
                for (int x = 0; x < 100; x++) {
                    System.out.print("-");
                }
                System.out.println("\n\u001B[1mTitulo: \u001B[m" + content.getTitle());
                System.out.println("\u001B[1mPoster: \u001B[1m" + content.getUrlImage());
                System.out.println("\u001B[43mClassificação: " + rating + "\u001B[m");

                for (int x = 0; x < size; x++) {
                    System.out.print("⭐");
                }
                System.out.println();

                for (int x = 0; x < 100; x++) System.out.print("-");
                System.out.println();
            }else if (server == "nasa.prop.server.url"){
                for (int x = 0; x < 100; x++) {
                    System.out.print("-");
                }
                System.out.println("\n\u001B[1mTitulo: \u001B[m" + content.getTitle());
                System.out.println();
                for (int x = 0; x < 100; x++) System.out.print("-");
                System.out.println();
                String text = "Incrivel";
                InputStream inputStream = new URL(content.getUrlImage()).openStream();
                generator.generate(inputStream, archiveName, text);
            }
        }
    }
}
