package com.jomanager.jo_manager.Models;

import java.sql.*;

public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:olympics.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet executeQuery(String query) {
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getMedalsData(String medalType, int countryId, int athleteId, int limit) {
        ResultSet resultSet;

        if (medalType.equals("all")) {
            if (athleteId != 0) {
                resultSet = executeQuery("SELECT athlete_id, COUNT(athlete_id) as cnt FROM Medals GROUP BY athlete_id ORDER BY cnt DESC LIMIT '"+limit+"'");
            } else {
                resultSet = executeQuery("SELECT country_id, COUNT(athlete_id) as cnt FROM Medals GROUP BY country_id ORDER BY cnt DESC LIMIT '"+limit+"'");
            }
        } else {
            if (athleteId != 0) {
                resultSet = executeQuery("SELECT COUNT(*) as cnt FROM Medals WHERE medal_type='"+medalType+"' AND athlete_id='"+athleteId+"'");
            } else {
                resultSet = executeQuery("SELECT COUNT(*) as cnt FROM Medals WHERE medal_type='"+medalType+"' AND country_id='"+countryId+"'");
            }
        }

        return resultSet;
    }

    public ResultSet getAthletesData(String groupBy, int countryId, int sportId) {
       ResultSet resultSet;
       if (groupBy.equals("byCountry")) {
           resultSet = executeQuery("SELECT * FROM Athletes WHERE country_id='"+countryId+"'");
       } else {
           resultSet = executeQuery("SELECT * FROM Athletes WHERE sport_id='"+sportId+"'");
       }

       return resultSet;
    }

    public ResultSet getAthleteById(int athleteId) {
        return executeQuery("SELECT * FROM Athletes WHERE athlete_id='"+athleteId+"'");
    }

    public String getCountryName(int countryId) throws SQLException {
        return executeQuery("SELECT name FROM Countries WHERE country_id='"+countryId+"'").getString("name");
    }



}
