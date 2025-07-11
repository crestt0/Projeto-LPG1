package com.exemplo.listadeafazeresfx;

public class Tarefa {
    private final int id;
    private final String descricao;
    private boolean concluida; // Novo campo

    public Tarefa(int id, String descricao, boolean concluida) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = concluida;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public boolean isConcluida() { return concluida; }

    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    @Override
    public String toString() { return descricao; }
}