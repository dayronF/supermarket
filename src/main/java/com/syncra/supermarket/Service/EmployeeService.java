package com.syncra.supermarket.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.syncra.supermarket.Dto.Employee.EmployeeMessage;
import com.syncra.supermarket.Dto.Employee.EmployeeRequest;
import com.syncra.supermarket.Dto.Employee.EmployeeResponse;
import com.syncra.supermarket.Entity.EmployeeEntity;
import com.syncra.supermarket.Entity.EmployeeEntity.Post;
import com.syncra.supermarket.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> getAllEmployee() {
        List<EmployeeEntity> Empleados = employeeRepository.findAll();
        List<EmployeeResponse> EmpleadosR = new ArrayList<>();
        for (EmployeeEntity empleado : Empleados) {
            EmployeeResponse response = new EmployeeResponse();
            response.setCc(empleado.getCc());
            response.setName(empleado.getName());
            response.setPost(empleado.getPost().toString());
            response.setSalary(empleado.getSalary());
            response.setEntryDate(empleado.getEntrydate());
            EmpleadosR.add(response);
        }
        return EmpleadosR;
    }

    public EmployeeResponse getById(Integer cc) {
        Optional<EmployeeEntity> employeeO = employeeRepository.findById(cc);

        if (employeeO.isEmpty())
            return null;

        EmployeeEntity entity = employeeO.get();

        EmployeeResponse response = new EmployeeResponse();
        response.setCc(entity.getCc());
        response.setName(entity.getName());
        response.setPost(entity.getPost().toString());
        response.setSalary(entity.getSalary());
        response.setEntryDate(entity.getEntrydate());

        return response;
    }

    public EmployeeMessage create(EmployeeRequest request, Integer cc) {
        EmployeeMessage message = new EmployeeMessage(null);

        Optional<EmployeeEntity> employeeO = employeeRepository.findById(cc);
        if (employeeO.isEmpty()) {
            return new EmployeeMessage("Empleado no encontrado");
        }

        if (!employeeO.get().getPost().equals(Post.ADMINISTRADOR)) {
            return new EmployeeMessage("No autorizado para crear empleados");
        }

        if (employeeRepository.existsByCc(request.getCc())) {
            return new EmployeeMessage("Ya existe un empleado con esa cédula");
        }

        EmployeeEntity employee = new EmployeeEntity();
        employee.setCc(request.getCc());
        employee.setName(request.getName());
        employee.setPost(EmployeeEntity.Post.valueOf(request.getPost()));
        employee.setSalary(request.getSalary());
        employee.setEntrydate(request.getEntryDate());

        employeeRepository.save(employee);
        message.setMessage("Empleado creado exitosamente");
        return message;
    }

    public EmployeeMessage Update(Integer cc, EmployeeRequest request) {
        Optional<EmployeeEntity> eOptional = employeeRepository.findById(cc);

        if (eOptional.isEmpty()) {
            return new EmployeeMessage("Empleado no encontrado");
        }

        if (!eOptional.get().getPost().equals(Post.ADMINISTRADOR)) {
            return new EmployeeMessage("No autorizado para actualizar empleados");
        }

        EmployeeMessage message = new EmployeeMessage(null);

        EmployeeEntity entity = eOptional.get();
        entity.setCc(request.getCc());
        entity.setName(request.getName());
        entity.setPost(EmployeeEntity.Post.valueOf(request.getPost()));
        entity.setSalary(request.getSalary());
        entity.setEntrydate(request.getEntryDate());

        employeeRepository.save(entity);
        message.setMessage("Empleado actualizado correctamente");
        return message;
    }

    public EmployeeMessage delete(Integer cc) {
        Optional<EmployeeEntity> eOptional = employeeRepository.findById(cc);
        EmployeeMessage message = new EmployeeMessage(null);

        if (eOptional.isEmpty()) {
            return new EmployeeMessage("Empleado no encontrado");
        }

        if (!eOptional.get().getPost().equals(Post.ADMINISTRADOR)) {
            return new EmployeeMessage("No autorizado para eliminar empleados");
        }

        EmployeeEntity entity = eOptional.get();
        employeeRepository.delete(entity);
        message.setMessage("Empleado eliminado exitosamente");
        return message;
    }

    public List<EmployeeResponse> getByPost(String postStr) {
        Post post = Post.valueOf(postStr.toUpperCase());

        List<EmployeeEntity> empleados = employeeRepository.findByPost(post);
        List<EmployeeResponse> responses = new ArrayList<>();

        for (EmployeeEntity empleado : empleados) {
            EmployeeResponse response = new EmployeeResponse();
            response.setCc(empleado.getCc());
            response.setName(empleado.getName());
            response.setPost(empleado.getPost().toString());
            response.setSalary(empleado.getSalary());
            response.setEntryDate(empleado.getEntrydate());
            responses.add(response);
        }

        return responses;
    }

    public List<EmployeeResponse> getByDateRange(LocalDate startDate, LocalDate endDate) {

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return new ArrayList<>();
        }

        List<EmployeeEntity> empleados = employeeRepository.findByEntrydateBetween(startDate, endDate);

        List<EmployeeResponse> resultado = new ArrayList<>();

        for (EmployeeEntity empleado : empleados) {
            EmployeeResponse response = new EmployeeResponse();
            response.setCc(empleado.getCc());
            response.setName(empleado.getName());
            response.setPost(empleado.getPost().toString());
            response.setSalary(empleado.getSalary());
            response.setEntryDate(empleado.getEntrydate());
            resultado.add(response);
        }

        return resultado;
    }
}