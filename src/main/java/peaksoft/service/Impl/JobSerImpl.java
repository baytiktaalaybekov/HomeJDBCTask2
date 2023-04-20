package peaksoft.service.Impl;

import peaksoft.dao.Impl.JobDaoImpl;
import peaksoft.dao.JobDao;
import peaksoft.model.Job;
import peaksoft.service.JobSer;

import java.util.List;

public class JobSerImpl implements JobSer {

    JobDao jobDao = new JobDaoImpl();

    public void createJobTable() {
        jobDao.createJobTable();
    }

    public void addJob(Job job) {
        jobDao.addJob(job);
    }

    public Job getJobById(Long jobId) {
        return jobDao.getJobById(jobId);
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();
    }
}
