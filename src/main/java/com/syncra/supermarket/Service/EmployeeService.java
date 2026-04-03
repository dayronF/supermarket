package com.syncra.supermarket.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.syncra.supermarket.Dto.Employee.EmployeeRequest;
import com.syncra.supermarket.Entity.EmployeeEntity;
import com.syncra.supermarket.Entity.EmployeeEntity.Post;
import com.syncra.supermarket.Repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                    "El empleado no se encuentra por ese id: " + id));
    }

    public EmployeeEntity create(EmployeeRequest request) {
        if (employeeRepository.existsByCc(request.getCc())) {
            throw new IllegalArgumentException(
                    "Ya existe un empleado con ese tipo de cédula: " + request.getCc());
        }

        EmployeeEntity employee = new EmployeeEntity();
        employee.setCc(request.getCc());
        employee.setPost(Post.valueOf(request.getPost()));
        employee.setName(request.getName());
        employee.setEntryDate(request.getEntryDate());
        employee.setSalary(request.getSalary());

        return employeeRepository.save(employee);
    }

    public EmployeeEntity put(Integer id, EmployeeRequest request) {
        EmployeeEntity employee = getById(id);

        if (!employee.getCc().equals(request.getCc()) &&
                employeeRepository.existsByCc(request.getCc())) {
            throw new IllegalArgumentException(
                "Ya existe un empleado con ese tipo de cédula: " + request.getCc());
        }

        employee.setCc(request.getCc());
        employee.setPost(Post.valueOf(request.getPost()));
        employee.setName(request.getName());
        employee.setEntryDate(request.getEntryDate());
        employee.setSalary(request.getSalary());

        return employeeRepository.save(employee);
    }

    public void delete(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException(
                "El empleado no se encuentra por ese id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public List<EmployeeEntity> getByPost(String postStr) {
        Post post = Post.valueOf(postStr.toUpperCase());
        return employeeRepository.findByPost(post);
    }

    public List<EmployeeEntity> getByDate(LocalDate entryDate) {
        if (entryDate == null) {
            throw new IllegalArgumentException(
                "La fecha no puede ser nula. El formato debe ser: yyyy-MM-dd");
        }
        return employeeRepository.findByEntryDate(entryDate);
    }

}