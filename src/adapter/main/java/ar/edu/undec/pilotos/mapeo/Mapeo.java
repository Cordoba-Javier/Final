package ar.edu.undec.pilotos.mapeo;

import ar.edu.undec.pilotos.entidad.PilotoDTO;
import ar.edu.undec.pilotos.entidad.PilotoData;
import modelo.Piloto;

public class Mapeo {
    public static PilotoData mapeoCoreData(Piloto piloto){
        return PilotoData.instancia(piloto);
    }

    public static Piloto maperoDataCore(PilotoData piloto){
        return Piloto.Instancia(piloto.getIdpiloto(), piloto.getNombre(), piloto.getApellido(), piloto.getNombreCompleto(), piloto.getAbreviasion(), piloto.getUrl());
    }

    public static Piloto mapeoDTOCore(PilotoDTO piloto){
        return Piloto.Instancia(piloto.getIdpiloto(), piloto.getNombre(), piloto.getApellido(), piloto.getNombreCompleto(), piloto.getAbreviasion(), piloto.getUrl());
    }

}
