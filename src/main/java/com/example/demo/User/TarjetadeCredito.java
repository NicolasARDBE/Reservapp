package com.example.demo.User;

import java.util.Date;

public class TarjetadeCredito {
    private long numero;
    private Date fecha_de_vencimiento;
    private int cvv;

    public TarjetadeCredito(long numero,Date fecha_de_vencimiento,int cvv){
        this.numero = numero;
        this.fecha_de_vencimiento = fecha_de_vencimiento;
        this.cvv = cvv;
    }
}
