package functions;

import model.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaExtractContent {
    public List<Content> extractContent(String Json){

        var parser = new JsonParse();
        List<Map<String, String>> attributesList = parser.parse(Json);

        List<Content> contents = new ArrayList<>();
        for (Map<String, String> attributes : attributesList ) {
            String title = attributes.get("title");
            String urlImage = attributes.get("url");
            var content = new Content(title, urlImage);
            contents.add(content);
        }
        return contents;
    }
}

