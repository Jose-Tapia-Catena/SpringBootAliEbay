package es.taw.aliebay.dto;

import es.taw.aliebay.entity.Comprador;

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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompradorDTO)) {
            return false;
        }
        CompradorDTO other = (CompradorDTO) object;
        if ((this.usuario.getIdUsuario() == null && other.usuario.getIdUsuario() != null) ||
                (this.usuario.getIdUsuario() != null && !this.usuario.getIdUsuario().equals(other.usuario.getIdUsuario()))) {
            return false;
        }
        return true;
    }

    private UsuarioDTO usuario;
    private List<ProductoDTO> productoList;
    private List<ListacompradorDTO> listacompradorList;
    private List<VentaDTO> ventaList;
    private List<PujaDTO> pujaList;

}
