package com.formacionbdi.springboot.app.productos.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.models.entity.DetalleVentaPos;
import com.formacionbdi.springboot.app.productos.models.entity.EstadVentas;
import com.formacionbdi.springboot.app.productos.models.entity.ImagenCliente;
import com.formacionbdi.springboot.app.productos.models.entity.ProductoPos;
import com.formacionbdi.springboot.app.productos.models.entity.VentaPos;
import com.formacionbdi.springboot.app.productos.models.service.IDetalleVentaServicePos;
import com.formacionbdi.springboot.app.productos.models.service.IEstadisticaVentaMes;
import com.formacionbdi.springboot.app.productos.models.service.IImagenClientes;
import com.formacionbdi.springboot.app.productos.models.service.IProductoServicePos;
import com.formacionbdi.springboot.app.productos.models.service.IVentaServicePos;

@CrossOrigin(origins = "http://localhost:80/") 
@RestController
public class ProductoController {
	
	@Autowired
	private IProductoServicePos productoServicePos;
	
	@Autowired
	private IVentaServicePos ventaServicePos;
	
	@Autowired
	private IDetalleVentaServicePos detalleventaServicePos;
	
	@Autowired
	private IEstadisticaVentaMes estadisticaVentaMes;
	
	@Autowired
	private IImagenClientes imagenClienteService;

	@GetMapping("/listar-productos")
	public ResponseEntity<List<ProductoPos>> listarProductos(){
		System.out.println("/listarProductos");
		return ResponseEntity.ok(productoServicePos.findAllProductsPos());
	}
	
	@GetMapping("/consultar-producto/{idProd}")
	public ResponseEntity<ProductoPos> consultarProductos(@PathVariable int idProd){
		System.out.println("/consultar-producto");
		return ResponseEntity.ok(productoServicePos.consultarId(idProd));
	}

	@PostMapping("/agregar-producto")
	public ResponseEntity<Object> addProductoPos(@RequestBody ProductoPos producto){
		return ResponseEntity.ok(
			 productoServicePos.agregarProductoPos(producto.getNombreproducto(), 
					 							   producto.getPrecio(),
					 							   producto.getFechacreacion(),
					 							   producto.getEstado(),
					 							   producto.getNroposicion(),
					 							   producto.getCantidaddisponible(),
					 							   null));
	}
	
	@PostMapping("/actualizar-producto")
	public ResponseEntity<Object> updateProductoPos(@RequestBody ProductoPos producto){
		return ResponseEntity.ok(
			 productoServicePos.actualizarProductoPos(
					 							   producto.getIdproductos(),
					 							   producto.getNombreproducto(), 
					 							   producto.getPrecio(),
					 							   producto.getFechacreacion(),
					 							   producto.getEstado(),
					 							   producto.getNroposicion(),
					 							   producto.getCantidaddisponible(),
					 							   null));
	}
	
	@GetMapping("/listar-ventas")
	public ResponseEntity<List<VentaPos>> listarVentas(){
		System.out.println("/listar-ventas");
		return ResponseEntity.ok(ventaServicePos.listarVentasPos());
	}
	
	@GetMapping("/consultar-ventas/{sfecha}")
	public ResponseEntity<List<VentaPos>> consultarProductos(@PathVariable String sfecha){
		System.out.println("/consultar-ventas");
		return ResponseEntity.ok(ventaServicePos.consultarVenta(sfecha));
	}
	
	

	
	
	@PostMapping("/insertar-venta")
	public ResponseEntity<Object> addVentaPos(@RequestBody VentaPos ventapos){
		return ResponseEntity.ok(
				ventaServicePos.insertaVentaPos(
					 ventapos.getIdventa(),
					 ventapos.getFechaventa(),
					 ventapos.getSecuencia(),
					 ventapos.getNroboleta(),
					 ventapos.getTotalarticulos(),
					 ventapos.getSubtotalventa(),
					 ventapos.getIva(),
					 ventapos.getTotalimporte(),
					 ventapos.getTipopago(),
					 ventapos.getComisiontbk(),
					 ventapos.getComunicacionpos(),
					 ventapos.getEstadotransbank(),
					 ventapos.getTrazastattransbk(), 
					 ventapos.getLongmsgtransbank()
            ));
	}
	
	
	@PostMapping("/insertar-detalleventa")
	public ResponseEntity<Object> addDetalleVentaPos(@RequestBody DetalleVentaPos detalleventapos){
		return ResponseEntity.ok(
				detalleventaServicePos.insertarDetalleVentaPos(
						detalleventapos.getIdventa(),
						detalleventapos.getNombreproducto(),
						detalleventapos.getIdproducto(),
						detalleventapos.getCantidad(),
						detalleventapos.getPreciosubtotal()
            ));
	}
	
	
	@GetMapping("/cons-estadis-mensual/{sfecha}")
	public ResponseEntity<List<EstadVentas>> consultarEstadisticaMes(@PathVariable String sfecha){
		System.out.println("/cons-estadis-mensual");
		return ResponseEntity.ok(estadisticaVentaMes.consultarEstadisticaMes(sfecha));
	}
	
	@GetMapping("/cons-estadis-mensual-por-prod/{sfecha}/{sproducto}")
	public ResponseEntity<List<EstadVentas>> consultarEstadisticaMesProd(@PathVariable String sfecha, @PathVariable String sproducto){
		System.out.println("/cons-estadis-mensual");
		return ResponseEntity.ok(estadisticaVentaMes.consultarEstadisticaMesProd(sfecha, sproducto));
	}
	
	
	@GetMapping("/cons-estadis-mensual-masvendido-cant/{sfecha}")
	public ResponseEntity<List<EstadVentas>> consultarEstadisticaMesMasVendidoCant(@PathVariable String sfecha){
		System.out.println("/cons-estadis-mensual");
		return ResponseEntity.ok(estadisticaVentaMes.consultarEstadisticaMesMasVendidoCant(sfecha));
	}
	
	@GetMapping("/cons-estadis-mensual-masvendido-monto/{sfecha}")
	public ResponseEntity<List<EstadVentas>> consultarEstadisticaMesMasVendidoMonto(@PathVariable String sfecha){
		System.out.println("/cons-estadis-mensual");
		return ResponseEntity.ok(estadisticaVentaMes.consultarEstadisticaMesMasVendidoMonto(sfecha));
	}
	
	
	@GetMapping("/consultar-imagencli/{sfecha}")
	public ResponseEntity<List<ImagenCliente>> consultarImagenCliente2(@PathVariable String sfecha){
		System.out.println("/consultar-imagencli/{sfecha}");
		return ResponseEntity.ok(imagenClienteService.consultarImagenCliente2(sfecha));
	}
	
	@GetMapping("/consultar-imagencli")
	public ResponseEntity<List<ImagenCliente>> consultarImagenCliente(){
		System.out.println("/consultar-imagencli");
		return ResponseEntity.ok(imagenClienteService.consultarImagenCliente());
	}
	
	
	@PostMapping("/insertar-imagencli")
	public ResponseEntity<Object> insertaImagenCliente(@RequestBody ImagenCliente imagenCliente){
		System.out.println("/insertar-imagencli");  //+ imagenCliente.getImagen());
		return ResponseEntity.ok(
				imagenClienteService.insertaImagenCliente(
						imagenCliente.getIdventa(),
						imagenCliente.getImagen(),
						imagenCliente.getRutaimagen()));
					 
	}
	   
	
	
	
}
