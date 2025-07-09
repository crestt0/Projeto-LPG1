package com.exemplo.listadeafazeresfx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorBancoDeDados {
    private static final String URL_BANCO = "jdbc:sqlite:listadeafazeres.db";

    public static void inicializarBancoDeDados() {
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT NOT NULL);";
        try (Connection c = DriverManager.getConnection(URL_BANCO); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }

    public static List<Tarefa> buscarTodasAsTarefas() {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT id, descricao FROM tarefas";
        try (Connection c = DriverManager.getConnection(URL_BANCO); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                lista.add(new Tarefa(r.getInt("id"), r.getString("descricao")));
            }
        } catch (SQLException e) { System.out.println(e.getMessage()); }
        return lista;
    }

    public static void adicionarTarefa(String descricao) {
        String sql = "INSERT INTO tarefas(descricao) VALUES(?)";
        try (Connection c = DriverManager.getConnection(URL_BANCO); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, descricao);
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }

    public static void removerTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection c = DriverManager.getConnection(URL_BANCO); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }
}