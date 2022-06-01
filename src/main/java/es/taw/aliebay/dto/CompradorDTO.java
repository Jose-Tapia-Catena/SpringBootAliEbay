package es.taw.aliebay.dto;

import java.util.List;

public class CompradorDTO {
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
        this.productoList = productoList;
    }

    public List<ListacompradorDTO> getListacompradorList() {
        return listacompradorList;
    }

    public void setListacompradorList(List<ListacompradorDTO> listacompradorList) {
        this.listacompradorList = listacompradorList;
    }

    public List<VentaDTO> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<VentaDTO> ventaList) {
        this.ventaList = ventaList;
    }

    public List<PujaDTO> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<PujaDTO> pujaList) {
        this.pujaList = pujaList;
    }

    private UsuarioDTO usuario;
    private List<ProductoDTO> productoList;
    private List<ListacompradorDTO> listacompradorList;
    private List<VentaDTO> ventaList;
    private List<PujaDTO> pujaList;

}
