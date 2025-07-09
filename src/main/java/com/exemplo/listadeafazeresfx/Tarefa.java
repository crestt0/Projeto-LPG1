package com.exemplo.listadeafazeresfx;

public class Tarefa {
    private final int id;
    private final String descricao;

    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() { return descricao; }
}