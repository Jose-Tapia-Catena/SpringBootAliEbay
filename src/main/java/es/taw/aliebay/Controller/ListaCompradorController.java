package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.dto.ListacompradorDTO;
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
    public String doInit(Model model){

        List<ListacompradorDTO> listacompradores = this.listacompradorService.listarListaCompradores();
        model.addAttribute("listaCompradores", listacompradores);

        return "listaCompradores";
    }

    @GetMapping("/marketing/listaCompradorMensajes/{id}")
    public String verMensajes(){
        return null;
    }

    @GetMapping("marketing/listaCompradorCrear")
    public String crearLista(){
        return null;
    }

    @GetMapping("/marketing/listaCompradorEditar/{id}")
    public String editarLista(@PathVariable("id") Integer idLista,
                              Model model){

        ListacompradorDTO listaComprador = this.listacompradorService.buscarListacomprador(idLista);
        model.addAttribute("listaComprador", listaComprador);

        List<CompradorDTO> compradores = compradorService.listarCompradores();
        model.addAttribute("compradores", compradores);

        List<CompradorDTO> compradoresLista = new ArrayList<>();
        model.addAttribute("compradoresLista", compradoresLista);

        return "listaComprador";
    }

    /*
    @PostMapping("/marketing/listaCompradorGuardar")
    public String editarLista(@ModelAttribute("listaComprador")  ListacompradorDTO dto){
        if(dto.getIdLista() == null){
            this.listacompradorService.modificarLista(dto);
        }

        if (strId == null || strId.isEmpty()){
            lComprador = new ListacompradorDTO();
        } else{
            lComprador = this.lcS.buscarListacomprador(Integer.parseInt(strId));
        }

        String nombre = request.getParameter("nombre");

        if (strId == null || strId.isEmpty()){
            lComprador = lcS.crear(nombre);
        } else {
            lcS.editar(lComprador.getIdLista(), nombre);
        }

        List <CompradorDTO> c = new ArrayList();
        for (UsuarioDTO comprador : compradorS.listarComprador()){
            int compradorID = comprador.getIdUsuario();
            List <ListacompradorDTO> a = lcS.getListListaComprador(compradorID);
            str = request.getParameter(Integer.toString(compradorID));
            if (str != null){ //Aqui entro si he seleccionado el comprador
                //actualizar la referencia de comprador
                c.add(comprador.getComprador());
                if (!a.contains(lComprador)){
                    compradorS.añadirLista(lComprador, compradorID);
                    //this.lcS.añadirComprador(lComprador, compradorID);
                }
            } else {
                if (a.contains(lComprador)){
                    compradorS.eliminarLista(lComprador, compradorID);
                }
            }
        }

        if (!c.isEmpty()) this.lcS.editar(lComprador.getIdLista(), nombre, c);

        response.sendRedirect(request.getContextPath() + "/MarketingServlet");

        this.listacompradorService.guardar(dto);
        return "redirect:/marketing/";
    }
*/
    @GetMapping("/marketing/listaCompradorBorrar/{id}")
    public String borrarLista(){
        return null;
    }
}
