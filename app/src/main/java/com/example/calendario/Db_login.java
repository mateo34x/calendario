package com.example.calendario;

public class Db_login {

    //al momento de declarar una variable para un constructor se debe evitar colocar:
    //variables privadas por ejemplo: private String nombre;
    //Ya que al momento de acceder a ellas desde otra clase no podremos hacerlo ya que estan privadas


    //Debemos también tener en cuenta escribir variables sin guiones bajos y crear el constructor de
    //automática para evitar errores a la hora de establecer los valores y si se crean manual, verifi
    //car que los valores sean iguales


    //No podía obtener los valores por la razón de que esos errores estaban aquí

    String uid, email, nombre, tipo,imgUrl;


    public Db_login() {
    }

    public Db_login(String uid, String email, String nombre, String tipo,String imgUrl) {
        this.uid = uid;
        this.email = email;
        this.nombre = nombre;
        this.tipo = tipo;
        this.imgUrl = imgUrl;
    }


    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String toString() {
        return "Db_login{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}