package es.taw.aliebay.dto;

import java.util.List;

public class VendedorDTO {
    private UsuarioDTO usuario;
    private List<ProductoDTO> productoList;

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
}
