package es.cifpcm.farmaciasgarciak.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import es.cifpcm.farmaciasgarciak.controller.FarmaciasGarciaK;
import es.cifpcm.farmaciasgarciak.model.Farmacia;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ImplementsPersistence implements Persistence {
    
    private static final String FILESTORE_PATH = System.getProperty("java.io.tmpdir").concat("\\GarciaKevin_farmacias.json");
    
    private List<Farmacia> memStore = new ArrayList();
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Override
    public void openJSON() throws Exception {
        try (Reader reader = new FileReader(FILESTORE_PATH)){
            memStore = gson.fromJson(reader, new TypeToken<List<Farmacia>>() {
            }.getType());
            System.out.println(FILESTORE_PATH);
        }
        catch(IOException ex){
            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void closeJSON() throws Exception {
        try (FileWriter writer = new FileWriter(FILESTORE_PATH)){
            gson.toJson(memStore, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void add(Farmacia farmacia){
        memStore.add(farmacia);
    }
    
    @Override
    public void delete(Farmacia farmacia){
        memStore.remove(farmacia);
    }
    
    public List<Farmacia> list(){
        return memStore;
    }
}
