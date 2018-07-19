/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pydee.jsonexample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author David
 */
public class JsonByExample {

    static public void main(String[] args) {
        DetallesVenta detalle1 = new DetallesVenta("Aceite", new BigDecimal("12.00"), new BigDecimal("120.0"), 10);
        DetallesVenta detalle2 = new DetallesVenta("Anticongelante", new BigDecimal("45.00"), new BigDecimal("90.0"), 2);
        DetallesVenta detalle3 = new DetallesVenta("Reparacion Rin", new BigDecimal("400.00"), new BigDecimal("400.0"), 1);
        ArrayList<DetallesVenta> detalles = new ArrayList<>();
        Venta venta = new Venta();

        Calendar calendario = Calendar.getInstance();
        Date fecha = new Date(calendario.getTimeInMillis());

        detalles.add(detalle1);
        detalles.add(detalle2);
        detalles.add(detalle3);

        venta.setCliente("CLIENTE DE PRUEBA");
        venta.setFecha(fecha);
        venta.setDetalle(detalles);

        // Creamos el objeto Gson que se encargara de las conversiones
        Gson gson = new Gson();

        // Convertimos un objecto sencillo a JSON
        System.out.println(gson.toJson(detalle1));
        System.out.println();

        // Convertimos un Objecto que contiene un arreglo de objetos
        System.out.println(gson.toJson(venta));
        System.out.println();

        // Convertimos un JSON sencillo a un objeto adecuado
        // Note que convertimos las comillas dobles a comillas simples, esto es necesario debido
        // a que las comillas dobles son caracteres reservados en Java
        String jsonSimple = "{'producto':'Limpiaparabrisas','importe':100.0,'precioUnitario':50.00,'cantidad':2}";
        DetallesVenta detalles4 = gson.fromJson(jsonSimple, DetallesVenta.class);

        System.out.println("Producto: " + detalles4.getProducto());
        System.out.println("Importe: " + detalles4.getImporte());
        System.out.println("Precio Unitario: " + detalles4.getPrecioUnitario());
        System.out.println();

        // Convertimos un JSON que incluye un arreglo de objetos a un objeto
        String jsonComplejo = "{'fecha':'Jul 9, 2018 3:37:49 PM','cliente':'LIMPIEZA Y ASEO PROFESIONAL','detalle':[{'producto':'Cloro','importe':150.0,'precioUnitario':15.00,'cantidad':10},{'producto':'Escoba','importe':30,'precioUnitario':30.00,'cantidad':1},{'producto':'Aromatizante ambiental','importe':100.0,'precioUnitario':10.00,'cantidad':10}]}";
        Venta venta2 = gson.fromJson(jsonComplejo, Venta.class);

        System.out.println("Fecha: " + venta2.getFecha());
        System.out.println("Cliente: " + venta2.getCliente());
        System.out.println();

        for (DetallesVenta detalle : venta2.getDetalle()) {
            System.out.println("Producto: " + detalle.getProducto());
            System.out.println("Importe: " + detalle.getImporte());
            System.out.println("Precio Unitario: " + detalle.getPrecioUnitario());
            System.out.println();
        }

        // Convertimos un arreglo suelto a JSON
        String jsonArray = gson.toJson(detalles);
        System.out.println(jsonArray);

        // Convertir un array Json a ArrayList

        // Creaos el tipo que represente el arreglo
        Type listType = new TypeToken<ArrayList<DetallesVenta>>(){}.getType();
        ArrayList<DetallesVenta> arrayDeJson = gson.fromJson(jsonArray, listType);

        System.out.println("\n");

        for(DetallesVenta detalle : arrayDeJson) {
            System.out.println(detalle.getProducto());
        }

    }
}
