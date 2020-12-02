/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.farmaciasgarciak.data;

import es.cifpcm.farmaciasgarciak.model.Farmacia;
import java.util.List;

public interface Persistence {

    public void openJSON() throws Exception;

    public void closeJSON() throws Exception;

    public void add (Farmacia farmacia);
    
    public void delete(Farmacia farmacia);
    
    public List<Farmacia> list();

}
