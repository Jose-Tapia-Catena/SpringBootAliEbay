package es.taw.aliebay.dto;

import java.util.List;

public class CategoriaDTO {
    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    private String idCategoria;

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
        this.productoList = productoList;
    }

    private List<ProductoDTO> productoList;

}
