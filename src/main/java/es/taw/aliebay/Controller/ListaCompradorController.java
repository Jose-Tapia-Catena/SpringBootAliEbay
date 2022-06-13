package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.dto.ListacompradorDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.CompradorService;
import es.taw.aliebay.service.ListaCompradorService;
import es.taw.aliebay.service.MarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ListaCompradorController {

    private ListaCompradorService listacompradorService;
    private CompradorService compradorService;

    public ListaCompradorService getListacompradorService() {
        return listacompradorService;
    }

    @Autowired
    public void setListacompradorService(ListaCompradorService listacompradorService) {
        this.listacompradorService = listacompradorService;
    }

    public CompradorService getCompradorService() {
        return compradorService;
    }

    @Autowired
    public void setCompradorService(CompradorService compradorService) {
        this.compradorService = compradorService;
    }

    @GetMapping("/marketing/")
    public String doInit(HttpSession httpSession, Model model){

        List<ListacompradorDTO> listacompradores = this.listacompradorService.listarListaCompradores();
        model.addAttribute("listaCompradores", listacompradores);

        UsuarioDTO user = (UsuarioDTO) httpSession.getAttribute("user");
        model.addAttribute("marketing", user);

        return "listaCompradores";
    }

    @GetMapping("/marketing/listaCompradorMensajes/{idLista}/")
    public String verMensajes(){
        return null;
    }

    @GetMapping("/marketing/listaCompradorEditar/{idLista}/")
    public String editarLista(@PathVariable("idLista") Integer idLista,
                              Model model){

        ListacompradorDTO listaComprador = this.listacompradorService.buscarListacomprador(idLista);
        model.addAttribute("listaComprador", listaComprador);

        List<CompradorDTO> compradores = compradorService.listarCompradores();
        model.addAttribute("compradores", compradores);

        List<CompradorDTO> compradoresLista = new ArrayList<>();
        model.addAttribute("compradoresLista", compradoresLista);

        return "listaComprador";
    }

    @GetMapping("/marketing/listaCompradorCrear/")
    public String editarLista(HttpSession session, Model model){

        ListacompradorDTO listaComprador = new ListacompradorDTO();
        model.addAttribute("listaComprador", listaComprador);

        List<CompradorDTO> compradores = compradorService.listarCompradores();
        model.addAttribute("compradores", compradores);

        List<CompradorDTO> compradoresLista = new ArrayList<>();
        model.addAttribute("compradoresLista", compradoresLista);

        return "listaComprador";
    }


    @PostMapping("/marketing/listaCompradorGuardar/")
    public String editarLista(@ModelAttribute("listaComprador")  ListacompradorDTO dto){
        if(dto.getIdLista() != null){
            this.listacompradorService.modificarListacomprador(dto);
        }else{
            this.listacompradorService.crearListacomprador(dto);
        }
        return "redirect:/marketing/";
    }


    @GetMapping("/marketing/listaCompradorBorrar/{idLista}/")
    public String borrarLista(HttpSession session,
                              @PathVariable("idLista") Integer idLista){
        this.listacompradorService.borrarListacomprador(idLista);
        return "redirect:/marketing/";
    }
}
