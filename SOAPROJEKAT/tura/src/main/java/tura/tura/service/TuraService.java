package tura.tura.service;

import tura.tura.entity.Korpa;
import tura.tura.entity.Tura;
import tura.tura.entity.TuraDTO;

import java.util.List;
import java.util.Set;


public interface TuraService {

    Tura createTura(Tura tura) throws Exception;

    List<Tura> sveture() throws Exception;

    Korpa novakorpa() throws Exception;

    Set<TuraDTO> svetureukorpi(String idkorpe, String tura1, String brojljudi) throws Exception;

    Set<TuraDTO> ukloniizkorpe(String idkorpe, String turaukorpi) throws Exception;
}
