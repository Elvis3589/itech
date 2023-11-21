package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoEvento;
import com.example.entidades.Eventos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoEventoImpl implements DaoEvento {

    private final Conexion conexion;

    public DaoEventoImpl() {
        conexion = new Conexion();
    }

    @Override
    public boolean registrarEvento(Eventos evento) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "INSERT INTO eventos (id_usuario, nombre, apellidos, email, nombre_evento, "
                    + "lugar, hora, fecha, celular, descripcion, max_cantidad, imagen_evento) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, evento.getIdUsuario());
                preparedStatement.setString(2, evento.getNombre());
                preparedStatement.setString(3, evento.getApellidos());
                preparedStatement.setString(4, evento.getEmail());
                preparedStatement.setString(5, evento.getNombreEvento());
                preparedStatement.setString(6, evento.getLugar());
                preparedStatement.setString(7, evento.getHora());
                preparedStatement.setDate(8, evento.getFecha());
                preparedStatement.setString(9, evento.getCelular());
                preparedStatement.setString(10, evento.getDescripcion());
                preparedStatement.setInt(11, evento.getMaxCantidad());
                preparedStatement.setBytes(12, evento.getImagenEvento());

                int filasAfectadas = preparedStatement.executeUpdate();

                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Eventos> obtenerEventos() {
        List<Eventos> eventos = new ArrayList<>();

        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT * FROM eventos";

            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Eventos evento = new Eventos(
                            resultSet.getInt("id_evento"),
                            resultSet.getInt("id_usuario"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellidos"),
                            resultSet.getString("email"),
                            resultSet.getString("nombre_evento"),
                            resultSet.getString("lugar"),
                            resultSet.getString("hora"),
                            resultSet.getDate("fecha"),
                            resultSet.getString("celular"),
                            resultSet.getString("descripcion"),
                            resultSet.getInt("max_cantidad"),
                            resultSet.getBytes("imagen_evento")
                    );

                    eventos.add(evento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

}
