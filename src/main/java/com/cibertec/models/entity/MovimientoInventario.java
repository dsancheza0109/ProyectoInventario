package com.cibertec.models.entity;

import jakarta.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="movimientoinventario")

@NamedStoredProcedureQuery(
	    name = "MovimientoInventario.findById",
	    procedureName = "GetMovimientoInventarioById",
	    resultClasses = MovimientoInventario.class,
	    parameters = {
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nroMovimiento", type = Integer.class)
	    }
)
public class MovimientoInventario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private int id;

    @Column(name = "codigo_producto", length = 100)
    @NotEmpty
    private String codigoProducto;

    @Column(name = "tipo", length = 50)
    @NotEmpty
    private String tipo;

    @Column(name = "cantidad")
    @NotNull
    private Integer cantidad;

    @Column(name = "fecha")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // Constructor sin argumentos
    public MovimientoInventario() {
    }

    // Constructor con argumentos
    public MovimientoInventario(@NotEmpty String codigoProducto, @NotEmpty String tipo, @NotNull Integer cantidad,
                                @NotNull Date fecha, String observaciones, Usuario usuario) {
        this.codigoProducto = codigoProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.usuario = usuario;
    }

    public MovimientoInventario(int id, @NotEmpty String codigoProducto, @NotEmpty String tipo,
                                @NotNull Integer cantidad, @NotNull Date fecha, String observaciones, Usuario usuario) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.usuario = usuario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "MovimientoInventario [id=" + id + ", codigoProducto=" + codigoProducto + ", tipo=" + tipo
                + ", cantidad=" + cantidad + ", fecha=" + fecha + ", observaciones=" + observaciones + "]";
    }
}
