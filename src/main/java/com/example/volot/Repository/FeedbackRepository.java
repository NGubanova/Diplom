package com.example.volot.Repository;

import com.example.volot.Models.Feedback;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    List<Feedback> findFeedbackByProductId(Long id);
}
