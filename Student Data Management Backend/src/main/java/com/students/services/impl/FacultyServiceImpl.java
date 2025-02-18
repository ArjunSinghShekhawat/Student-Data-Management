package com.students.services.impl;

import com.students.exceptions.FacultyException;
import com.students.exceptions.StudentException;
import com.students.jwt.JwtProvider;
import com.students.models.Faculty;
import com.students.models.User;
import com.students.repositories.FacultyRepository;
import com.students.repositories.UserRepository;
import com.students.services.FacultyService;
import com.students.utility.Validate;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public Faculty findByEmail(String email) throws FacultyException {
        Faculty faculty = facultyRepository.findByEmail(email);

        if(faculty==null){
            throw new FacultyException("Faculty not found with email "+email);
        }
        return facultyRepository.findByEmail(email);
    }

    @Override
    public Faculty findById(ObjectId id) throws FacultyException {
        return facultyRepository.findById(id).orElseThrow(()->new FacultyException("Faculty not found with id "+id));
    }

    @Override
    public User findByRollNumberStudent(String rollNumber) throws StudentException {
        User student = userRepository.findByRollNumber(rollNumber);

        if(student==null){
            throw new StudentException("student not found with roll number "+rollNumber);
        }
        return student;
    }
    @Override
    public User updateStudentInformation(String rollNumber, User user) throws StudentException {

       User student =  findByRollNumberStudent(rollNumber);
        //update user
        if(user.getFullName()!=null){
            student.setFullName(Validate.validate(user.getFullName()));
        }
        if(user.getEmail()!=null){
            student.setEmail(Validate.validate(user.getEmail()));
        }
        if(user.getAddress()!=null && user.getAddress().getCity()!=null && user.getAddress().getState()!=null
                && user.getAddress().getStreet()!=null && user.getAddress().getPlotNumber()!=null
                && user.getAddress().getZipCode()!=null){
            student.getAddress().setState(user.getAddress().getState());
            student.getAddress().setCity(user.getAddress().getState());
            student.getAddress().setStreet(user.getAddress().getState());
            student.getAddress().setPlotNumber(user.getAddress().getState());
            student.getAddress().setZipCode(user.getAddress().getState());
        }
        if(user.getContactNumber()!=null){
            student.setContactNumber(user.getContactNumber());
        }
        if(user.getRollNumber()!=null){
            student.setRollNumber(user.getRollNumber());
        }
        if(user.getDateOfBirth()!=null){
            student.setDateOfBirth(user.getDateOfBirth());
        }
        if(user.getImageUrl()!=null){
            student.setImageUrl(user.getImageUrl());
        }
        if(user.getGuardian()!=null && user.getGuardian().getGuardianName()!=null && user.getGuardian().getContactNumber()!=null
        && user.getGuardian().getRelationship()!=null && user.getGuardian().getOccupation()!=null){
            student.getGuardian().setGuardianName(user.getGuardian().getGuardianName());
            student.getGuardian().setContactNumber(user.getGuardian().getContactNumber());
            student.getGuardian().setOccupation(user.getGuardian().getOccupation());
            student.getGuardian().setRelationship(user.getGuardian().getRelationship());
        }

        if(user.getAcademicDetails()!=null && user.getAcademicDetails().getBranch()!=null && user.getAcademicDetails().getCgpa()!=null
        && user.getAcademicDetails().getSgpa()!=null && user.getAcademicDetails().getCourse()!=null && user.getAcademicDetails().getMonthlyAttendance()!=null
        && user.getAcademicDetails().getNumberOfBacklog()!=0 && user.getAcademicDetails().getSemesterRecords()!=null && user.getAcademicDetails().getYearOfAdmission()!=null){

            student.getAcademicDetails().setCourse(user.getAcademicDetails().getCourse());
            student.getAcademicDetails().setBranch(user.getAcademicDetails().getBranch());
            student.getAcademicDetails().setYearOfAdmission(user.getAcademicDetails().getYearOfAdmission());
            student.getAcademicDetails().setSemesterRecords(user.getAcademicDetails().getSemesterRecords());
            student.getAcademicDetails().setCgpa(user.getAcademicDetails().getCgpa());
            student.getAcademicDetails().setSgpa(user.getAcademicDetails().getSgpa());
            student.getAcademicDetails().setMonthlyAttendance(user.getAcademicDetails().getMonthlyAttendance());
            student.getAcademicDetails().setNumberOfBacklog(user.getAcademicDetails().getNumberOfBacklog());

        }
        return userRepository.save(student);
    }

    @Override
    public User createNewUserEntry(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllStudents() {
        return userRepository.findAll();
    }

    @Override
    public Faculty updateSelfInformation(String jwt,Faculty faculty) throws FacultyException {

        String email =  jwtProvider.getEmailFromJwtToken(jwt);
        Faculty existFaculty = facultyRepository.findByEmail(email);

        if (existFaculty != null) {

            updateIfNotNull(faculty.getFullName(), existFaculty::setFullName);
            updateIfNotNull(faculty.getGender(), existFaculty::setGender);
            updateIfNotNull(faculty.getDob(), existFaculty::setDob);
            updateIfNotNull(faculty.getEmail(), existFaculty::setEmail);
            updateIfNotNull(faculty.getPassword(), existFaculty::setPassword);
            updateIfNotNull(faculty.getContactNumber(), existFaculty::setContactNumber);
            updateIfNotNull(faculty.getPlotNumber(), existFaculty::setPlotNumber);
            updateIfNotNull(faculty.getStreet(), existFaculty::setStreet);
            updateIfNotNull(faculty.getExperience(), existFaculty::setExperience);
            updateIfNotNull(faculty.getQualification(), existFaculty::setQualification);
            updateIfNotNull(faculty.getRole(), existFaculty::setRole);
            updateIfNotNull(faculty.getImageUrl(), existFaculty::setImageUrl);

            // Save the updated faculty information
            return facultyRepository.save(existFaculty);
        }
        throw new FacultyException("Faculty not found with email "+email);
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
