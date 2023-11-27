package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoPremium;
import com.example.entidades.Premium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoPremiumImpl implements DaoPremium {

    private final Conexion conexion;
    private String mensaje;

    public DaoPremiumImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean registrarSuscripcionPremium(Premium premium) {
        if (tieneSuscripcionPremium(premium.getIdUsuario())) {
            mensaje = "El usuario ya tiene una suscripción activa.";
            return false;
        }
        try (Connection connection = conexion.Conectar()) {
            String sql = "INSERT INTO suscripciones_premium (id_usuario, fecha_inicio, fecha_fin) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, premium.getIdUsuario());
                preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(premium.getFechaInicio()));
                preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(premium.getFechaFin()));

                int filasAfectadas = preparedStatement.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            mensaje = "Error al registrar la suscripción premium: " + e.getMessage();
            return false;
        }
    }

    @Override
    public String getMensaje() {
        return mensaje;
    }

    @Override
    public boolean tieneSuscripcionPremium(int idUsuario) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT COUNT(*) FROM suscripciones_premium WHERE id_usuario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
        }

        return false;
    }
}
