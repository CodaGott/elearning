package io.elearning.data.repository;

import io.elearning.data.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseName(String courseName);
    Optional<Course> findAllByCourseName(String courseName);
    List<Course> findAllByCoursePrice(double coursePrice);
}
