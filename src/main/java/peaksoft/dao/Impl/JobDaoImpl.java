package peaksoft.dao.Impl;

import peaksoft.config.Util;
import peaksoft.dao.JobDao;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {

    private Connection connection;

    public JobDaoImpl() {
        this.connection = Util.getConnection();
    }

    @Override
    public void createJobTable() {
        String sql = """
                create table if not exists job(
                id serial primary key,
                position varchar,
                profession varchar,
                description varchar,
                experience int)""";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully created job!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addJob(Job job) {
        String sql = """
                Insert into job(position, profession, description, experience)Values(?,?,?,?)""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.execute();
            System.out.println("Successfully addJob!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Job getJobById(Long jobId) {
        Job job = new Job();
        String sql = """
                Select * from job where Id = ?""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               job.setId(resultSet.getLong("id"));
               job.setPosition(resultSet.getString("position"));
               job.setProfession(resultSet.getString("profession"));
               job.setDescription(resultSet.getString("description"));
               job.setExperience(resultSet.getInt("experience"));
                System.out.println("Successfully get Job !");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();

        switch (ascOrDesc) {
            case "asc":
                String sql = """
                select * from job order by experience ;
                """;
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        Job job = new Job();
                        job.setId(resultSet.getLong("id"));
                        job.setPosition(resultSet.getString("position"));
                        job.setProfession(resultSet.getString("profession"));
                        job.setDescription(resultSet.getString("description"));
                        job.setExperience(resultSet.getInt("experience"));
                        jobs.add(job);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "desc":
                String query = """
                         select * from job order by experience desc;
                        """;
                try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        Job job = new Job();
                        job.setId(resultSet.getLong("id"));
                        job.setPosition(resultSet.getString("position"));
                        job.setProfession(resultSet.getString("profession"));
                        job.setDescription(resultSet.getString("description"));
                        job.setExperience(resultSet.getInt("experience"));
                        jobs.add(job);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println(" asc or desc !");
        } return jobs;



    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();
        String sql = """
                select from job as jobs join employee e on jobs.id=e.jobId where jobs.id=?;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
            System.out.println("Successfully get Job by Employee!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String sql = """
                alter table job drop column description;
                """;
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Successfully delete Description!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
