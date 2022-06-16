package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.*;
import es.taw.aliebay.service.ListaCompradorService;
import es.taw.aliebay.service.MarketingService;
import es.taw.aliebay.service.MensajeService;
import es.taw.aliebay.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class MensajeController  extends AliEbaySessionController{

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MarketingService marketingService;

    @Autowired
    private ListaCompradorService listaCompradorService;

    @GetMapping("/marketing/listaCompradorMensajes/{idLista}/marketing/{idMarketing}")
    public String doInit(HttpSession session,
                         @PathVariable("idLista") Integer idLista,
                         @PathVariable("idMarketing") Integer idMarketing,
                         Model model){

        if (this.comprobarMarketing(session)){
            List<MensajeDTO> mensajes = mensajeService.listarMensajesByIdListaAndIdMarketing(idLista,idMarketing);
            model.addAttribute("mensajes", mensajes);


            model.addAttribute("idLista",idLista);

            return "mensajeComprador";
        } else {
            return "redirect:/login/error/";

        }
    }

    @GetMapping("/marketing/mensaje/editar/{idMensaje}")
    public String doEdit(HttpSession session,
                         @PathVariable("idMensaje") Integer idMensaje,
                         Model model){

        if (this.comprobarMarketing(session)){
            MensajeDTO dto = this.mensajeService.buscarMensaje(idMensaje);
            model.addAttribute("mensaje", dto);

            List<ProductoDTO> productos = this.productoService.listarProductos();
            model.addAttribute("productos", productos);

            return "mensaje";
        } else {
            return "redirect:/login/error/";
        }
    }

    @GetMapping("/marketing/mensaje/crear/{idLista}")
    public String doCreate(HttpSession session,
                           @PathVariable("idLista") Integer idLista,
                            Model model){

        if (this.comprobarMarketing(session)){
            MensajeDTO dto = new MensajeDTO();
            dto.setListaComprador(this.listaCompradorService.buscarListacomprador(idLista));
            model.addAttribute("mensaje", dto);

            List<ProductoDTO> productos = this.productoService.listarProductos();
            model.addAttribute("productos", productos);

            return "mensaje";
        } else {
            return "redirect:/login/error/";
        }
    }

    @PostMapping("/marketing/MensajeGuardar")
    public String guardar(HttpSession session,
                          @ModelAttribute("mensaje") MensajeDTO dto) throws ParseException {

        if (this.comprobarMarketing(session)){
            if (dto.getId() != null){
                this.mensajeService.modificarMensaje(dto);
            } else {
                UsuarioDTO usuario  = (UsuarioDTO) session.getAttribute("user");
                MarketingDTO marketingDTO = this.marketingService.buscarById(usuario.getIdUsuario());
                ListacompradorDTO listaComprador = this.listaCompradorService.buscarListacomprador(dto.getListaComprador().getIdLista());

                dto.setListaComprador(listaComprador);
                dto.setMarketing(marketingDTO);

                this.mensajeService.crearMensaje(dto);
            }
            return "redirect:/marketing/listaCompradorMensajes/" + dto.getListaComprador().getIdLista()
                    + "/marketing/" + dto.getMarketing().getUsuario().getIdUsuario() + "/";
        } else {
            return "redirect:/login/error/";
        }
    }

    @GetMapping("/marketing/mensaje/borrar/{idMensaje}")
    public String borrarLista(HttpSession session,
                              @PathVariable("idMensaje") Integer idMensaje){

        if (this.comprobarMarketing(session)){
            MensajeDTO dto = this.mensajeService.buscarMensaje(idMensaje);
            Integer idLista = dto.getListaComprador().getIdLista();
            Integer idMarketing = dto.getMarketing().getUsuario().getIdUsuario();

            this.mensajeService.borrarMensaje(idMensaje);

            return "redirect:/marketing/listaCompradorMensajes/" + idLista
                    + "/marketing/" + idMarketing + "/";
        } else {
            return "redirect:/login/error/";
        }
    }


}
