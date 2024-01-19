package com.sitema.helpdeskback.enums;

public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
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
public static Status toEnum(Integer cod){
        if (cod == null){
            return  null;
        }
        for (Status x: Status.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw  new IllegalArgumentException("Status inválido");
}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Perfil{");
        sb.append("codigo=").append(codigo);
        sb.append('}');
        return sb.toString();
    }
}
