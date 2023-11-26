package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoPremium;
import com.example.entidades.Premium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoPremiumImpl implements DaoPremium {

    private final Conexion conexion;
    private String mensaje;

    public DaoPremiumImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean registrarSuscripcionPremium(Premium premium) {
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
}
