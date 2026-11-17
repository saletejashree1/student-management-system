
package com.example.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudentManagementApplication.class, args);

        System.out.println("----------------------------------------");
        System.out.println("   CAMPUSPRO APPLICATION STARTED");
        System.out.println("----------------------------------------");
        System.out.println("   Dashboard: http://localhost:8081/dashboard");
        System.out.println("   Students : http://localhost:8081/students");
        System.out.println("----------------------------------------");
    }
}
