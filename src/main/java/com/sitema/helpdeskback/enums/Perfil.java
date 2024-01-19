package com.sitema.helpdeskback.enums;

public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
public static Perfil toEnum(Integer cod){
        if (cod == null){
            return  null;
        }
        for (Perfil x: Perfil.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw  new IllegalArgumentException("Prefil inválido");
}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Perfil{");
        sb.append("codigo=").append(codigo);
        sb.append('}');
        return sb.toString();
    }
}
