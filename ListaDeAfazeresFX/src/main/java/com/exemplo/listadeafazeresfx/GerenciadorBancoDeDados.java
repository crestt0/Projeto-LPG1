package com.exemplo.listadeafazeresfx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorBancoDeDados {
    private static final String URL_BANCO = "jdbc:sqlite:listadeafazeres.db";

    //Cria a tabela 'tarefas' com a nova coluna 'concluida' se ela não existir.
    public static void inicializarBancoDeDados() {
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT NOT NULL, concluida BOOLEAN NOT NULL CHECK (concluida IN (0, 1)));";
        try (Connection c = DriverManager.getConnection(URL_BANCO); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Busca todas as tarefas, incluindo o status 'concluida'
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

    //Adiciona uma nova tarefa, definindo 'concluida' como falso por padrão
    public static void adicionarTarefa(String descricao) {
        String sql = "INSERT INTO tarefas(descricao, concluida) VALUES(?, ?)";
        try (Connection c = DriverManager.getConnection(URL_BANCO); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, descricao);
            ps.setBoolean(2, false); // Novas tarefas começam como não concluídas
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        //Remove uma tarefa pelo ID.
         public static void removerTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL_BANCO); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Atualiza o status de uma tarefa no banco.
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