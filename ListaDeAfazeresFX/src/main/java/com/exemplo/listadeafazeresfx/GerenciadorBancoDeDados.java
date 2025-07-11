package com.exemplo.listadeafazeresfx;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorBancoDeDados {

    // --- MUDANÇA CRUCIAL AQUI ---
    // Pega o caminho da pasta 'home' do usuário (ex: C:\Users\Rodrigo)
    private static final String PASTA_HOME = System.getProperty("user.home");
    // Garante que o separador de pastas seja o correto para o sistema operacional (\ no Windows, / no Linux)
    private static final String SEPARADOR = File.separator;
    // Define o caminho completo e seguro para o banco de dados
    private static final String URL_BANCO = "jdbc:sqlite:" + PASTA_HOME + SEPARADOR + "listadeafazeres.db";


    public static void inicializarBancoDeDados() {
        // O resto do código permanece o mesmo
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT NOT NULL, concluida BOOLEAN NOT NULL CHECK (concluida IN (0, 1)));";
        try (Connection c = DriverManager.getConnection(URL_BANCO); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Tarefa> buscarTodasAsTarefas() {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT id, descricao, concluida FROM tarefas";
        try (Connection c = DriverManager.getConnection(URL_BANCO); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                lista.add(new Tarefa(r.getInt("id"), r.getString("descricao"), r.getBoolean("concluida")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void adicionarTarefa(String descricao) {
        String sql = "INSERT INTO tarefas(descricao, concluida) VALUES(?, ?)";
        try (Connection c = DriverManager.getConnection(URL_BANCO); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, descricao);
            ps.setBoolean(2, false);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removerTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL_BANCO); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarStatusTarefa(int id, boolean concluida) {
        String sql = "UPDATE tarefas SET concluida = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL_BANCO);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, concluida);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}