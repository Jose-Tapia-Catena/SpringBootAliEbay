package es.taw.aliebay.service;

import es.taw.aliebay.dao.MarketingRepository;
import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.dto.MarketingDTO;
import es.taw.aliebay.entity.Comprador;
import es.taw.aliebay.entity.Marketing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketingService {
    public MarketingRepository getMarketingRepository() {
        return marketingRepository;
    }
    @Autowired
    public void setMarketingRepository(MarketingRepository marketingRepository) {
        this.marketingRepository = marketingRepository;
    }

    private MarketingRepository marketingRepository;

    public List<MarketingDTO> listarMarketings(){
        List<Marketing> marketings = marketingRepository.findAll();
        return listaEntityADTO(marketings);
    }

    private List<MarketingDTO> listaEntityADTO (List<Marketing> lista) {
        List<MarketingDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Marketing m : lista) {
                listaDTO.add(m.toDTO());
            }
        }
        return listaDTO;
    }

    public MarketingDTO buscarById(Integer idUsuario) {
        Marketing marketing = this.marketingRepository.findById(idUsuario).orElse(null);
        return marketing.toDTO();
    }
}
