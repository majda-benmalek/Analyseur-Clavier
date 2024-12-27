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
        this.path = "ressources/azerty.json";
        chargerAzerty();
        // for (Touche touche : touches) {
        // System.out.println(touche);
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
        // TODO : shift droit ou gauche??
        try (FileReader reader = new FileReader((this.path))) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray clavier = jsonObject.getAsJsonArray("clavier");

            for (JsonElement element : clavier) {
                JsonObject toucheJson = element.getAsJsonObject();
                int x = toucheJson.get("x").getAsInt();
                int y = toucheJson.get("y").getAsInt();
                String touche = toucheJson.get("touche").getAsString();
                Doigt doigt = null;
                List<String> touchesMortes = new ArrayList<>();
                if (!toucheJson.get("doigt").isJsonNull()) {
                    doigt = Doigt.valueOf(toucheJson.get("doigt").getAsString().toUpperCase());
                }
                if (toucheJson.has("morte") && toucheJson.get("morte").isJsonArray()) {
                    JsonArray jsonMortes = toucheJson.getAsJsonArray("morte");
                    for (JsonElement morteElement : jsonMortes) {
                        String morteTouche = morteElement.getAsString();
                        if(morteTouche != null){
                            touchesMortes.add(morteTouche);
                        }
                    }
                }
                this.touches.add(new Touche(touche, x, y, doigt, touchesMortes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //retourne une liste pour les touches en double (shift, alt, ctrl)
    public List<Touche> chercheTouche(String etiquette) {
        List<Touche> res = new ArrayList<>();
        for( Touche t : touches){
            if(etiquette.equals(t.getEtiq())){
                res.add(t);
            }
        }
        return res;
    }

    @Override
    public List<Touche> getTouches() {
        return touches;
    }

}
