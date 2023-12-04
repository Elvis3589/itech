package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoEvento;
import com.example.entidades.Eventos;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

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
            String sql = "SELECT * FROM eventos WHERE fecha >= CURRENT_DATE";

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

    @Override
    public List<Eventos> obtenerEventosActivosPorUsuario(int idUsuario) {
        List<Eventos> eventos = new ArrayList<>();

        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT * FROM eventos WHERE id_usuario = ? AND fecha >= CURRENT_DATE";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    @Override
    public Eventos obtenerDetallesEvento(int idEvento) {
        Eventos evento = null;

        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT * FROM eventos WHERE id_evento = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idEvento);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        evento = new Eventos(
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
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evento;
    }

    @Override
    public boolean registrarReserva(int idEvento, int idUsuario) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "INSERT INTO reservas (id_evento, id_usuario) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idEvento);
                preparedStatement.setInt(2, idUsuario);

                int filasAfectadas = preparedStatement.executeUpdate();

                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Eventos> obtenerEventosReservadosPorUsuario(int idUsuario) {
        List<Eventos> eventosReservados = new ArrayList<>();

        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT e.* FROM eventos e "
                    + "INNER JOIN reservas r ON e.id_evento = r.id_evento "
                    + "WHERE r.id_usuario = ? AND e.fecha >= CURRENT_DATE";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
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

                        eventosReservados.add(evento);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventosReservados;
    }

    @Override
    public List<Eventos> obtenerEventosFinalizadosPorUsuario(int idUsuario) {
        List<Eventos> eventosFinalizados = new ArrayList<>();

        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT * FROM eventos "
                    + "WHERE (id_usuario = ? OR id_evento IN (SELECT id_evento FROM reservas WHERE id_usuario = ?)) "
                    + "AND fecha < CURRENT_DATE";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);
                preparedStatement.setInt(2, idUsuario);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
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

                        eventosFinalizados.add(evento);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventosFinalizados;
    }

    @Override
    public List<Eventos> obtenerEventosPremium() {
        List<Eventos> eventosPremium = new ArrayList<>();

        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT e.* FROM eventos e "
                    + "INNER JOIN suscripciones_premium sp ON e.id_usuario = sp.id_usuario "
                    + "WHERE e.fecha >= CURRENT_DATE AND sp.fecha_fin >= CURRENT_DATE "
                    + "ORDER BY RAND() LIMIT 4";

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

                    eventosPremium.add(evento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventosPremium;
    }

    @Override
    public int obtenerCantidadReservasPorEvento(int idEvento) {
        int cantidadReservas = 0;

        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT COUNT(*) FROM reservas WHERE id_evento = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idEvento);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cantidadReservas = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cantidadReservas;
    }

    @Override
    public JasperPrint reporteEvento(int id_evento, int user) {
        try (Connection con = conexion.Conectar()) {
            InputStream is = getClass().getResourceAsStream("/reporte/evento3.jasper");
            if (is == null) {
                System.out.println("El recurso no se pudo cargar.");
                return null;
            }
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id_evento);
            parametros.put("user", user);
            JasperReport jr = (JasperReport) JRLoader.loadObject(is);
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            return jp;

        } catch (SQLException ex) {
            Logger.getLogger(DaoEventoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro en primer catch" + ex);
        } catch (JRException ex) {
            Logger.getLogger(DaoEventoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro en segundo catch" + ex);
        }

        return null;
    }

}
