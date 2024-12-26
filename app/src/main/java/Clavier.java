import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;

public class Clavier implements InterfaceClavier {
    private List<Touche> touches;
    private String path;

    public Clavier() {
        this.touches = new ArrayList<>();
        this.path = "src/main/java/azerty.json";
        chargerAzerty();
        // for (Touche touche : touches) {
        //     System.out.println(touche);
        // }
    }

    public Clavier(String path) {
        this.touches = new ArrayList<>();
        this.path = path;
        creerClavier();
    }

    public void chargerAzerty() {
        creerClavier();
    }

    @Override
    public void creerClavier() {
        try (FileReader reader = new FileReader((this.path))) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray jsonTouches = jsonObject.getAsJsonArray("clavier");

            for (JsonElement element : jsonTouches) {
                JsonObject jsonTouche = element.getAsJsonObject();
                int x = jsonTouche.get("x").getAsInt();
                int y = jsonTouche.get("y").getAsInt();
                String touche = jsonTouche.get("touche").getAsString();
                Doigt doigt = null;
                List<Touche> touchesMortes = new ArrayList<>();
                if (!jsonTouche.get("doigt").isJsonNull()) {
                    doigt = Doigt.valueOf(jsonTouche.get("doigt").getAsString().toUpperCase());
                }
                if (jsonTouche.has("morte") && jsonTouche.get("morte").isJsonArray()) {
                    JsonArray jsonMortes = jsonTouche.getAsJsonArray("morte");
                    for (JsonElement morteElement : jsonMortes) {
                        String morteTouche = morteElement.getAsString();
                        touchesMortes.add(new Touche(morteTouche, x, y, doigt));
                    }
                } else {
                    touchesMortes = null;
                }
                this.touches.add(new Touche(touche, x, y, doigt, touchesMortes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Touche chercheTouche(char etiquette) {
        Touche res = null;
        for (Touche touche : touches) {
            if (touche.getEtiq().charAt(0) == etiquette) {
                res=touche;
                if(res!=null){
                    return res;
                }
            }
        }
        
        return null;
    }

    @Override
    public List<Touche> getTouches() {
        return touches;
    }

}
