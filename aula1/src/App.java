import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {

    // Busca a url no arquivo.properties e adiciona a mesma numa variavel e retorna a variavel
    public static Properties getProp() throws IOException {
        Properties prop = new Properties();
        try (FileInputStream file = new FileInputStream("src/Properties/url.properties")) {
            prop.load(file);
        }
        return prop;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        //recebe o conteudo da variavel prop e adiciona um mesmo em uma string
        String url;
        Properties prop = getProp();
        url = prop.getProperty("prop.server.url");

        //faz a conexão HTTP para receber os dados do json
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //extrair os dados desejados do json
        var parser = new JsonParse();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //cria diretorio de figurinhas caso nao exista
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        //inicializa o gerador de figurinhas
        var geradora = new GeradorDeStikers();

        //faz o tratamento dos dados
        for (Map<String,String> filme : listaDeFilmes) {
            String urlimagem = filme.get("image");
            String titulo = filme.get("title");
            var rating = filme.get("imDbRating");
            var tam = Double.parseDouble(rating);

            //personaliza o texto das figurinhas de acordo com o rating
            String texto;
            if(tam >= 9){
                texto = "TOPZEIRA!";
            }else {
                texto = "MARROMENOS";
            }

            //gera as figurinhas e armazena no diretorio especificado
            InputStream inputStream = new URL(urlimagem).openStream();
            String nomeArquivo = "figurinhas/" + titulo + ".png";
            geradora.gerar(inputStream, nomeArquivo, texto);

            //apresentação personalizada dos dados no terminal
            for (int x = 0; x < 100; x++) {
                System.out.print("-");
            }
            System.out.println("\n\u001B[1mTitulo: \u001B[m" + titulo);
            System.out.println("\u001B[1mPoster: \u001B[1m" + urlimagem);
            System.out.println("\u001B[43mClassificação: " + rating + "\u001B[m");

            for (int x = 0; x < tam; x++){
                System.out.print("⭐");
            }
            System.out.println();

            for (int x = 0; x < 100; x++) System.out.print("-");
            System.out.println();
        }
    }
}
