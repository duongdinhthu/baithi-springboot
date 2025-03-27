package com.project.dethi.service;

import com.project.dethi.entity.Employee;
import com.project.dethi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    // 1. Lấy danh sách tất cả nhân viên
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // 2. Tìm kiếm nhân viên theo tên
    public List<Employee> searchEmployees(String name) {
        return repository.findByNameContaining(name);
    }

    // 3. Lấy nhân viên theo ID
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = repository.findById(id);
        return employee.orElse(null);
    }

    // 4. Thêm mới nhân viên
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    // 5. Cập nhật thông tin nhân viên
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = repository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            return repository.save(employee);
        }
        return null;
    }

    // 6. Xóa nhân viên theo ID
    public boolean deleteEmployee(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
