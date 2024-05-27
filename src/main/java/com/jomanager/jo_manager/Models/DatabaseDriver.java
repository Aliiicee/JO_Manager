package com.jomanager.jo_manager.Models;

import java.sql.*;
import java.time.LocalDate;

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

    public String getCountryName(int countryId) {
        String countryName = null;
        try {
            countryName = executeQuery("SELECT name FROM Countries WHERE country_id='"+countryId+"'").getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryName;
    }
    public String getSportName(int sportId) {
        String sportName = null;
        try {
            sportName = executeQuery("SELECT name FROM Sports WHERE sport_id='"+sportId+"'").getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sportName;
    }

    public Integer getCountryId(String name) {
        Integer country_id = null;
        try {
            country_id = executeQuery("SELECT country_id FROM Countries WHERE name='"+name+"'").getInt("country_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country_id;
    }

    public ResultSet getCountries() {
        return executeQuery("SELECT * FROM Countries");
    }

    public ResultSet getSports() {
        return executeQuery("SELECT * FROM Sports");
    }
    public ResultSet getAthletes() {return executeQuery("SELECT * FROM Athletes");}
    public ResultSet getEvents() {return executeQuery("SELECT * FROM Events");}

    public Integer getSportId(String name) {
        Integer sport_id = null;
        try {
            sport_id = executeQuery("SELECT sport_id FROM Sports WHERE name='"+name+"'").getInt("sport_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sport_id;
    }

    public Integer getAthleteId(String fName, String lName) {
        Integer athlete_id = null;
        try {
            athlete_id = executeQuery("SELECT athlete_id FROM Athletes WHERE first_name='"+fName+"' AND last_name='"+lName+"'").getInt("athlete_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return athlete_id;
    }

    public Integer getEventId(String date) {
        Integer event_id = null;
        try {
            event_id = executeQuery("SELECT event_id FROM Events WHERE date='"+date+"'").getInt("event_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event_id;
    }

    public void addCountry(String name) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Countries (name) " +
                    "VALUES ('"+name+"')"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAthlete(String fName, String lName, LocalDate birthdate, String gender, Integer height, Double weight, Integer countryId, Integer sportId) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                            "Athletes (first_name, last_name, birthdate, gender, height, weight, country_id, sport_id) " +
                            "VALUES ('"+fName+"', '"+lName+"','"+birthdate.toString()+"','"+gender+"','"+height+"','"+weight+"','"+countryId+"','"+sportId+"')"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSport(String name) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Sports (name) " +
                    "VALUES ('"+name+"')"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMedal(String medalType, Integer athleteId, Integer eventId, Integer countryId) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Medals (medal_type, athlete_id, event_id, country_id) " +
                    "VALUES ('"+medalType+"', '"+athleteId+"','"+eventId+"','"+countryId+"')"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEvent(Integer sportId, String date) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Events (sport_id, date) " +
                    "VALUES ('"+sportId+"', '"+date+"')"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
