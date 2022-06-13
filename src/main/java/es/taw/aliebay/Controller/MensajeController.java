package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.*;
import es.taw.aliebay.entity.Marketing;
import es.taw.aliebay.entity.Mensaje;
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
import java.util.List;

@Controller
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MarketingService marketingService;

    @Autowired
    private ListaCompradorService listaCompradorService;

    @GetMapping("/marketing/listaCompradorMensajes/{idLista}/marketing/{idMarketing}")
    public String doInit(@PathVariable("idLista") Integer idLista,
                         @PathVariable("idMarketing") Integer idMarketing,
                         Model model){
        List<MensajeDTO> mensajes = mensajeService.listarMensajesByIdListaAndIdMarketing(idLista,idMarketing);
        model.addAttribute("mensajes", mensajes);

        model.addAttribute("idLista",idLista);

        return "mensajeComprador";
    }

    @GetMapping("/marketing/mensaje/editar/{idMensaje}")
    public String doEdit(@PathVariable("idMensaje") Integer idMensaje,
                         Model model){

        MensajeDTO dto = this.mensajeService.buscarMensaje(idMensaje);
        model.addAttribute("mensaje", dto);

        List<ProductoDTO> productos = this.productoService.listarProductos();
        model.addAttribute("productos", productos);

        return "mensaje";
    }

    @GetMapping("/marketing/mensaje/crear/{idLista}")
    public String doCreate(@PathVariable("idLista") Integer idLista,
            Model model){

        MensajeDTO dto = new MensajeDTO();
        dto.setListaComprador(this.listaCompradorService.buscarListacomprador(idLista));
        model.addAttribute("mensaje", dto);

        List<ProductoDTO> productos = this.productoService.listarProductos();
        model.addAttribute("productos", productos);

        return "mensaje";
    }

    @PostMapping("/marketing/MensajeGuardar")
    public String guardar(@ModelAttribute("mensaje") MensajeDTO dto,
                          HttpSession session){
        if (dto.getId() != null){
            this.mensajeService.modificarMensaje(dto);
        } else {
            UsuarioDTO usuario  = (UsuarioDTO) session.getAttribute("user");
            MarketingDTO marketingDTO = this.marketingService.buscarById(usuario.getIdUsuario());
            ListacompradorDTO prueba = dto.getListaComprador();

            dto.setListaComprador(prueba);
            dto.setMarketing(marketingDTO);

            this.mensajeService.crearMensaje(dto);
        }
        return "redirect:/marketing/listaCompradorMensajes/" + dto.getListaComprador().getIdLista()
                + "/marketing/" + dto.getMarketing().getUsuario().getIdUsuario() + "/";
    }



}
