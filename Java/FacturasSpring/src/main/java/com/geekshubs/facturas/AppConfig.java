package com.geekshubs.facturas;

import com.geekshubs.facturas.model.Cliente;
import com.geekshubs.facturas.model.ItemFactura;
import com.geekshubs.facturas.model.Producto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class AppConfig {
    private static final int MAX_ITEMS = 5;
    private static final String[] NOMBRES_CLIENTES = {"José", "Luis", "Marta", "Juan", "Pablo"};
    private static final String[] APELLIDOS_CLIENTES = {"García", "Sánchez", "Martínez", "Gómez", "González"};
    private static final String[] NOMBRES_ITEMS = {"Bolígrafo", "Libreta", "Agenda", "Ordenador", "Mesa de escritorio", "Silla", "Tipp-ex"};

    @Bean(name = "itemsOficina")
    public List<ItemFactura> registrarItemsOficina() {
        List<ItemFactura> itemsOficina = new ArrayList<>();

        Producto producto;
        int cantidad;

        for(int i = 0; i < MAX_ITEMS; i++) {
            producto = generarProductoAleatorio();
            cantidad = generarNumeroAleatorio(1, 3);
            itemsOficina.add(new ItemFactura(producto, cantidad));
        }

        return itemsOficina;
    }

    @Bean(name = "clienteAleatorio")
    public Cliente generarClienteAleatorio() {
        String nombre = NOMBRES_CLIENTES[generarNumeroAleatorio(0, NOMBRES_CLIENTES.length-1)];
        String apellido = APELLIDOS_CLIENTES[generarNumeroAleatorio(0, APELLIDOS_CLIENTES.length-1)];
        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setApellido(apellido);
        return c;
    }

    public static Producto generarProductoAleatorio() {
        String nombre = NOMBRES_ITEMS[generarNumeroAleatorio(0, NOMBRES_ITEMS.length-1)];
        int precio = 0;
        if(nombre.equals("Ordenador") || nombre.equals("Mesa de escritorio") || nombre.equals("Silla")) // Productos "caros"
            precio = generarNumeroAleatorio(100, 800);
        else                                                                                            // Productos "baratos"
            precio = generarNumeroAleatorio(2, 5);

        return new Producto(nombre, precio);
    }

    public static int generarNumeroAleatorio(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}


